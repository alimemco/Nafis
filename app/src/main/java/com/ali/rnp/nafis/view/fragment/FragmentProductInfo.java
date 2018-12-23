package com.ali.rnp.nafis.view.fragment;

import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.ali.rnp.nafis.R;
import com.ali.rnp.nafis.view.MyApplication;
import com.ali.rnp.nafis.view.adapter.ProductCategoryAdapter;
import com.ali.rnp.nafis.view.adapter.ProductSliderAdapter;
import com.ali.rnp.nafis.view.services.PicassoImageLoadingServiceProduct;
import com.ali.rnp.nafis.view.utils.Utils;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.Slider;


public class FragmentProductInfo extends Fragment implements AppBarLayout.OnOffsetChangedListener {

    private TextView titleProduct;
    private TextView priceProduct;
    private ImageView imageProduct;
    private TextView shortDesProduct;
    private TextView desProduct;
    private TextView regularPriceProduct;
    private AppBarLayout appBarLayout;

    private Slider slider;

    private String imgProductTxt;
    private String titleProductTxt;
    private String priceProductTxt;
    private String regularPriceProductTxt;
    private String shortDesProductTxt;
    private String desProductTxt;

    private ImageView backBtn;
    private ImageView shareBtn;
    private ImageView favBtn;
    private TextView title_toolbar;

    private RecyclerView recyclerView;
    private ProductCategoryAdapter productCategoryAdapter;


    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.8f;
    private static final int ALPHA_ANIMATIONS_DURATION = 200;

