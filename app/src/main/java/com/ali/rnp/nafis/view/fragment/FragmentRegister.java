package com.ali.rnp.nafis.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ali.rnp.nafis.R;
import com.ali.rnp.nafis.view.DataModel.ApiService;
import com.ali.rnp.nafis.view.MyApplication;
import com.ali.rnp.nafis.view.utils.Utils;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.json.JSONException;
import org.json.JSONObject;

import es.dmoral.toasty.Toasty;

public class FragmentRegister extends Fragment {

    private TextInputEditText firstNameEditText;
    private TextInputEditText lastNameEditText;
    private TextInputEditText emailEditText;
    private TextInputEditText usernameEditText;
    private TextInputEditText passwordEditText;
    private TextInputLayout firstEditTextLayout;
    private TextInputLayout lastEditTextLayout;
    private TextInputLayout emailEditTextLayout;
    private TextInputLayout usernameEditTextLayout;
    private TextInputLayout passwordEditTextLayout;
    private Button sendButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register,null,false);

        initViews(rootView);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.checkConnection(getActivity())){

                    if (!usernameEditText.getText().toString().equals("") &&
                            !passwordEditText.getText().toString().equals("") &&
                           !emailEditText.getText().toString().equals("") &&
                            !firstNameEditText.getText().toString().equals("") &&
                            !lastNameEditText.getText().toString().equals("")){

                        registerUserJsonObject();

                    }else {
                        if (usernameEditText.getText().toString().equals("")){
                            YoYo.with(Techniques.Shake)
                                    .duration(1000)
                                    .playOn(usernameEditTextLayout);
                        }
                        if (passwordEditText.getText().toString().equals("")){
                            YoYo.with(Techniques.Shake)
                                    .duration(1000)
                                    .playOn(passwordEditTextLayout);
                        }

                        if (emailEditText.getText().toString().equals("")){
                            YoYo.with(Techniques.Shake)
                                    .duration(1000)
                                    .playOn(emailEditTextLayout);
                        }

                        if (firstNameEditText.getText().toString().equals("")){
                            YoYo.with(Techniques.Shake)
                                    .duration(1000)
                                    .playOn(firstEditTextLayout);
                        }

                        if (lastNameEditText.getText().toString().equals("")){
                            YoYo.with(Techniques.Shake)
                                    .duration(1000)
                                    .playOn(lastEditTextLayout);
                        }
                    }

                }else {
                    Toasty.info(getActivity(),"اینترنت در دسترس نیست" , Toast.LENGTH_SHORT).show();
                }
            }
        });


        return rootView;
    }

    private void registerUserJsonObject() {
        JSONObject registerJsonObject = new JSONObject();
        ApiService apiService = new ApiService(getActivity());
        String display_name = firstNameEditText.getText().toString()+" "+lastNameEditText.getText().toString();
        try {
            registerJsonObject.put("username",usernameEditText.getText().toString());
            registerJsonObject.put("password",passwordEditText.getText().toString());
            registerJsonObject.put("email",emailEditText.getText().toString());
            registerJsonObject.put("nickname",display_name);
            registerJsonObject.put("display_name",display_name);
            registerJsonObject.put("first_name",firstNameEditText.getText().toString());
            registerJsonObject.put("last_name",lastNameEditText.getText().toString());

            apiService.registerUser(registerJsonObject, new ApiService.onRegisterUserReceived() {
                @Override
                public void onRegisterUser(int status) {
                    switch (status){
                        case 0:
                            Toasty.error(getActivity(),"نام کاربری وجود دارد",Toast.LENGTH_SHORT).show();
                            break;

                        case 1:
                            Toasty.error(getActivity(),"ایمیل وجود دارد",Toast.LENGTH_SHORT).show();
                            break;


                        case 2:
                            Toasty.error(getActivity(),"خطا در ثبت نام",Toast.LENGTH_SHORT).show();

                            break;


                        case 3:
                            Toasty.success(getActivity(),"ثبت نام انجام شد :)",Toast.LENGTH_SHORT).show();

                            break;

                        case 404:
                            Toasty.warning(getActivity(),"خطا در دریافت دریافت اطلاعات",Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initViews(View rootView) {
        firstNameEditText = rootView.findViewById(R.id.fragment_register_first_name);
        lastNameEditText = rootView.findViewById(R.id.fragment_register_last_name);
        emailEditText = rootView.findViewById(R.id.fragment_register_email);
        usernameEditText = rootView.findViewById(R.id.fragment_register_username);
        passwordEditText = rootView.findViewById(R.id.fragment_register_password);
        sendButton = rootView.findViewById(R.id.fragment_register_sendButton);
        firstEditTextLayout = rootView.findViewById(R.id.fragment_register_first_name_layout);
        lastEditTextLayout = rootView.findViewById(R.id.fragment_register_last_name_layout);
        emailEditTextLayout = rootView.findViewById(R.id.fragment_register_email_layout);
        usernameEditTextLayout = rootView.findViewById(R.id.fragment_register_username_layout);
        passwordEditTextLayout = rootView.findViewById(R.id.fragment_register_password_layout);

        firstNameEditText.setTypeface(MyApplication.getIranianSansFont(getActivity()));
        lastNameEditText.setTypeface(MyApplication.getIranianSansFont(getActivity()));
        emailEditText.setTypeface(MyApplication.getIranianSansFont(getActivity()));
        usernameEditText.setTypeface(MyApplication.getIranianSansFont(getActivity()));
        sendButton.setTypeface(MyApplication.getIranianSansFont(getActivity()));

        firstEditTextLayout.setTypeface(MyApplication.getIranianSansFont(getActivity()));
        lastEditTextLayout.setTypeface(MyApplication.getIranianSansFont(getActivity()));
        emailEditTextLayout.setTypeface(MyApplication.getIranianSansFont(getActivity()));
        usernameEditTextLayout.setTypeface(MyApplication.getIranianSansFont(getActivity()));
        passwordEditTextLayout.setTypeface(MyApplication.getIranianSansFont(getActivity()));

    }

    private boolean isEmailValid(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
