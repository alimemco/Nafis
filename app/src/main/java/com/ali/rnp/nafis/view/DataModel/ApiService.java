package com.ali.rnp.nafis.view.DataModel;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ApiService {
    private static final String TAG = "ApiService";
    private static final String categoryLinkApi="http://nafis-app.ir/apiService/api/categories.php";
    private Context context;
    public ApiService(Context context){
        this.context=context;
    }

    public void getCategoryFromServer(final onGetCategories onGetCategories){

        JsonRequest request = new JsonObjectRequest(Request.Method.GET, categoryLinkApi, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                parseCategoryJson(response,onGetCategories);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onGetCategories.onReceivedCategory(null);
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(8000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }

    private void parseCategoryJson(JSONObject response,onGetCategories onGetCategories) {

        List<Category> categories = new ArrayList<>();

        for (int i = 0; i < 7; i++) {

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

    public interface onGetCategories{
        void onReceivedCategory(List<Category> categories);
    }
}