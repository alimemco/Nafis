package com.ali.rnp.nafis.view.fragment;

import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ali.rnp.nafis.R;
import com.ali.rnp.nafis.view.MyApplication;

public class FragmentUserInfo extends Fragment implements AppBarLayout.OnOffsetChangedListener  {

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 200;

    private boolean mIsTheTitleVisible          = false;
    private boolean mIsTheTitleContainerVisible = true;

    private TextView user_name;
    private TextView user_code;
    private TextView user_name_toolbar;
    private ImageView settingImg;
    private AppBarLayout appBarLayout;
    private Button userLevelBtn;
    private TextView subset_title;
    private TextView subset_number;
    private TextView subset_people;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_user_info,null,false);

        initViews(rootView);

        appBarLayout.addOnOffsetChangedListener(this);

       // mToolbar.inflateMenu(R.menu.menu_main);
        startAlphaAnimation(user_name_toolbar, 0, View.INVISIBLE);


        return rootView;
    }

    private void initViews(View rootView) {

        user_name = rootView.findViewById(R.id.fragment_user_info_txt_name);
        user_code = rootView.findViewById(R.id.fragment_user_info_txt_code);
        user_name_toolbar = rootView.findViewById(R.id.fragment_user_info_user_name_toolbar);
        settingImg = rootView.findViewById(R.id.fragment_user_info_setting);
        appBarLayout = rootView.findViewById(R.id.fragment_user_appbar);
        userLevelBtn = rootView.findViewById(R.id.fragment_user_info_btn_level);
        subset_title = rootView.findViewById(R.id.fragment_user_info_txt_subset_title);
        subset_number = rootView.findViewById(R.id.fragment_user_info_txt_subset_Number);
        subset_people = rootView.findViewById(R.id.fragment_user_info_txt_subset_people);

        user_name.setTypeface(MyApplication.getBYekanFont(getContext()));
        user_name_toolbar.setTypeface(MyApplication.getBYekanFont(getContext()));
        subset_title.setTypeface(MyApplication.getBYekanFont(getContext()));
        subset_people.setTypeface(MyApplication.getBYekanFont(getContext()));
        userLevelBtn.setTypeface(MyApplication.getBYekanFont(getContext()));

    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);

    }

    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

            if(!mIsTheTitleVisible) {
               startAlphaAnimation(user_name_toolbar, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    settingImg.setImageTintList(ResourcesCompat.getColorStateList(getResources(),R.color.toolbar_collapsed,null));
                }
               // user_name_toolbar.setTextColor(ResourcesCompat.getColor(getResources(),R.color.toolbar_collapsed,null));



                mIsTheTitleVisible = true;
            }

        } else {


            if (mIsTheTitleVisible) {
                startAlphaAnimation(user_name_toolbar, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    settingImg.setImageTintList(ResourcesCompat.getColorStateList(getResources(),R.color.white,null));
                }

                //user_name_toolbar.setTextColor(ResourcesCompat.getColor(getResources(),R.color.red200,null));



                mIsTheTitleVisible = false;
            }
        }
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {

            if(mIsTheTitleContainerVisible) {
               //startAlphaAnimation(user_name, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);

                //user_name_toolbar.setTextColor(ResourcesCompat.getColor(getResources(),R.color.red400,null));


                mIsTheTitleContainerVisible = false;
            }

        } else {


            if (!mIsTheTitleContainerVisible) {
              // startAlphaAnimation(user_name, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);

               // user_name_toolbar.setTextColor(ResourcesCompat.getColor(getResources(),R.color.red500,null));


                mIsTheTitleContainerVisible = true;
            }
        }
    }

    public static void startAlphaAnimation (View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }
}
