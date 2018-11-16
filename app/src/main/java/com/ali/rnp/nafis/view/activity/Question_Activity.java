package com.ali.rnp.nafis.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ali.rnp.nafis.R;
import com.ali.rnp.nafis.view.DataModel.DataGenrator;
import com.ali.rnp.nafis.view.adapter.QuestionAdapter;

public class Question_Activity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        recyclerView=findViewById(R.id.activity_question_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(Question_Activity.this,LinearLayoutManager.HORIZONTAL,false));
        QuestionAdapter questionAdapter = new QuestionAdapter(this);
        questionAdapter.SetupQuestionAdapter(DataGenrator.getQuestions());
        recyclerView.setAdapter(questionAdapter);

    }
}
