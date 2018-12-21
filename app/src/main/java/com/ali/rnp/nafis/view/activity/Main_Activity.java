package com.ali.rnp.nafis.view.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ali.rnp.nafis.R;
import com.ali.rnp.nafis.view.DataModel.ApiService;
import com.ali.rnp.nafis.view.DataModel.Category;
import com.ali.rnp.nafis.view.DataModel.SharedPrefManager;
import com.ali.rnp.nafis.view.DataModel.User;
import com.ali.rnp.nafis.view.MyApplication;
import com.ali.rnp.nafis.view.fragment.FragmentForm;
import com.ali.rnp.nafis.view.fragment.FragmentHome;
import com.ali.rnp.nafis.view.fragment.FragmentUser;
import com.ali.rnp.nafis.view.fragment.FragmentUserInfo;
import com.ali.rnp.nafis.view.utils.BlurImage;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class Main_Activity extends AppCompatActivity {

    private ProgressBar progressBar;
    private BottomNavigationView bottomNavigationView;
    private FragmentHome fragmentHome;
    private FragmentUser fragmentUser;
    private FragmentForm fragmentForm;
    private FragmentUserInfo fragmentUserInfo;
    private NavigationView navigationView;

    private TextView userInfoText;
    private TextView userInfoEmail;
    private ImageView userInfoImageBackground;
    private com.mikhaellopez.circularimageview.CircularImageView userImage;
    private Button logOutBtn;
    private Button userLevelBtn;

    SharedPrefManager sharedPrefManager;
    DrawerLayout drawerLayout;
    android.support.v4.app.FragmentManager fragmentManager;

    private ImageView shopBtn;
    private ImageView searchBtn;

    public static final String GUEST_LEVEL="{\"guest\":true}";
    public static final String NORMAL_LEVEL="{\"normal\":true}";
    public static final String SUBSCRIBER_LEVEL="{\"subscriber\":true}";
    public static final String ADMINISTRATOR_LEVEL="{\"administrator\":true}";
    public static final String AUTHOR_LEVEL="{\"author\":true}";
    public static final String CONTRIBUTOR_LEVEL="{\"contributor\":true}";
    public static final String EDITOR_LEVEL="{\"editor\":true}";
    public static final String SHOP_MANAGER_LEVEL="{\"shop_manager\":true}";
    public static final String MARKETER_LEVEL="{\"marketer\":true}";
    public static final String SENIORMARKETER_LEVEL="{\"seniormarketer\":true}";

    public static int BLUR_PERCENTAGE =70;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupToolbar();
        setupFragments();
        setupBottomNavigation();
        statusBarColor();
        setupNavigationView();

        afterGetFromServer();

        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setUserInfoFromShPref();
                    }
                });

            }
        }).start();


        Toasty.Config.getInstance()
                .setToastTypeface(MyApplication.getIranianSansFont(this))
                .apply();

    }



    private void setupNavigationView() {
        navigationView = findViewById(R.id.main_navigation);
//
        userLevelBtn = navigationView.findViewById(R.id.activity_main_nav_user_level);
        userLevelBtn.setTypeface(MyApplication.getBYekanFont(this));
        userInfoText = navigationView.getHeaderView(0).findViewById(R.id.banner_drawer_layout_txt_name);
        userInfoEmail = navigationView.getHeaderView(0).findViewById(R.id.banner_drawer_layout_txt_email);
        userInfoText.setTypeface(MyApplication.getBYekanFont(this));
        userInfoImageBackground = navigationView.getHeaderView(0).findViewById(R.id.banner_drawer_layout_img_background);
        userImage  = navigationView.getHeaderView(0).findViewById(R.id.banner_drawer_layout_img_user);
        logOutBtn  = navigationView.getHeaderView(0).findViewById(R.id.banner_drawer_layout_btn_log_out);
        logOutBtn.setTypeface(MyApplication.getBYekanFont(this));

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout.closeDrawers();
                Toasty.info(Main_Activity.this,"با موفقیت از حساب کابری خارج شدید",Toast.LENGTH_SHORT).show();

                android.support.v4.app.FragmentTransaction HomeTransaction = fragmentManager.beginTransaction();
                HomeTransaction.replace(R.id.mainFragmentContainer, fragmentHome);
                HomeTransaction.commit();

                bottomNavigationView.setSelectedItemId(R.id.bottom_navigation_home);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        User user = new User();
                        user.setUsername(null);
                        user.setEmail(null);
                        user.setFirstName(null);
                        user.setLastName(null);
                        user.setCapacity(null);
                        user.setImage_url(null);
                        sharedPrefManager.SaveUserInfo(user);

                        setUserInfoFromShPref();
                        setBlurBannerBackgroundDefault();
                        userInfoEmail.setVisibility(View.GONE);
                        logOutBtn.setVisibility(View.GONE);
                        userImage.setBorderColor(ContextCompat.getColor(Main_Activity.this,R.color.white));

                        setButtonUserLevel(GUEST_LEVEL);


                    }
                },1000);


            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.menu_loginSignUp:

                        break;


                    case R.id.menu_logOut:

                        break;

                    case R.id.menu_about:

                        break;
                }


                return true;
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void statusBarColor() {
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.light_gray));
    }

    private void afterGetFromServer() {
        ApiService apiService = new ApiService(this);
        apiService.getCategoryFromServer(new ApiService.onGetCategories() {
            @Override
            public void onReceivedCategory(List<Category> categories) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }


    private void setupFragments() {
        fragmentHome = new FragmentHome();
        fragmentUser = new FragmentUser();
        fragmentForm = new FragmentForm();
        fragmentUserInfo = new FragmentUserInfo();

        fragmentManager = getSupportFragmentManager();

        android.support.v4.app.FragmentTransaction HomeTransaction = fragmentManager.beginTransaction();
        HomeTransaction.replace(R.id.mainFragmentContainer, fragmentHome);
        HomeTransaction.commit();
    }

    private void setupBottomNavigation() {
        bottomNavigationView = findViewById(R.id.mainActivity_BottomNavigation);
        changeBottomNavigationIconSize();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.bottom_navigation_home:

                        clearFragmentBackStack();

                        android.support.v4.app.FragmentTransaction HomeTransaction = fragmentManager.beginTransaction();
                        HomeTransaction.replace(R.id.mainFragmentContainer, fragmentHome);
                        HomeTransaction.commit();
                        progressBar.setVisibility(View.VISIBLE);
                        afterGetFromServer();


                        return true;

                    case R.id.bottom_navigation_form:

                        clearFragmentBackStack();

                        android.support.v4.app.FragmentTransaction FormTransaction = fragmentManager.beginTransaction();
                        FormTransaction.replace(R.id.mainFragmentContainer, fragmentForm);
                        FormTransaction.commit();
                        progressBar.setVisibility(View.GONE);


                        return true;

                    case R.id.bottom_navigation_userManagement:
                        clearFragmentBackStack();

                        if (sharedPrefManager.getUserInfo().getUsername().equals("") &&
                                sharedPrefManager.getUserInfo().getUsername().isEmpty())
                        {
                            android.support.v4.app.FragmentTransaction UserTransaction = fragmentManager.beginTransaction();
                            UserTransaction.replace(R.id.mainFragmentContainer, fragmentUser);
                            UserTransaction.commit();
                        }else {
                            FragmentTransaction UserInfoTransaction = fragmentManager.beginTransaction();
                            UserInfoTransaction.replace(R.id.mainFragmentContainer,fragmentUserInfo);
                            UserInfoTransaction.commit();

                        }

                        progressBar.setVisibility(View.GONE);

                        return true;
                }
                return false;
            }
        });
    }


    private void changeBottomNavigationIconSize() {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        for (int i = 0; i < menuView.getChildCount(); i++) {
            final View iconView = menuView.getChildAt(i).findViewById(android.support.design.R.id.icon);
            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            // set your height here
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 31, displayMetrics);
            // set your width here
            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 31, displayMetrics);
            iconView.setLayoutParams(layoutParams);
        }
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.mainToolbar);
        shopBtn = findViewById(R.id.activity_main_toolbar_shop_xml);
        searchBtn = findViewById(R.id.activity_main_toolbar_search_xml);


        shopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main_Activity.this, "Shop", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Main_Activity.this, Test.class));
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main_Activity.this, "Search", Toast.LENGTH_SHORT).show();
            }
        });


        DrawerLayout drawerLayout = findViewById(R.id.main_drawer_layout);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));


        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, 0, 0);
        drawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorPrimaryDark));
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        actionBar.setTitle(getResources().getString(R.string.company_name));

        for (int i = 0; i < toolbar.getChildCount(); i++) {
            if (toolbar.getChildAt(i) instanceof TextView) {
                ((TextView) toolbar.getChildAt(i)).setTypeface(MyApplication.getBYekanFont(this));
            }
        }


    }

    @SuppressLint("SetTextI18n")
    private void setUserInfoFromShPref() {

        userInfoImageBackground.setTag(target);

        setBlurBannerBackgroundDefault();

        String first_name = sharedPrefManager.getUserInfo().getFirstName();
        String last_name = sharedPrefManager.getUserInfo().getLastName();
        String image_url = sharedPrefManager.getUserInfo().getImage_url();
        String email = sharedPrefManager.getUserInfo().getEmail();
        String user_level = sharedPrefManager.getUserInfo().getCapacity();

        setButtonUserLevel(user_level);

        if (sharedPrefManager.getUserInfo().getUsername()!=null &&
                !sharedPrefManager.getUserInfo().getUsername().isEmpty()){

            if (first_name!=null && last_name!=null
                    && !first_name.isEmpty() && !last_name.isEmpty()){
                userInfoText.setText(first_name + " " + last_name);
                userImage.setBorderColor(ContextCompat.getColor(Main_Activity.this,R.color.colorPrimaryDark));

            }

            if (image_url!=null && !image_url.isEmpty()){
                Picasso.get().load(image_url).into(userImage);

                Picasso.get()
                        .load(image_url)
                        .error(R.drawable.avatar)
                        .placeholder(R.drawable.avatar)
                        .into(target);
            }



            if (email !=null && !email.isEmpty() ){
                userInfoEmail.setText(email);
            }





        }else {
            userInfoText.setText(R.string.userQuest);
            userInfoEmail.setVisibility(View.GONE);
            logOutBtn.setVisibility(View.GONE);
            userImage.setBorderColor(ContextCompat.getColor(Main_Activity.this,R.color.white));
        }


    }

    public void setButtonUserLevel(String user_level) {
        switch (user_level){

            case GUEST_LEVEL:
                userLevelBtn.setText("کاربر مهمان");
                break;

            case NORMAL_LEVEL:
                userLevelBtn.setText("تازه کار");
                break;

            case SUBSCRIBER_LEVEL:
                userLevelBtn.setText("تازه کار");
                break;

            case ADMINISTRATOR_LEVEL:
                userLevelBtn.setText("مدیر عامل");
                break;


            case AUTHOR_LEVEL:
                userLevelBtn.setText("نویسنده");
                break;


            case CONTRIBUTOR_LEVEL:
                userLevelBtn.setText("مشارکت کننده");
                break;


            case EDITOR_LEVEL:
                userLevelBtn.setText("ویرایشگر");
                break;


            case SHOP_MANAGER_LEVEL:
                userLevelBtn.setText("مدیر فروشگاه");
                break;

            case MARKETER_LEVEL:
                userLevelBtn.setText("بازاریاب");
                break;

            case SENIORMARKETER_LEVEL:
                userLevelBtn.setText("بازاریاب ارشد");
                break;


            default:
                userLevelBtn.setText("مهمان");

        }

    }


    private void setBlurBannerBackgroundDefault() {

        Picasso.get().load(R.drawable.avatar).into(userImage);

        Picasso.get()
                .load(R.drawable.avatar)
                .error(R.drawable.avatar)
                .placeholder(R.drawable.avatar)
                .into(target);
    }


    private void initViews() {
        bottomNavigationView = findViewById(R.id.mainActivity_BottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_navigation_home);
        progressBar = findViewById(R.id.activity_main_progressBar);
        drawerLayout = findViewById(R.id.main_drawer_layout);
        sharedPrefManager = new SharedPrefManager(this);
        progressBar.setVisibility(View.VISIBLE);


    }

    Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            userInfoImageBackground.setImageBitmap(BlurImage.fastblur(bitmap, 1f, BLUR_PERCENTAGE));
        }

        @Override
        public void onBitmapFailed(Exception e, Drawable errorDrawable) {
            userInfoImageBackground.setImageResource(R.mipmap.ic_launcher);
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };


    private void clearFragmentBackStack(){
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {

           fragmentManager.popBackStack();


        }
    }
}