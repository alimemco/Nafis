package com.ali.rnp.nafis.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ali.rnp.nafis.R;
import com.ali.rnp.nafis.view.DataModel.Product;
import com.ali.rnp.nafis.view.MyApplication;
import com.ali.rnp.nafis.view.fragment.FragmentProductsCategory;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class ProductCategoryAdapter extends RecyclerView.Adapter<ProductCategoryAdapter.ProductCategoryHolder>{

    private static final int VIEW_TYPE_BANNER=0;
    private static final int VIEW_TYPE_DEFAULT=1;

    private int RECYCLER_PRODUCT_MODE=2;

    private Context context;
    private List<Product> productList;
    private String imageUrlCategory;
    private String nameCategory;

    public ProductCategoryAdapter (Context context,String imageUrlCategory,String nameCategory){
        this.context = context;
        this.imageUrlCategory = imageUrlCategory;
        this.nameCategory = nameCategory;
    }

    public void SetupProductRecyclerView (List<Product> productList){
        this.productList = productList;
        notifyDataSetChanged();

    }
/*
    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return VIEW_TYPE_BANNER;
        }else {
            return VIEW_TYPE_DEFAULT;
        }
    }
*/
    @NonNull
    @Override
    public ProductCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(context).inflate(R.layout.product_category_item,null,false);
                RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                view.setLayoutParams(lp);
                return new ProductCategoryHolder(view);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductCategoryHolder holder, int position) {

            Product product = productList.get(position);

            holder.productName.setText(product.getTitle());

            if (!product.getImg_src().equals("") &&
                    !product.getImg_src().isEmpty()) {
                Picasso.get().load(product.getImg_src()).into(holder.productImage);
            }

            if (!product.getPrice().equals(product.getRegular_price())){
                holder.productRegularPrice.setVisibility(View.VISIBLE);

                holder.productRegularPrice.setPaintFlags(holder.productRegularPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                holder.productRegularPrice.setText(formatPrice(product.getRegular_price())+"  تومان"  );
                holder.productPrice.setText(formatPrice(product.getPrice())+"  تومان");
            }else {
                holder.productRegularPrice.setVisibility(View.INVISIBLE);

                holder.productPrice.setText(formatPrice(product.getPrice())+"  تومان");
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

        public ProductCategoryHolder(View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.product_category_item_image);
            productName = itemView.findViewById(R.id.product_category_item_name);
            productRegularPrice = itemView.findViewById(R.id.product_category_item_regular_price);
            productPrice = itemView.findViewById(R.id.product_category_item_price);

            productName.setTypeface(MyApplication.getBYekanFont(context));
            productPrice.setTypeface(MyApplication.getBYekanFont(context));
            productRegularPrice.setTypeface(MyApplication.getBYekanFont(context));
        }
    }
/*
    public class BannerCategoryHolder extends RecyclerView.ViewHolder {
        private ImageView banner_image;
        private ImageView list_products;
        private TextView categoryTitle;
        public BannerCategoryHolder(View itemView) {
            super(itemView);
            banner_image = itemView.findViewById(R.id.banner_product_category_image);
            list_products = itemView.findViewById(R.id.banner_product_category_list);
            categoryTitle = itemView.findViewById(R.id.banner_product_category_name);

            categoryTitle.setText(nameCategory);
            categoryTitle.setTypeface(MyApplication.getBYekanFont(context));

            if (imageUrlCategory!=null && !imageUrlCategory.equals("")){
                Picasso.get().load(imageUrlCategory).into(banner_image);

            }

            list_products.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
                    FragmentProductsCategory fragmentProductsCategory = new FragmentProductsCategory();

                }
            });


        }
    }
    */

    private String formatPrice(String price ){

        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String priceFormatted = formatter.format(Integer.parseInt(price));
        return priceFormatted;
    }
}
