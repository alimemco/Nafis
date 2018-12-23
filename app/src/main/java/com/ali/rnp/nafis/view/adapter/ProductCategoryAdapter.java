package com.ali.rnp.nafis.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ali.rnp.nafis.R;
import com.ali.rnp.nafis.view.DataModel.ApiService;
import com.ali.rnp.nafis.view.DataModel.Product;
import com.ali.rnp.nafis.view.MyApplication;
import com.ali.rnp.nafis.view.activity.Main_Activity;
import com.ali.rnp.nafis.view.fragment.FragmentProductInfo;
import com.ali.rnp.nafis.view.fragment.FragmentProductsCategory;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryAdapter extends RecyclerView.Adapter<ProductCategoryAdapter.ProductCategoryHolder> {


    private int RECYCLER_PRODUCT_MODE = 2;

    public static final String KEY_PRODUCT_IMAGE="product_image";
    public static final String KEY_PRODUCT_TITLE="product_title";
    public static final String KEY_PRODUCT_PRICE="product_price";
    public static final String KEY_PRODUCT_REGULAR_PRICE="product_regular_price";
    public static final String KEY_PRODUCT_SHORT_DES="product_short_des";
    public static final String KEY_PRODUCT_DES="product_des";
    public static final String KEY_PRODUCT_SRC="product_src";
    public static final String KEY_PRODUCT_SRC_ONE="product_src_one";
    public static final String KEY_PRODUCT_SRC_TWO="product_src_two";
    public static final String KEY_PRODUCT_SRC_THREE="product_src_three";
    public static final String KEY_PRODUCT_SRC_FOUR="product_src_four";
    public static final String KEY_PRODUCT_SRC_FIVE="product_src_five";
    public static final String KEY_PRODUCT_LIST="product_list";

    private Context context;
    private List<Product> productList;
    private String imageUrlCategory;
    private String nameCategory;

    public ProductCategoryAdapter(Context context, String imageUrlCategory, String nameCategory) {
        this.context = context;
        this.imageUrlCategory = imageUrlCategory;
        this.nameCategory = nameCategory;
    }

    public ProductCategoryAdapter(Context context) {
        this.context = context;
    }

    public void SetupProductRecyclerView(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ProductCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.product_category_item, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new ProductCategoryHolder(view);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductCategoryHolder holder, int position) {

        Product product = productList.get(position);

        holder.bindProductCategoryForClick(product);

        holder.productName.setText(product.getTitle());

        if (product.getIn_stock()){

            holder.productRegularPrice.setVisibility(View.VISIBLE);
            holder.productPrice.setVisibility(View.VISIBLE);
            holder.not_in_Stock.setVisibility(View.GONE);

        }else {
            holder.productRegularPrice.setVisibility(View.INVISIBLE);
            holder.productPrice.setVisibility(View.INVISIBLE);
            holder.not_in_Stock.setVisibility(View.VISIBLE);
        }

        if (!product.getImg_src().equals("") &&
                !product.getImg_src().isEmpty()) {
            Picasso.get().load(product.getImg_src()).into(holder.productImage);
        }

        if (!product.getPrice().equals(product.getRegular_price())) {
            holder.productRegularPrice.setVisibility(View.VISIBLE);

            holder.productRegularPrice.setPaintFlags(holder.productRegularPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.productRegularPrice.setText(formatPrice(product.getRegular_price()) + "  تومان");
            holder.productPrice.setText(formatPrice(product.getPrice()) + "  تومان");
        } else {
            holder.productRegularPrice.setVisibility(View.INVISIBLE);

            holder.productPrice.setText(formatPrice(product.getPrice()) + "  تومان");
        }



    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public class ProductCategoryHolder extends RecyclerView.ViewHolder {
        private ImageView productImage;
        private TextView productName;
        private TextView productRegularPrice;
        private TextView productPrice;
        private TextView not_in_Stock;

        public ProductCategoryHolder(View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.product_category_item_image);
            productName = itemView.findViewById(R.id.product_category_item_name);
            productRegularPrice = itemView.findViewById(R.id.product_category_item_regular_price);
            productPrice = itemView.findViewById(R.id.product_category_item_price);
            not_in_Stock = itemView.findViewById(R.id.product_category_item_Stock);

            productName.setTypeface(MyApplication.getBYekanFont(context));
            productPrice.setTypeface(MyApplication.getBYekanFont(context));
            productRegularPrice.setTypeface(MyApplication.getBYekanFont(context));
            not_in_Stock.setTypeface(MyApplication.getBYekanFont(context));
        }


        public void bindProductCategoryForClick(final Product product) {


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentManager fragmentManager = ((Main_Activity) context).getSupportFragmentManager();
                    FragmentTransaction fragmentTransProductInfo = fragmentManager.beginTransaction();
                    FragmentProductInfo fragmentProductInfo = new FragmentProductInfo();

                    Bundle bundle = new Bundle();
                    bundle.putString(KEY_PRODUCT_IMAGE,product.getImg_src());
                    bundle.putString(KEY_PRODUCT_TITLE,product.getTitle());
                    bundle.putString(KEY_PRODUCT_PRICE,product.getPrice());
                    bundle.putString(KEY_PRODUCT_REGULAR_PRICE,product.getRegular_price());
                    bundle.putString(KEY_PRODUCT_SHORT_DES,product.getShort_description());
                    bundle.putString(KEY_PRODUCT_DES,product.getDescription());

                    bundle.putString(KEY_PRODUCT_SRC,product.getImg_src());
                    bundle.putString(KEY_PRODUCT_SRC_ONE,product.getImg_src_gallery_one());
                    bundle.putString(KEY_PRODUCT_SRC_TWO,product.getImg_src_gallery_two());
                    bundle.putString(KEY_PRODUCT_SRC_THREE,product.getImg_src_gallery_three());
                    bundle.putString(KEY_PRODUCT_SRC_FOUR,product.getImg_src_gallery_four());
                    bundle.putString(KEY_PRODUCT_SRC_FIVE,product.getImg_src_gallery_five());
                    /*
                    bundle.putSerializable(KEY_PRODUCT_LIST, (Serializable) productList);
                    bundle.putParcelable(KEY_PRODUCT_LIST, (Parcelable) new ArrayList<Product>(productList));
*/
                    fragmentProductInfo.setArguments(bundle);

                    fragmentTransProductInfo.replace(R.id.mainFragmentContainer,fragmentProductInfo);
                    fragmentTransProductInfo.addToBackStack("Product").commit();


                }
            });

        }
    }


    private String formatPrice(String price) {

        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String priceFormatted = formatter.format(Integer.parseInt(price));
        return priceFormatted;
    }
}
