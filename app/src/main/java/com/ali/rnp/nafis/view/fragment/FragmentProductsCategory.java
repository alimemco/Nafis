package com.ali.rnp.nafis.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ali.rnp.nafis.R;
import com.ali.rnp.nafis.view.DataModel.ApiService;
import com.ali.rnp.nafis.view.DataModel.Product;
import com.ali.rnp.nafis.view.MyApplication;
import com.ali.rnp.nafis.view.adapter.ProductCategoryAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FragmentProductsCategory extends Fragment {

    private String slug;
    private String imageUrlCategory;
    private String nameCategory;
    private TextView product_not_exists;
    private int RECYCLER_MODE = 1;

    private ImageView bannerImageCategory;
    private ImageView listCategoryImg;
    private TextView productCategoryTitle;

    private List<Product> productListFinal;

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ProductCategoryAdapter productCategoryAdapter;
    private GridLayoutManager gridLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product_category, null, false);

        iniViews(rootView);


        if (getArguments() != null) {
            slug = getArguments().getString("slug");
            imageUrlCategory = getArguments().getString("imageUrl");
            nameCategory = getArguments().getString("nameCategory");


            if (imageUrlCategory!=null && !imageUrlCategory.equals("") ){
                Picasso.get().load(imageUrlCategory).into(bannerImageCategory);
            }

            if (nameCategory!=null && !nameCategory.equals("")){

                productCategoryTitle.setText(nameCategory);
            }


            setupRecyclerView(1);

            ApiService apiService = new ApiService(getActivity());
            apiService.getProductByCategory(slug, new ApiService.onGetProductCategory() {
                @Override
                public void onProductReceived(List<Product> productList) {

                    progressBar.setVisibility(View.GONE);

                    if (productList != null) {

                        if (productList.size()>0){
                            productCategoryAdapter.SetupProductRecyclerView(productList);
                            recyclerView.setAdapter(productCategoryAdapter);

                            listCategoryImg.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    if (RECYCLER_MODE==1){
                                        setupRecyclerView(2);
                                        RECYCLER_MODE=2;
                                        Toast.makeText(getContext(), "2", Toast.LENGTH_SHORT).show();

                                    }else if (RECYCLER_MODE==2){
                                        setupRecyclerView(1);
                                        RECYCLER_MODE=1;
                                        Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                        }else {
                            product_not_exists.setVisibility(View.VISIBLE);

                        }


                    } else {


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

                    return spanProduct;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        productCategoryAdapter = new ProductCategoryAdapter(getActivity(), imageUrlCategory, nameCategory);


    }

    private void iniViews(View rootView) {
        progressBar = rootView.findViewById(R.id.fragment_product_category_pb);
        recyclerView = rootView.findViewById(R.id.fragment_product_category_recyclerView);

        bannerImageCategory = rootView.findViewById(R.id.fragment_product_category_banner_image);
        listCategoryImg = rootView.findViewById(R.id.fragment_product_category_banner_listMenu);
        productCategoryTitle = rootView.findViewById(R.id.fragment_product_category_banner_title);
        product_not_exists = rootView.findViewById(R.id.fragment_product_category_txt_notExistProduct);

        productCategoryTitle.setTypeface(MyApplication.getBYekanFont(getActivity()));
        product_not_exists.setTypeface(MyApplication.getBYekanFont(getActivity()));



        gridLayoutManager = new GridLayoutManager(getActivity(), 2);

    }
}
