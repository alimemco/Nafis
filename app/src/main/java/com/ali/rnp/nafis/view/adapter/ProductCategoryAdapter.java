package com.ali.rnp.nafis.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
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
import com.ali.rnp.nafis.view.fragment.FragmentProductsCategory;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class ProductCategoryAdapter extends RecyclerView.Adapter<ProductCategoryAdapter.ProductCategoryHolder> {

    private static final int VIEW_TYPE_BANNER = 0;
    private static final int VIEW_TYPE_DEFAULT = 1;

    private int RECYCLER_PRODUCT_MODE = 2;

    private Context context;
    private List<Product> productList;
    private String imageUrlCategory;
    private String nameCategory;

    public ProductCategoryAdapter(Context context, String imageUrlCategory, String nameCategory) {
        this.context = context;
        this.imageUrlCategory = imageUrlCategory;
        this.nameCategory = nameCategory;
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
            holder.productRegularPrice.setVisibility(View.GONE);
            holder.productPrice.setVisibility(View.GONE);
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

                    Log.i("ApiService", "onClick: "+product.getImg_src());
                    Log.i("ApiService", "onClick: "+product.getImg_src_gallery_one());
                    Log.i("ApiService", "onClick: "+product.getImg_src_gallery_two());
                    Log.i("ApiService", "onClick: "+product.getImg_src_gallery_three());
                    Log.i("ApiService", "onClick: "+product.getImg_src_gallery_four());
                    Log.i("ApiService", "onClick: "+product.getImg_src_gallery_five());


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
