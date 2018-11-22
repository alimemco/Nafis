package com.ali.rnp.nafis.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ali.rnp.nafis.R;
import com.ali.rnp.nafis.view.DataModel.ApiService;
import com.ali.rnp.nafis.view.DataModel.Category;
import com.ali.rnp.nafis.view.adapter.CategoryAdapter;

import java.util.List;

public class Test extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    private static final String TAG = "Test";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        recyclerView = findViewById(R.id.testRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        categoryAdapter = new CategoryAdapter(this);

        ApiService apiService = new ApiService(this);
        apiService.getCategoryFromServer(new ApiService.onGetCategories() {
            @Override
            public void onReceivedCategory(List<Category> categories) {
                Log.i(TAG, "onReceivedCategory: before");

                if(categories!=null) {
                    categoryAdapter.SetupCategoryAdapter(categories);

                    recyclerView.setAdapter(categoryAdapter);
                    Log.i(TAG, "onReceivedCategory: ");
                }else {
                    Log.i(TAG, "onReceivedCategory: error");
                }

            }
        });
    }
}
