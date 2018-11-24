package com.ali.rnp.nafis.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.ali.rnp.nafis.R;
import com.ali.rnp.nafis.view.DataModel.DataGenrator;
import com.ali.rnp.nafis.view.MyApplication;
import com.ali.rnp.nafis.view.adapter.QuestionAdapter;


public class Question_Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button pre;
    private Button next;
    private int RecyclerPosition;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        initViews();
        setupRecyclerView();





        RecyclerPosition = 0;
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RecyclerPosition <= 15) {

                    RecyclerPosition++;
                    recyclerView.getLayoutManager().scrollToPosition(RecyclerPosition);

                }
            }
        });

        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RecyclerPosition >= 1) {
                    RecyclerPosition--;
                    recyclerView.getLayoutManager().scrollToPosition(RecyclerPosition);

                }
            }
        });

    }



    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(Question_Activity.this, linearLayoutManager.HORIZONTAL, false));
        QuestionAdapter questionAdapter = new QuestionAdapter(this);
        questionAdapter.SetupQuestionAdapter(DataGenrator.getQuestions());
        recyclerView.setNestedScrollingEnabled(false);

        recyclerView.setAdapter(questionAdapter);
    }

    private void initViews() {
        recyclerView = findViewById(R.id.activity_question_recyclerView);
        pre = findViewById(R.id.activity_question_previous);
        next = findViewById(R.id.activity_question_next);
        pre.setTypeface(MyApplication.getIranianSansFont(this));
        next.setTypeface(MyApplication.getIranianSansFont(this));
    }

    public class CustomGridLayoutManager extends LinearLayoutManager {
        private boolean isScrollEnabled = true;

        public CustomGridLayoutManager(Context context) {
            super(context);
        }

        public void setScrollEnabled(boolean flag) {
            this.isScrollEnabled = flag;
        }

        @Override
        public boolean canScrollVertically() {
            //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
            return isScrollEnabled && super.canScrollVertically();
        }
    }
}
