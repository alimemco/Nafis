package com.ali.rnp.nafis.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.ali.rnp.nafis.R;
import com.ali.rnp.nafis.view.DataModel.Question;
import com.ali.rnp.nafis.view.MyApplication;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionHolder>{

    private static final String TAG = "QuestionAdapter";
    private List<Question> questions;
    Context context;
    private int lastPosition = 0;
    public QuestionAdapter(Context context){
        this.context=context;
    }

    public void SetupQuestionAdapter(List<Question> questions){
        this.questions = questions;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.question_item,null,false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rootView.setLayoutParams(lp);
        return new QuestionHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionHolder holder, final int position) {


        final Question question = questions.get(position);

        checkBoxDetect(holder,position);

        holder.txtQuestion.setText(question.getQuestion());

        if(question.getExplanation()!=null){
            holder.explanationCardview.setVisibility(View.VISIBLE);
            holder.edtxtExplanation.setHint(question.getExplanation());
        }else {
            holder.explanationCardview.setVisibility(View.GONE);
        }

        Log.i(TAG, "onBindViewHolder: "+position);

        CheckBoxAnswerSetText(holder,question);

    }

    private void checkBoxDetect(QuestionHolder holder, int position) {

        if (position>= lastPosition){
            holder.chbAnswer1.setChecked(false);
            holder.chbAnswer2.setChecked(false);
            holder.chbAnswer3.setChecked(false);
            holder.chbAnswer4.setChecked(false);
            holder.chbAnswer5.setChecked(false);
            holder.chbAnswer6.setChecked(false);
            holder.chbAnswer7.setChecked(false);
            holder.chbAnswer8.setChecked(false);
            holder.chbAnswer9.setChecked(false);
            holder.edtxtExplanation.setText("");
        }
        lastPosition=position;
    }

    private void CheckBoxAnswerSetText(QuestionHolder holder, Question question) {



        if (question.getAnswer1()!=null){
            holder.chbAnswer1.setVisibility(View.VISIBLE);
            holder.chbAnswer1.setText(question.getAnswer1());
        }else {
            holder.chbAnswer1.setVisibility(View.GONE);
        }


        if (question.getAnswer2()!=null){
            holder.chbAnswer2.setVisibility(View.VISIBLE);
            holder.chbAnswer2.setText(question.getAnswer2());
        }else {
            holder.chbAnswer2.setVisibility(View.GONE);
        }


        if (question.getAnswer3()!=null){
            holder.chbAnswer3.setVisibility(View.VISIBLE);
            holder.chbAnswer3.setText(question.getAnswer3());
        }else {
            holder.chbAnswer3.setVisibility(View.GONE);
        }


        if (question.getAnswer4()!=null){
            holder.chbAnswer4.setVisibility(View.VISIBLE);
            holder.chbAnswer4.setText(question.getAnswer4());
        }else {
            holder.chbAnswer4.setVisibility(View.GONE);
        }


        if (question.getAnswer5()!=null){
            holder.chbAnswer5.setVisibility(View.VISIBLE);
            holder.chbAnswer5.setText(question.getAnswer5());
        }else {
            holder.chbAnswer5.setVisibility(View.GONE);
        }


        if (question.getAnswer6()!=null){
            holder.chbAnswer6.setVisibility(View.VISIBLE);
            holder.chbAnswer6.setText(question.getAnswer6());
        }else {
            holder.chbAnswer6.setVisibility(View.GONE);
        }

        if (question.getAnswer7()!=null){
            holder.chbAnswer7.setVisibility(View.VISIBLE);
            holder.chbAnswer7.setText(question.getAnswer7());
        }else {
            holder.chbAnswer7.setVisibility(View.GONE);
        }

        if (question.getAnswer8()!=null){
            holder.chbAnswer8.setVisibility(View.VISIBLE);
            holder.chbAnswer8.setText(question.getAnswer8());
        }else {
            holder.chbAnswer8.setVisibility(View.GONE);
        }

        if (question.getAnswer9()!=null){
            holder.chbAnswer9.setVisibility(View.VISIBLE);
            holder.chbAnswer9.setText(question.getAnswer9());
        }else {
            holder.chbAnswer9.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class QuestionHolder extends RecyclerView.ViewHolder {
        private TextView txtQuestion;
        private CheckBox chbAnswer1;
        private CheckBox chbAnswer2;
        private CheckBox chbAnswer3;
        private CheckBox chbAnswer4;
        private CheckBox chbAnswer5;
        private CheckBox chbAnswer6;
        private CheckBox chbAnswer7;
        private CheckBox chbAnswer8;
        private CheckBox chbAnswer9;
        private CardView explanationCardview;
        private EditText edtxtExplanation;
        public QuestionHolder(View itemView) {
            super(itemView);
            txtQuestion=itemView.findViewById(R.id.question_item_question);
            chbAnswer1=itemView.findViewById(R.id.question_item_checkbox1);
            chbAnswer2=itemView.findViewById(R.id.question_item_checkbox2);
            chbAnswer3=itemView.findViewById(R.id.question_item_checkbox3);
            chbAnswer4=itemView.findViewById(R.id.question_item_checkbox4);
            chbAnswer5=itemView.findViewById(R.id.question_item_checkbox5);
            chbAnswer6=itemView.findViewById(R.id.question_item_checkbox6);
            chbAnswer7=itemView.findViewById(R.id.question_item_checkbox7);
            chbAnswer8=itemView.findViewById(R.id.question_item_checkbox8);
            chbAnswer9=itemView.findViewById(R.id.question_item_checkbox9);
            edtxtExplanation=itemView.findViewById(R.id.question_item_editText_Explanation);
            explanationCardview=itemView.findViewById(R.id.question_item_cardView_Explanation);

            txtQuestion.setTypeface(MyApplication.getIranianSansFont(context));
            chbAnswer1.setTypeface(MyApplication.getIranianSansFont(context));
            chbAnswer2.setTypeface(MyApplication.getIranianSansFont(context));
            chbAnswer3.setTypeface(MyApplication.getIranianSansFont(context));
            chbAnswer4.setTypeface(MyApplication.getIranianSansFont(context));
            chbAnswer5.setTypeface(MyApplication.getIranianSansFont(context));
            chbAnswer6.setTypeface(MyApplication.getIranianSansFont(context));
            chbAnswer7.setTypeface(MyApplication.getIranianSansFont(context));
            chbAnswer8.setTypeface(MyApplication.getIranianSansFont(context));
            chbAnswer9.setTypeface(MyApplication.getIranianSansFont(context));
            edtxtExplanation.setTypeface(MyApplication.getIranianSansFont(context));

        }
    }


}
