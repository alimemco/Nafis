package com.ali.rnp.nafis.view.activity;


import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.ali.rnp.nafis.R;
import com.ali.rnp.nafis.view.CustomGridLayoutManager;
import com.ali.rnp.nafis.view.DataModel.DataGenrator;
import com.ali.rnp.nafis.view.MyApplication;
import com.ali.rnp.nafis.view.adapter.QuestionAdapter;


import params.com.stepview.StatusViewScroller;


public class Question_Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button pre;
    private Button next;
    private ImageView bakBtn;
    private int RecyclerPosition;
    private LinearLayoutManager customGridLayoutManager;
    private StatusViewScroller statusViewScroller;




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

                    statusViewScroller.getStatusView().setCurrentCount(RecyclerPosition+1);

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            statusViewScroller.scrollToStep((RecyclerPosition-1));
                        }
                    },1);


                }


            }
        });

        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (RecyclerPosition >= 1) {

                    RecyclerPosition--;
                    recyclerView.getLayoutManager().scrollToPosition(RecyclerPosition);

                    statusViewScroller.getStatusView().setCurrentCount(RecyclerPosition+1);

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            statusViewScroller.scrollToStep((RecyclerPosition-1));
                        }
                    },1);



                }

            }

        });

    }



    private void setupRecyclerView() {
        customGridLayoutManager = new CustomGridLayoutManager(this) {

            @Override
            public boolean canScrollHorizontally() {
                return false;
            }

        };
        recyclerView.setLayoutManager(new CustomGridLayoutManager(Question_Activity.this, LinearLayoutManager.HORIZONTAL, false));
        QuestionAdapter questionAdapter = new QuestionAdapter(this);
        questionAdapter.SetupQuestionAdapter(DataGenrator.getQuestions());
        recyclerView.setNestedScrollingEnabled(false);

        recyclerView.setAdapter(questionAdapter);
    }

    private void initViews() {
        recyclerView = findViewById(R.id.activity_question_recyclerView);
        pre = findViewById(R.id.activity_question_previous);
        next = findViewById(R.id.activity_question_next);
        bakBtn = findViewById(R.id.ativity_question_back);

        pre.setTypeface(MyApplication.getIranianSansFont(this));
        next.setTypeface(MyApplication.getIranianSansFont(this));

        statusViewScroller = findViewById(R.id.activity_question_StatusViewScroller);



        bakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }



}
