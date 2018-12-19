package com.ali.rnp.nafis.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ali.rnp.nafis.R;
import com.ali.rnp.nafis.view.DataModel.ApiService;
import com.ali.rnp.nafis.view.DataModel.Product;
import com.ali.rnp.nafis.view.adapter.ProductCategoryAdapter;

import java.util.List;

public class FragmentProductsCategory extends Fragment {


    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private  ProductCategoryAdapter productCategoryAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View rootView = inflater.inflate(R.layout.fragment_product_category,null,false);

       iniViews(rootView);
       setupRecyclerView(rootView);

       if (getArguments()!=null){
           String slug = getArguments().getString("slug");

           ApiService apiService = new ApiService(getActivity());
           apiService.getProductByCategory(slug, new ApiService.onGetProductCategory() {
               @Override
               public void onProductReceived(List<Product> productList) {

                   if (productList!=null){
                       progressBar.setVisibility(View.GONE);
                       productCategoryAdapter.SetupProductRecyclerView(productList);
                       recyclerView.setAdapter(productCategoryAdapter);
                   }else {
                       progressBar.setVisibility(View.GONE);
                   }




               }
           });

       }

       return rootView;
    }

    private void setupRecyclerView(View rootView) {
        recyclerView = rootView.findViewById(R.id.fragment_product_category_recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        productCategoryAdapter = new ProductCategoryAdapter(getActivity());



    }

    private void iniViews(View rootView) {
       progressBar= rootView.findViewById(R.id.fragment_product_category_pb);

    }
}
