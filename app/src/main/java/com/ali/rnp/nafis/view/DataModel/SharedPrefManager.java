package com.ali.rnp.nafis.view.DataModel;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    private static final String SharedPrefManName="user_sharedPref";

    public static final String USERNAME_KEY="username";
    public static final String EMAIL_KEY="email";
    public static final String FIRST_NAME_KEY="first_name";
    public static final String LAST_NAME_KEY="last_name";
    public static final String IMAGE_URL_KEY="image_url";
    public static final String USER_LEVEL="user_level";

    private SharedPreferences sharedPreferences;

    public SharedPrefManager(Context context){
        sharedPreferences = context.getSharedPreferences(SharedPrefManName,Context.MODE_PRIVATE);
    }

    public void SaveUserInfo(User user){

        if (user!=null){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(USERNAME_KEY,user.getUsername());
            editor.putString(EMAIL_KEY,user.getEmail());
            editor.putString(FIRST_NAME_KEY,user.getFirstName());
            editor.putString(LAST_NAME_KEY,user.getLastName());
            editor.putString(IMAGE_URL_KEY,user.getImage_url());
            editor.putString(USER_LEVEL,user.getCapacity());
            editor.apply();
        }

    }

    public User getUserInfo(){
        User user = new User();

        user.setUsername(sharedPreferences.getString(USERNAME_KEY,""));
        user.setEmail(sharedPreferences.getString(EMAIL_KEY,""));
        user.setFirstName(sharedPreferences.getString(FIRST_NAME_KEY,""));
        user.setLastName(sharedPreferences.getString(LAST_NAME_KEY,""));
        user.setImage_url(sharedPreferences.getString(IMAGE_URL_KEY,""));
        user.setCapacity(sharedPreferences.getString(USER_LEVEL,"{\"guest\":true}"));

        return user;
    }
}
