package com.ali.rnp.nafis.view;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

public class MyApplication extends Application {

    private static Typeface iranianSansFont;

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
}
