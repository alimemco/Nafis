package com.ali.rnp.nafis.view.DataModel;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ApiService {
    private static final String TAG = "ApiService";
    private static final String categoryLinkApi="http://nafis-app.ir/apiService/api/categories.php";
    private static final String loginUserLinkApi="http://nafis-app.ir/apiService/api/user/userLogin.php";
    private int timeOut=10000;
    private Context context;
    private int responseLength =0;

    public ApiService(Context context){
        this.context=context;
    }

    public void getCategoryFromServer(final onGetCategories onGetCategories){

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, categoryLinkApi, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                responseLength(response);
                parseCategoryJson(response,onGetCategories);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onGetCategories.onReceivedCategory(null);
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(timeOut,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }


    public void loginUser(JSONObject jsonObject, final onLoginUserReceived onLoginUserReceived){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, loginUserLinkApi, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    onLoginUserReceived.onLoginUser(response.getInt("status"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onLoginUserReceived.onLoginUser(404);
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(timeOut,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }

    private void parseCategoryJson(JSONObject response,onGetCategories onGetCategories) {

        List<Category> categories = new ArrayList<>();

        for (int i = 0; i < responseLength; i++) {

            Category category = new Category();
            try {
                JSONArray jsonArray = response.getJSONArray("product_categories");
                JSONObject jsonObject =jsonArray.getJSONObject(i);
                category.setId(jsonObject.getInt("id"));
                category.setName(jsonObject.getString("name"));
                category.setSlug(jsonObject.getString("slug"));
                category.setDescription(jsonObject.getString("description"));
                category.setImage(jsonObject.getString("image"));
                category.setCount(jsonObject.getString("count"));
                categories.add(category);

            } catch (JSONException e) {

            }
        }
        onGetCategories.onReceivedCategory(categories);

    }

    private void responseLength(JSONObject response){
        String responseString = response.toString();
        for (int i = 0; i < responseString.length(); i++) {
            if (responseString.charAt(i)=='{'){
                responseLength++;
            }
        }
        responseLength--;
    }

    public interface onGetCategories{
        void onReceivedCategory(List<Category> categories);
    }

    public interface onLoginUserReceived {
        void onLoginUser(int status);
    }
}