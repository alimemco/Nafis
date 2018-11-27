package com.ali.rnp.nafis.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ali.rnp.nafis.R;
import com.ali.rnp.nafis.view.DataModel.ApiService;
import com.ali.rnp.nafis.view.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

public class FragmentUser extends Fragment {

    private EditText username;
    private EditText password;
    private TabLayout tabLayout;
    private static final String TAG = "FragmentUser";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_user,container,false);
        tabLayout = rootView.findViewById(R.id.fragment_user_tabLayout);
        //no
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:

                        break;
                    case 1:

                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        /*
        username = rootView.findViewById(R.id.fragment_user_edtxt_username);
        password = rootView.findViewById(R.id.fragment_user_edtxt_password);
        btn_login = rootView.findViewById(R.id.fragment_user_btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Utils.checkConnection(getActivity())){
                    loginUserJsonObject();
                }else {
                    Toast.makeText(getActivity(),"اینترنت در دسترس نیست",Toast.LENGTH_SHORT).show();
                }


            }
        });
*/
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
                            Log.i(TAG, "user not found: "+status);
                            Toast.makeText(getActivity(), status+"", Toast.LENGTH_SHORT).show();
                            break;

                        case 1:
                            Log.i(TAG, "success: "+status);
                            Toast.makeText(getActivity(), status+"", Toast.LENGTH_SHORT).show();

                            break;

                        case 2:
                            Log.i(TAG, "pass incurent: "+status);
                            Toast.makeText(getActivity(), status+"", Toast.LENGTH_SHORT).show();

                            break;

                        case 404:
                            Log.i(TAG, "error: "+status);
                            Toast.makeText(getActivity(), status+"", Toast.LENGTH_SHORT).show();
                            break;

                    }
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
