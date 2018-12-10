package com.ali.rnp.nafis.view.activity;


import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ali.rnp.nafis.R;
import com.shuhart.stepview.StepView;


public class Test extends AppCompatActivity {

    private StepView stepView;
    private Button btnNext;
    private Button btnback;
    int stepcu = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        stepView = findViewById(R.id.step_view);
        btnback = findViewById(R.id.backBTN);
        btnNext = findViewById(R.id.nextBTN);

        stepView.getState()
                .selectedTextColor(ContextCompat.getColor(this, R.color.colorAccent))
                .animationType(StepView.ANIMATION_CIRCLE)
                .selectedCircleColor(ContextCompat.getColor(this, R.color.colorAccent))
                .selectedStepNumberColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .stepsNumber(17)
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .commit();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stepcu=stepcu ++;
                stepView.go(stepcu, true);

            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stepcu=stepcu --;
                stepView.go(stepcu, true);

            }
        });

        }

}
