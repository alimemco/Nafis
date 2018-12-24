package com.ali.rnp.nafis.view;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;


public class MyApplication extends Application {

    private static Typeface iranianSansFont;
    private static Typeface bYekanFont;
    private static Typeface iranianSansFontBold;
    private static Typeface IRAN_Sans;
    private static Typeface IRAN_Sans_Bold;
    private static Typeface IRAN_Sans_Mobile;
    private static Typeface IRAN_Sans_Mobile_Bold;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public static Typeface getIranianSansFont(Context context){
        if (iranianSansFont==null){
            iranianSansFont=Typeface.createFromAsset(context.getAssets(), "fonts/iranSans.ttf");
        }
        return iranianSansFont;
    }

    public static Typeface getBYekanFont(Context context) {
        if(bYekanFont==null){
            bYekanFont=Typeface.createFromAsset(context.getAssets(), "fonts/byekan.ttf");
        }
        return bYekanFont;
    }

    public static Typeface getIranianSansBoldFont(Context context) {
        if(iranianSansFontBold==null){
            iranianSansFontBold=Typeface.createFromAsset(context.getAssets(), "fonts/iranSansBold.ttf");
        }
        return iranianSansFontBold;
    }

    public static Typeface getIranSansOriginalFont(Context context){

        if (IRAN_Sans==null){
            IRAN_Sans = Typeface.createFromAsset(context.getAssets(),"fonts/IRAN Sans.ttf");
        }
        return IRAN_Sans;
    }


    public static Typeface getIranSansBoldOriginalFont(Context context){

        if (IRAN_Sans_Bold==null){
            IRAN_Sans_Bold = Typeface.createFromAsset(context.getAssets(),"fonts/IRAN Sans Bold.ttf");
        }
        return IRAN_Sans_Bold;
    }


    public static Typeface getIranSansBoldMobileOriginalFont(Context context){

        if (IRAN_Sans_Mobile==null){
            IRAN_Sans_Mobile = Typeface.createFromAsset(context.getAssets(),"fonts/IRANSansMobile.ttf");
        }
        return IRAN_Sans_Mobile;
    }

    public static Typeface getIranSansBoldMobileBoldOriginalFont(Context context){

        if (IRAN_Sans_Mobile_Bold==null){
            IRAN_Sans_Mobile_Bold = Typeface.createFromAsset(context.getAssets(),"fonts/IRANSansMobile_Bold.ttf");
        }
        return IRAN_Sans_Mobile_Bold;
    }
}
