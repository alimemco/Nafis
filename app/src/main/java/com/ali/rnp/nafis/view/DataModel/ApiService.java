package com.ali.rnp.nafis.view.DataModel;

import android.content.Context;
import android.util.Log;

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
    private static final String categoryLinkApi = "http://nafis-app.ir/apiService/api/categories.php";
    private static final String loginUserLinkApi = "http://nafis-app.ir/apiService/api/user/userLogin.php";
    private static final String registerUserLinkApi = "http://nafis-app.ir/apiService/api/user/userRegister.php";
    private static final String userInfoLinkApi = "http://nafis-app.ir/apiService/api/user/userInfo.php";
    private static final String productCategoryLinkApi = "http://nafis-app.ir/apiService/api/product_category.php";
    private static final String productLinkApi = "http://nafis-app.ir/apiService/api/product.php";
    private int timeOut = 10000;
    private Context context;

    private static final String TAG = "ApiService";

    public ApiService(Context context) {
        this.context = context;
    }

    public void getCategoryFromServer(final onGetCategories onGetCategories) {

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, categoryLinkApi, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                parseCategoryJson(response, onGetCategories);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onGetCategories.onReceivedCategory(null);


            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(timeOut, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);


    }

    public void loginUser(JSONObject jsonObject, final onLoginUserReceived onLoginUserReceived) {
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

        request.setRetryPolicy(new DefaultRetryPolicy(timeOut, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }

    public void registerUser(JSONObject jsonObject, final onRegisterUserReceived onRegisterUserReceived) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, registerUserLinkApi, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    onRegisterUserReceived.onRegisterUser(response.getInt("status"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onRegisterUserReceived.onRegisterUser(404);
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(timeOut, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }

    public void UserInfo(String username, final onUserInfoReceived onUserInfoReceived) {

        JSONObject jsonObjectUser = new JSONObject();
        try {
            jsonObjectUser.put("username", username);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, userInfoLinkApi, jsonObjectUser, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                parseUserInfoJson(response, onUserInfoReceived);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onUserInfoReceived.onInfoReceived(null);
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(timeOut, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);

    }

    public void getProductByCategory(String Slug, final onGetProductCategory onGetProductCategory) {

        JSONObject jsonObjectCategorySlug = new JSONObject();

        try {
            jsonObjectCategorySlug.put("category_slug", Slug);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, productCategoryLinkApi, jsonObjectCategorySlug, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                parseProductCategory(response, onGetProductCategory);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(timeOut, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);


    }

    public void getProductById(int id, final onGetProduct onGetProduct) {

        JSONObject jsonObjectProductId = new JSONObject();

        try {
            jsonObjectProductId.put("product_id", id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, productLinkApi, jsonObjectProductId, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                parseProduct(response, onGetProduct);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(timeOut, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);


    }

    private void parseProductCategory(JSONObject response, onGetProductCategory onGetProductCategory) {
        List<Product> productList = new ArrayList<>();

        try {
            JSONObject jsonObjectProductCat = new JSONObject(response.toString());
            JSONArray jsonArrayProduct = jsonObjectProductCat.getJSONArray("products");

            for (int i = 0; i < jsonArrayProduct.length(); i++) {
                Product product = new Product();

                JSONObject jsonObjectProduct = jsonArrayProduct.getJSONObject(i);

                product.setId(jsonObjectProduct.getInt("id"));
                product.setTitle(jsonObjectProduct.getString("title"));
                product.setDescription(jsonObjectProduct.getString("description"));
                product.setShort_description(jsonObjectProduct.getString("short_description"));
                product.setStatus(jsonObjectProduct.getString("status"));
                product.setPrice(jsonObjectProduct.getString("price"));
                product.setRegular_price(jsonObjectProduct.getString("regular_price"));
                product.setIn_stock(jsonObjectProduct.getBoolean("in_stock"));

                JSONArray jsonArrayCategoryName = jsonObjectProduct.getJSONArray("categories");
                product.setCategories(jsonArrayCategoryName.getString(0));
/*
                JSONArray jsonArrayImages = jsonObjectProduct.getJSONArray("images");
                JSONObject jsonObjectImages = jsonArrayImages.getJSONObject(0);
                product.setImg_src(jsonObjectImages.getString("src"));
*/
                JSONArray jsonArrayImagesGallery = jsonObjectProduct.getJSONArray("images");

                for (int j = 0; j < jsonArrayImagesGallery.length(); j++) {

                    JSONObject jsonObjectImagesGallery = jsonArrayImagesGallery.getJSONObject(j);

                    switch (j) {

                        case 0:
                            product.setImg_src(jsonObjectImagesGallery.getString("src"));
                            break;

                        case 1:
                            product.setImg_src_gallery_one(jsonObjectImagesGallery.getString("src"));
                            break;
                        case 2:
                            product.setImg_src_gallery_two(jsonObjectImagesGallery.getString("src"));
                            break;
                        case 3:
                            product.setImg_src_gallery_three(jsonObjectImagesGallery.getString("src"));
                            break;
                        case 4:
                            product.setImg_src_gallery_four(jsonObjectImagesGallery.getString("src"));
                            break;
                        case 5:
                            product.setImg_src_gallery_five(jsonObjectImagesGallery.getString("src"));
                            break;
                    }


                }



                productList.add(product);

            }

            onGetProductCategory.onProductReceived(productList);

        } catch (JSONException e) {
            e.printStackTrace();
            onGetProductCategory.onProductReceived(null);
        }
    }

    private void parseProduct(JSONObject response, onGetProduct onGetProduct) {

        try {
            JSONObject jsonObjectProductResponse = new JSONObject(response.toString());
            JSONObject jsonObjectProduct = jsonObjectProductResponse.getJSONObject("product");
            JSONArray jsonArrayImagesGallery = jsonObjectProduct.getJSONArray("images");

            Product product = new Product();

            for (int j = 0; j < jsonArrayImagesGallery.length(); j++) {

                JSONObject jsonObjectImagesGallery = jsonArrayImagesGallery.getJSONObject(j);

                switch (j) {
                    case 1:
                        product.setImg_src_gallery_one(jsonObjectImagesGallery.getString("src"));
                        Log.i(TAG, "galleryImages: " + jsonObjectImagesGallery.getString("src"));

                    case 2:
                        product.setImg_src_gallery_two(jsonObjectImagesGallery.getString("src"));
                        Log.i(TAG, "galleryImages: " + jsonObjectImagesGallery.getString("src"));


                    case 3:
                        product.setImg_src_gallery_three(jsonObjectImagesGallery.getString("src"));
                        Log.i(TAG, "galleryImages: " + jsonObjectImagesGallery.getString("src"));


                    case 4:
                        product.setImg_src_gallery_four(jsonObjectImagesGallery.getString("src"));
                        Log.i(TAG, "galleryImages: " + jsonObjectImagesGallery.getString("src"));


                    case 5:
                        product.setImg_src_gallery_five(jsonObjectImagesGallery.getString("src"));
                        Log.i(TAG, "galleryImages: " + jsonObjectImagesGallery.getString("src"));


                }


            }


            onGetProduct.onProductReceived(product);

        } catch (JSONException e) {
            e.printStackTrace();
            onGetProduct.onProductReceived(null);

        }
    }

    private void parseUserInfoJson(JSONObject response, onUserInfoReceived onUserInfoReceived) {

        User user = new User();

        try {
            JSONObject jsonObject = response.getJSONObject("userInfo");
            user.setUsername(jsonObject.getString("user_login"));
            user.setEmail(jsonObject.getString("user_email"));
            user.setFirstName(jsonObject.getString("first_name"));
            user.setLastName(jsonObject.getString("last_name"));
            user.setCapacity(jsonObject.getString("wp_capabilities"));
            user.setImage_url(jsonObject.getString("user_url"));

            onUserInfoReceived.onInfoReceived(user);


        } catch (JSONException e) {
            e.printStackTrace();
            onUserInfoReceived.onInfoReceived(null);
        }

    }

    private void parseCategoryJson(JSONObject response, onGetCategories onGetCategories) {

        List<Category> categories = new ArrayList<>();

        try {
            JSONArray jsonArrayCategory = response.getJSONArray("product_categories");
            for (int i = 0; i < jsonArrayCategory.length(); i++) {
                Category category = new Category();

                JSONObject jsonObject = jsonArrayCategory.getJSONObject(i);
                category.setId(jsonObject.getInt("id"));
                category.setName(jsonObject.getString("name"));

                category.setSlug(jsonObject.getString("slug"));
                category.setDescription(jsonObject.getString("description"));
                category.setImage(jsonObject.getString("image"));
                category.setCount(jsonObject.getString("count"));
                categories.add(category);
            }

            onGetCategories.onReceivedCategory(categories);

        } catch (JSONException e) {
            onGetCategories.onReceivedCategory(null);

        }


    }


    public interface onGetCategories {
        void onReceivedCategory(List<Category> categories);
    }

    public interface onLoginUserReceived {
        void onLoginUser(int status);
    }

    public interface onRegisterUserReceived {
        void onRegisterUser(int status);
    }

    public interface onUserInfoReceived {
        void onInfoReceived(User user);
    }

    public interface onGetProductCategory {
        void onProductReceived(List<Product> productList);
    }

    public interface onGetProduct {
        void onProductReceived(Product product);
    }
}