    private boolean mIsTheTitleVisible = false;
    private boolean mIsTheTitleContainerVisible = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_product_info, null, false);

        initViews(rootView);


        appBarLayout.addOnOffsetChangedListener(this);

        startAlphaAnimation(title_toolbar, 0, View.INVISIBLE);
        startAlphaAnimation(shareBtn, 0, View.INVISIBLE);


        if (getArguments() != null) {

            imgProductTxt = getArguments().getString(ProductCategoryAdapter.KEY_PRODUCT_IMAGE);
            titleProductTxt = getArguments().getString(ProductCategoryAdapter.KEY_PRODUCT_TITLE);
            priceProductTxt = getArguments().getString(ProductCategoryAdapter.KEY_PRODUCT_PRICE);
            regularPriceProductTxt = getArguments().getString(ProductCategoryAdapter.KEY_PRODUCT_REGULAR_PRICE);
            shortDesProductTxt = getArguments().getString(ProductCategoryAdapter.KEY_PRODUCT_SHORT_DES);
            desProductTxt = getArguments().getString(ProductCategoryAdapter.KEY_PRODUCT_DES);

            String imageSrc = getArguments().getString(ProductCategoryAdapter.KEY_PRODUCT_SRC);
            String imageSrcOne = getArguments().getString(ProductCategoryAdapter.KEY_PRODUCT_SRC_ONE);
            String imageSrcTwo = getArguments().getString(ProductCategoryAdapter.KEY_PRODUCT_SRC_TWO);
            String imageSrcThree = getArguments().getString(ProductCategoryAdapter.KEY_PRODUCT_SRC_THREE);
            String imageSrcFour = getArguments().getString(ProductCategoryAdapter.KEY_PRODUCT_SRC_FOUR);
            String imageSrcFive = getArguments().getString(ProductCategoryAdapter.KEY_PRODUCT_SRC_FIVE);

            Picasso.get().load(imgProductTxt).into(imageProduct);

            titleProduct.setText(titleProductTxt);
            priceProduct.setText(Utils.formatPrice(priceProductTxt));
            title_toolbar.setText(titleProductTxt);
            regularPriceProduct.setText(Utils.formatPrice(regularPriceProductTxt));
            regularPriceProduct.setPaintFlags(regularPriceProduct.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            //shortDesProduct.setText(shortDesProductTxt);
            desProduct.setText(desProductTxt);

            List<String> imageUrls = new ArrayList<>();

            if (imageSrc != null && !imageSrc.equals("")) {
                imageUrls.add(imageSrc);
            }

            if (imageSrcOne != null && !imageSrcOne.equals("")) {
                imageUrls.add(imageSrcOne);
            }

            if (imageSrcTwo != null && !imageSrcTwo.equals("")) {
                imageUrls.add(imageSrcTwo);
            }

            if (imageSrcThree != null && !imageSrcThree.equals("")) {
                imageUrls.add(imageSrcThree);
            }

            if (imageSrcFour != null && !imageSrcFour.equals("")) {
                imageUrls.add(imageSrcFour);
            }

            if (imageSrcFive != null && !imageSrcFive.equals("")) {
                imageUrls.add(imageSrcFive);
            }


            slider = rootView.findViewById(R.id.fragment_product_info_slider);

            PicassoImageLoadingServiceProduct picassoImageLoadingServiceProduct = new PicassoImageLoadingServiceProduct(getActivity());

            Slider.init(picassoImageLoadingServiceProduct);

            ProductSliderAdapter productSliderAdapter = new ProductSliderAdapter(imageUrls);

            slider.setAdapter(productSliderAdapter);
            slider.setSelectedSlide(0);

            setupRecyclerView();

        }

        return rootView;
    }

    private void setupRecyclerView() {

        productCategoryAdapter = new ProductCategoryAdapter(getActivity());
       // productCategoryAdapter.SetupProductRecyclerView();

    }

    private void initViews(View rootView) {

        titleProduct = rootView.findViewById(R.id.fragment_product_info_title);
        priceProduct = rootView.findViewById(R.id.fragment_product_info_price);
        regularPriceProduct = rootView.findViewById(R.id.fragment_product_info_regular_price);
        shortDesProduct = rootView.findViewById(R.id.fragment_product_info_short_des);
        desProduct = rootView.findViewById(R.id.fragment_product_info_des);
        imageProduct = rootView.findViewById(R.id.fragment_product_info_imgProduct);
        appBarLayout = rootView.findViewById(R.id.fragment_product_info_appbar);
        backBtn = rootView.findViewById(R.id.fragment_product_info_back);
        shareBtn = rootView.findViewById(R.id.fragment_product_info_share);
        favBtn = rootView.findViewById(R.id.fragment_product_info_fav);
        title_toolbar = rootView.findViewById(R.id.fragment_product_info_toolbar_name);
        recyclerView = rootView.findViewById(R.id.fragment_product_info_recyclerView);

        title_toolbar.setTypeface(MyApplication.getBYekanFont(getActivity()));
        titleProduct.setTypeface(MyApplication.getBYekanFont(getActivity()));
        priceProduct.setTypeface(MyApplication.getBYekanFont(getActivity()));
        regularPriceProduct.setTypeface(MyApplication.getBYekanFont(getActivity()));
        desProduct.setTypeface(MyApplication.getBYekanFont(getActivity()));


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

            if (!mIsTheTitleVisible) {
                startAlphaAnimation(title_toolbar, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                startAlphaAnimation(shareBtn, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }

        } else {


            if (mIsTheTitleVisible) {
                startAlphaAnimation(title_toolbar, ALPHA_ANIMATIONS_DURATION, View.GONE);
                startAlphaAnimation(shareBtn, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {

            if (mIsTheTitleContainerVisible) {
                startAlphaAnimation(titleProduct, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);

                //user_name_toolbar.setTextColor(ResourcesCompat.getColor(getResources(),R.color.red400,null));


                mIsTheTitleContainerVisible = false;
            }

        } else {


            if (!mIsTheTitleContainerVisible) {
                 startAlphaAnimation(titleProduct, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);

                // user_name_toolbar.setTextColor(ResourcesCompat.getColor(getResources(),R.color.red500,null));


                mIsTheTitleContainerVisible = true;
            }
        }
    }

    public static void startAlphaAnimation(View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }


}
