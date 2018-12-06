package com.ali.rnp.nafis.view.fragment;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
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
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.dd.processbutton.iml.ActionProcessButton;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class FragmentLogin extends Fragment implements Validator.ValidationListener {

    @NotEmpty(messageResId = R.string.validation_username)
    private TextInputEditText username;

    @NotEmpty(messageResId = R.string.validation_username)
    @Password(min = 3, messageResId = R.string.validation_password)
    private TextInputEditText password;

    private TextInputLayout usernameLayout;
    private TextInputLayout passwordLayout;
    private TextView forgetPassword;

    private Button btnStart;
    private ActionProcessButton btnSignIn;

    private Validator validator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, null, false);
        username = rootView.findViewById(R.id.fragment_login_username);
        password = rootView.findViewById(R.id.fragment_login_password);
        forgetPassword = rootView.findViewById(R.id.fragment_login_forgetPassword_text);
        usernameLayout = rootView.findViewById(R.id.fragment_login_InputLayout_username);
        passwordLayout = rootView.findViewById(R.id.fragment_login_InputLayout_password);
        // btnStart = rootView.findViewById(R.id.fragment_login_buttonStart);
        btnSignIn = rootView.findViewById(R.id.fragment_login_processButton);

        usernameLayout.setTypeface(MyApplication.getIranianSansFont(getActivity()));
        passwordLayout.setTypeface(MyApplication.getIranianSansFont(getActivity()));
        forgetPassword.setTypeface(MyApplication.getIranianSansFont(getActivity()));
        btnSignIn.setTypeface(MyApplication.getIranianSansFont(getActivity()));


        validator = new Validator(this);
        validator.setValidationListener(this);


        btnSignIn.setMode(ActionProcessButton.Mode.ENDLESS);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validator.validate();

            }
        });


        return rootView;
    }


    private void checkLoginInfo() {
        if (Utils.checkConnection(getActivity())) {
            if (!username.getText().toString().equals("") && !password.getText().toString().equals("")) {
                loginWayButton(50,false,getResources().getString(R.string.UserLogin));
                loginUserJsonObject();
            } else {
                if (password.getText().toString().equals("")) {
                    Toasty.warning(getActivity(), "کلمه عبور نمی تواند خالی باشد", Toast.LENGTH_SHORT).show();
                    //password.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
                    YoYo.with(Techniques.Shake)
                            .duration(1000)
                            .playOn(passwordLayout);

                }
                if (username.getText().toString().equals("")) {
                    Toasty.warning(getActivity(), "نام کابری نمی تواند خالی باشد", Toast.LENGTH_SHORT).show();
                    // username.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
                    YoYo.with(Techniques.Shake)
                            .duration(1000)
                            .playOn(usernameLayout);
                }

            }

        } else {
            Toasty.info(getActivity(), "اینترنت در دسترس نیست", Toast.LENGTH_SHORT).show();
        }

    }

    private void loginUserJsonObject() {
        JSONObject jsonObjectLogin = new JSONObject();
        try {
            jsonObjectLogin.put("username", username.getText().toString());
            jsonObjectLogin.put("password", password.getText().toString());

            final ApiService apiService = new ApiService(getContext());
            apiService.loginUser(jsonObjectLogin, new ApiService.onLoginUserReceived() {
                @Override
                public void onLoginUser(int status) {

                    ButtonStateNormalTimer();

                    switch (status) {

                        case 0:
                            loginWayButton(-1,true,getResources().getString(R.string.usernameNotExist));
                            YoYo.with(Techniques.Shake)
                                    .duration(1000)
                                    .playOn(usernameLayout);
                            username.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);

                            break;

                        case 1:

                            loginWayButton(100,true,getResources().getString(R.string.successLogin));


                            break;

                        case 2:

                            loginWayButton(-1,true,getResources().getString(R.string.passwordNotExist));

                            YoYo.with(Techniques.Shake)
                                    .duration(1000)
                                    .playOn(passwordLayout);

                            password.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);

                            break;

                        case 404:
                            loginWayButton(-1,true,getResources().getString(R.string.errorInGetData));

                            break;

                    }
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onValidationSucceeded() {

        checkLoginInfo();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {




        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getContext());

            // Display error messages ;)
            if (view instanceof TextInputEditText) {
                ((TextInputEditText) view).setError(message);
            } else {
                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void ButtonStateNormalTimer() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                btnSignIn.setProgress(0);

            }
        }, 4000);
    }


    private void loginWayButton(int Progress,boolean MODE,CharSequence TextButton) {

        btnSignIn.setProgress(Progress);
        btnSignIn.setText(TextButton);
        username.setEnabled(MODE);
        password.setEnabled(MODE);

    }



}
