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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ali.rnp.nafis.R;
import com.ali.rnp.nafis.view.DataModel.ApiService;
import com.ali.rnp.nafis.view.DataModel.Product;
import com.ali.rnp.nafis.view.adapter.ProductCategoryAdapter;

import java.util.List;

public class FragmentProductsCategory extends Fragment {

    private String slug;
    private String imageUrlCategory;
    private String nameCategory;
    private ImageView listCategoryImg;
    private int RECYCLER_MODE=1;

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private  ProductCategoryAdapter productCategoryAdapter;
    private GridLayoutManager gridLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View rootView = inflater.inflate(R.layout.fragment_product_category,null,false);

       iniViews(rootView);


       if (getArguments()!=null){
           slug = getArguments().getString("slug");
           imageUrlCategory = getArguments().getString("imageUrl");
           nameCategory = getArguments().getString("nameCategory");

           setupRecyclerView(1);

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

    public void setupRecyclerView(final int spanProduct) {


        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position==0){
                    return 2;
                }else {
                    return spanProduct;
                }

            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        productCategoryAdapter = new ProductCategoryAdapter(getActivity(),imageUrlCategory,nameCategory);



    }

    private void iniViews(View rootView) {
       progressBar= rootView.findViewById(R.id.fragment_product_category_pb);
      listCategoryImg = rootView.findViewById(R.id.banner_product_category_list);
        recyclerView = rootView.findViewById(R.id.fragment_product_category_recyclerView);

        gridLayoutManager = new GridLayoutManager(getActivity(),2);

    }
}
