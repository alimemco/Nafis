package com.ali.rnp.nafis.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ali.rnp.nafis.R;
import com.ali.rnp.nafis.view.DataModel.ApiService;
import com.ali.rnp.nafis.view.MyApplication;
import com.ali.rnp.nafis.view.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

public class FragmentLogin extends Fragment {

    private TextInputEditText username;
    private TextInputEditText password;
    private TextInputLayout usernameLayout;
    private TextInputLayout passwordLayout;
    private TextView forgetPassword;

    private Button btnStart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login,null,false);
        username = rootView.findViewById(R.id.fragment_login_username);
        password = rootView.findViewById(R.id.fragment_login_password);
        forgetPassword = rootView.findViewById(R.id.fragment_login_forgetPassword_text);
        usernameLayout = rootView.findViewById(R.id.fragment_login_InputLayout_username);
        passwordLayout = rootView.findViewById(R.id.fragment_login_InputLayout_password);
        btnStart = rootView.findViewById(R.id.fragment_login_buttonStart);
        usernameLayout.setTypeface(MyApplication.getIranianSansFont(getActivity()));
        passwordLayout.setTypeface(MyApplication.getIranianSansFont(getActivity()));
        forgetPassword.setTypeface(MyApplication.getIranianSansFont(getActivity()));
        btnStart.setTypeface(MyApplication.getIranianSansFont(getActivity()));


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Utils.checkConnection(getActivity())){
                    loginUserJsonObject();
                }else {
                    Toast.makeText(getActivity(),"اینترنت در دسترس نیست",Toast.LENGTH_SHORT).show();
                }


            }
        });
        return rootView;
    }

    private void loginUserJsonObject(){
        JSONObject jsonObjectLogin = new JSONObject();
        try {
            jsonObjectLogin.put("username",username.getText().toString());
            jsonObjectLogin.put("password",password.getText().toString());

            final ApiService apiService = new ApiService(getContext());
            apiService.loginUser(jsonObjectLogin, new ApiService.onLoginUserReceived() {
                @Override
                public void onLoginUser(int status) {
                    switch (status){

                        case 0:
                            Toast.makeText(getActivity(), "نام کاربری وجود ندارد", Toast.LENGTH_SHORT).show();
                            break;

                        case 1:
                            Toast.makeText(getActivity(), "با موفقیت وارد شدید :)", Toast.LENGTH_SHORT).show();
                            break;

                        case 2:
                            Toast.makeText(getActivity(), "پسورد اشتباه می باشد", Toast.LENGTH_SHORT).show();
                            break;

                        case 404:
                            Toast.makeText(getActivity(), "خطا در دریافت اطلاعات", Toast.LENGTH_SHORT).show();
                            break;

                    }
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
