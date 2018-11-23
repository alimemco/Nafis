package com.ali.rnp.nafis.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ali.rnp.nafis.R;
import com.ali.rnp.nafis.view.DataModel.Category;
import com.ali.rnp.nafis.view.MyApplication;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<Category> categories;
    private static final int VIEW_TYPE_BANNER=0;
    private static final int VIEW_TYPE_DEFAULT=1;
    private static final String BANNER_IMAGE_LINK="http://hph.co.ir/data/upload/mirasmlm/slide/0b0e30e39d7ff8c00498acc298afc64a1533557477.jpg";

    public CategoryAdapter(Context context){
        this.context=context;
    }

    public void SetupCategoryAdapter(List<Category> categories){
        this.categories=categories;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return VIEW_TYPE_BANNER;
        }else {
            return VIEW_TYPE_DEFAULT;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType){
            case VIEW_TYPE_BANNER:
                View bannerView = LayoutInflater.from(context).inflate(R.layout.banner_main,null,false);
               RecyclerView.LayoutParams lpBanner = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                bannerView.setLayoutParams(lpBanner);

                return new BannerAdapterHolder(bannerView);

            case VIEW_TYPE_DEFAULT:
                View view = LayoutInflater.from(context).inflate(R.layout.category_item,null,false);
                RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                view.setLayoutParams(lp);
                return new HomeAdapterHolder(view);


                default:
                    return null;

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

       if (holder instanceof HomeAdapterHolder){
           HomeAdapterHolder homeAdapterHolder = (HomeAdapterHolder) holder;
           Category category = categories.get(position-1);
           homeAdapterHolder.nameCategory.setText(category.getName());
           homeAdapterHolder.descriptionCategory.setText(category.getDescription());
           Picasso.get().load(category.getImage()).into(homeAdapterHolder.imageCategory);
       }

    }

    @Override
    public int getItemCount() {
        return categories.size()+1;
    }

    public class HomeAdapterHolder extends RecyclerView.ViewHolder {
        private ImageView imageCategory;
        private TextView nameCategory;
        private TextView descriptionCategory;
        private HomeAdapterHolder(View itemView) {
            super(itemView);
            imageCategory=itemView.findViewById(R.id.category_item_image);
            nameCategory=itemView.findViewById(R.id.category_item_name);
            descriptionCategory =itemView.findViewById(R.id.category_item_description);

            nameCategory.setTypeface(MyApplication.getIranianSansFont(context));
            descriptionCategory.setTypeface(MyApplication.getIranianSansFont(context));
        }
    }

    public class BannerAdapterHolder extends RecyclerView.ViewHolder {
        private ImageView bannerImage;
        private TextView listText;
        private TextView newsText;
        private BannerAdapterHolder(View itemView) {
            super(itemView);
            bannerImage = itemView.findViewById(R.id.banner_main_image);
            listText = itemView.findViewById(R.id.banner_main_listText);
            newsText = itemView.findViewById(R.id.banner_main_news_text);
           Picasso.get().load(R.drawable.banner_holder).placeholder(R.drawable.banner_holder).into(bannerImage);
            listText.setTypeface(MyApplication.getIranianSansFont(context));
            newsText.setTypeface(MyApplication.getIranianSansFont(context));
        }
    }
}
