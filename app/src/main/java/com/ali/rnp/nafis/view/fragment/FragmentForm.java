package com.ali.rnp.nafis.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ali.rnp.nafis.R;
import com.ali.rnp.nafis.view.DbManager;
import com.ali.rnp.nafis.view.MyApplication;
import com.ali.rnp.nafis.view.activity.Question_Activity;

public class FragmentForm extends Fragment {

    private Button buttonStart;
    private TextView textDescription;

    private static final String TAG = "FragmentForm";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_form,container,false);

       buttonStart = view.findViewById(R.id.fragment_form_btnStart);
       textDescription = view.findViewById(R.id.fragment_form_description);
       textDescription.setTypeface(MyApplication.getIranianSansFont(getContext()));
       buttonStart.setTypeface(MyApplication.getIranianSansFont(getContext()));
       buttonStart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(getContext(), Question_Activity.class));
           }
       });

       return view;
    }
}
