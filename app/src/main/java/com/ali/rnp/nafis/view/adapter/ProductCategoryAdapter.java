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

import com.ali.rnp.nafis.R;
import com.ali.rnp.nafis.view.DataModel.Category;
import com.ali.rnp.nafis.view.DataModel.Product;
import com.ali.rnp.nafis.view.MyApplication;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class ProductCategoryAdapter extends RecyclerView.Adapter<ProductCategoryAdapter.ProductCategoryHolder>{

    Context context;
    List<Product> productList;

    public ProductCategoryAdapter (Context context){
        this.context = context;
    }

    public void SetupProductRecyclerView (List<Product> productList){
        this.productList = productList;
        notifyDataSetChanged();

    }

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
            holder.productRegularPrice.setVisibility(View.GONE);

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
        }
    }

    private String formatPrice(String price ){

        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String priceFormatted = formatter.format(Integer.parseInt(price));
        return priceFormatted;
    }
}
