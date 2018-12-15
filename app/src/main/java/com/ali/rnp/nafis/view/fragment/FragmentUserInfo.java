package com.ali.rnp.nafis.view.fragment;

import android.annotation.SuppressLint;
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
import com.ali.rnp.nafis.view.DataModel.SharedPrefManager;
import com.ali.rnp.nafis.view.DataModel.User;
import com.ali.rnp.nafis.view.MyApplication;
import com.ali.rnp.nafis.view.activity.Main_Activity;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

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

    private TextView user_code_hint;
    private TextView sale_title;
    private TextView sale_number;
    private TextView sale_toman;

    private TextView fab_sale_title;
    private TextView fab_subsets_title;

    private CircularImageView circularImageView;
    SharedPrefManager sharedPrefManager;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_user_info,null,false);

        initViews(rootView);

        setDataFromSharedPf();

        appBarLayout.addOnOffsetChangedListener(this);

       // mToolbar.inflateMenu(R.menu.menu_main);
        startAlphaAnimation(user_name_toolbar, 0, View.INVISIBLE);


        return rootView;
    }

    @SuppressLint("SetTextI18n")
    private void setDataFromSharedPf() {

        User user = sharedPrefManager.getUserInfo();

        user_name.setText(user.getFirstName()+" "+user.getLastName());
        user_name_toolbar.setText(user.getFirstName()+" "+user.getLastName());
        user_code.setText(user.getUsername());

        setButtonUserLevel(user.getCapacity());

        if (!user.getImage_url().isEmpty() &&
                !user.getImage_url().equals("")) {
            Picasso.get().load(user.getImage_url()).into(circularImageView);
        }



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
        circularImageView = rootView.findViewById(R.id.fragment_user_info_img_profile);
        user_code_hint = rootView.findViewById(R.id.fragment_user_info_code_hint);
        sale_title = rootView.findViewById(R.id.fragment_user_info_txt_sale_title);
        sale_number = rootView.findViewById(R.id.fragment_user_info_txt_sale_number);
        sale_toman = rootView.findViewById(R.id.fragment_user_info_txt_sale_toman);
        fab_sale_title = rootView.findViewById(R.id.fragment_user_info_txt_fab_sale);
        fab_subsets_title = rootView.findViewById(R.id.fragment_user_info_txt_fab_subsets);

        user_name.setTypeface(MyApplication.getBYekanFont(getContext()));
        user_name_toolbar.setTypeface(MyApplication.getBYekanFont(getContext()));
        subset_title.setTypeface(MyApplication.getBYekanFont(getContext()));
        subset_people.setTypeface(MyApplication.getBYekanFont(getContext()));
        userLevelBtn.setTypeface(MyApplication.getBYekanFont(getContext()));

        user_code_hint.setTypeface(MyApplication.getBYekanFont(getContext()));
        sale_title.setTypeface(MyApplication.getBYekanFont(getContext()));
        sale_toman.setTypeface(MyApplication.getBYekanFont(getContext()));

        fab_sale_title.setTypeface(MyApplication.getBYekanFont(getContext()));
        fab_subsets_title.setTypeface(MyApplication.getBYekanFont(getContext()));


        sharedPrefManager = new SharedPrefManager(getContext());

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

    public void setButtonUserLevel(String user_level) {
        switch (user_level){

            case Main_Activity.GUEST_LEVEL:
                userLevelBtn.setText("کاربر مهمان");
                break;

            case Main_Activity.NORMAL_LEVEL:
                userLevelBtn.setText("تازه کار");
                break;

            case Main_Activity.SUBSCRIBER_LEVEL:
                userLevelBtn.setText("تازه کار");
                break;

            case Main_Activity.ADMINISTRATOR_LEVEL:
                userLevelBtn.setText("مدیر عامل");
                break;


            case Main_Activity.AUTHOR_LEVEL:
                userLevelBtn.setText("نویسنده");
                break;


            case Main_Activity.CONTRIBUTOR_LEVEL:
                userLevelBtn.setText("مشارکت کننده");
                break;


            case Main_Activity.EDITOR_LEVEL:
                userLevelBtn.setText("ویرایشگر");
                break;


            case Main_Activity.SHOP_MANAGER_LEVEL:
                userLevelBtn.setText("مدیر فروشگاه");
                break;

            case Main_Activity.MARKETER_LEVEL:
                userLevelBtn.setText("بازاریاب");
                break;

            case Main_Activity.SENIORMARKETER_LEVEL:
                userLevelBtn.setText("بازاریاب ارشد");
                break;


            default:
                userLevelBtn.setText("مهمان");

        }

    }
}
