package com.ali.rnp.nafis.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ali.rnp.nafis.R;
import com.ali.rnp.nafis.view.MyApplication;

public class FragmentUser extends Fragment {


    private TabLayout tabLayout;
    private static final String TAG = "FragmentUser";
    private boolean isLogin=true;
    FragmentLogin fragmentLogin;
    FragmentRegister fragmentRegister;
    FragmentManager fragmentManager;
    FrameLayout frameLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_user,container,false);
        tabLayout = rootView.findViewById(R.id.fragment_user_tabLayout);
        frameLayout = rootView.findViewById(R.id.fragment_user_frame_layout);



        setupTabLayout();
        setupFragments();




        return rootView;
    }

    private void setupFragments() {
        fragmentLogin = new FragmentLogin();
        fragmentRegister = new FragmentRegister();
        fragmentManager = getFragmentManager();

        FragmentTransaction loginTransaction = fragmentManager.beginTransaction();
        loginTransaction.add(R.id.fragment_user_frame_layout,fragmentLogin);
        loginTransaction.commit();

    }

    private void setupTabLayout() {
        changeTabsFont();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()){
                    case 0:
                        isLogin=true;
                        FragmentTransaction loginTransaction = fragmentManager.beginTransaction();
                        loginTransaction.replace(R.id.fragment_user_frame_layout,fragmentLogin);
                        loginTransaction.commit();

                        break;
                    case 1:
                        isLogin=false;
                        FragmentTransaction registerTransaction = fragmentManager.beginTransaction();
                        registerTransaction.replace(R.id.fragment_user_frame_layout,fragmentRegister);
                        registerTransaction.commit();
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

    }

    private void changeTabsFont() {
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(MyApplication.getBYekanFont(getActivity()));
                }
            }
        }
    }


}
