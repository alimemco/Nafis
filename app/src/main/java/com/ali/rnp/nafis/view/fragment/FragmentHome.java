package com.ali.rnp.nafis.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ali.rnp.nafis.R;
import com.ali.rnp.nafis.view.DataModel.ApiService;
import com.ali.rnp.nafis.view.DataModel.Category;
import com.ali.rnp.nafis.view.DataModel.DataGenrator;
import com.ali.rnp.nafis.view.DataModel.Question;
import com.ali.rnp.nafis.view.adapter.CategoryAdapter;
import com.ali.rnp.nafis.view.adapter.QuestionAdapter;
import com.ali.rnp.nafis.view.utils.Utils;

import java.util.List;

public class FragmentHome extends Fragment {

    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    private static final String TAG = "FragmentHome";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home,container,false);

        recyclerView = rootView.findViewById(R.id.fragment_home_recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        categoryAdapter = new CategoryAdapter(getContext());


        if (Utils.checkConnection(getContext())==true){
            getDataFromServer();
        }else {
            getDataFromLocal();
        }

        return rootView;
    }

    private void getDataFromLocal() {
        categoryAdapter.SetupCategoryAdapter(DataGenrator.getCategories());
        recyclerView.setAdapter(categoryAdapter);
    }

    private void getDataFromServer() {
        ApiService apiService = new ApiService(getContext());
        apiService.getCategoryFromServer(new ApiService.onGetCategories() {
            @Override
            public void onReceivedCategory(List<Category> categories) {
                if(categories!=null) {
                    categoryAdapter.SetupCategoryAdapter(categories);
                    recyclerView.setAdapter(categoryAdapter);

                }else {
                    Toast.makeText(getContext(), "ارتباط با سرور برقرار نشد", Toast.LENGTH_LONG).show();

                }

            }
        });
    }
}
