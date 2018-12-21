package com.ali.rnp.nafis.view.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ali.rnp.nafis.R;
import com.ali.rnp.nafis.view.DataModel.Category;
import com.ali.rnp.nafis.view.MyApplication;
import com.ali.rnp.nafis.view.activity.Main_Activity;
import com.ali.rnp.nafis.view.fragment.FragmentProductsCategory;
import com.ali.rnp.nafis.view.services.PicassoImageLoadingService;
import com.ali.rnp.nafis.view.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

import es.dmoral.toasty.Toasty;
import ss.com.bannerslider.Slider;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private Slider slider;
    private static final String TAG = "CategoryAdapter";


    private List<Category> categories;

    private static final int VIEW_TYPE_BANNER = 0;
    private static final int VIEW_TYPE_DEFAULT = 1;

    public CategoryAdapter(Context context) {
        this.context = context;
    }

    public void SetupCategoryAdapter(List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_BANNER;
        } else {
            return VIEW_TYPE_DEFAULT;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_BANNER:
                View bannerView = LayoutInflater.from(context).inflate(R.layout.banner_main, null, false);
                RecyclerView.LayoutParams lpBanner = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                bannerView.setLayoutParams(lpBanner);

                return new BannerAdapterHolder(bannerView);

            case VIEW_TYPE_DEFAULT:
                View view = LayoutInflater.from(context).inflate(R.layout.category_item, null, false);
                RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                view.setLayoutParams(lp);
                return new HomeAdapterHolder(view);


            default:
                return null;

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof HomeAdapterHolder) {

            HomeAdapterHolder homeAdapterHolder = (HomeAdapterHolder) holder;
            Category category = categories.get(position - 1);
            homeAdapterHolder.nameCategory.setText(category.getName());
            homeAdapterHolder.descriptionCategory.setText(category.getDescription());
            if (!category.getImage().isEmpty()
                    && !category.getImage().equals("")) {

                Picasso.get()
                        .load(category.getImage())
                        .placeholder(R.drawable.banner_category_holder)
                        .into(homeAdapterHolder.imageCategory);

            } else {
                Picasso.get()
                        .load(R.drawable.banner_category_holder)
                        .into(homeAdapterHolder.imageCategory);
            }

            homeAdapterHolder.bindCategoryItem(category);

        }


    }

    @Override
    public int getItemCount() {
        return categories.size() + 1;
    }

    public class HomeAdapterHolder extends RecyclerView.ViewHolder {
        private ImageView imageCategory;
        private TextView nameCategory;
        private TextView descriptionCategory;

        private HomeAdapterHolder(View itemView) {
            super(itemView);
            imageCategory = itemView.findViewById(R.id.category_item_image);
            nameCategory = itemView.findViewById(R.id.category_item_name);
            descriptionCategory = itemView.findViewById(R.id.category_item_description);

            nameCategory.setTypeface(MyApplication.getBYekanFont(context));
            descriptionCategory.setTypeface(MyApplication.getIranianSansFont(context));


        }

        public void bindCategoryItem(final Category category) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (Utils.checkConnection(context)) {
                        sendDataToFragmentProduct(category);
                    } else {
                        Toasty.info(context, context.getResources().getString(R.string.noInternet), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }

    private void sendDataToFragmentProduct(Category category) {
        FragmentManager fragmentManager = ((Main_Activity) context).getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentProduct = fragmentManager.beginTransaction();
        FragmentProductsCategory fragmentProductsCategory = new FragmentProductsCategory();
        Bundle bundle = new Bundle();
        bundle.putString("slug", category.getSlug());
        bundle.putString("imageUrl", category.getImage());
        bundle.putString("nameCategory", category.getName());
        fragmentProductsCategory.setArguments(bundle);
        fragmentProduct.replace(R.id.mainFragmentContainer, fragmentProductsCategory);
        fragmentProduct.addToBackStack("Category").commit();
    }

    public class BannerAdapterHolder extends RecyclerView.ViewHolder {

        private TextView listText;
        private TextView newsText;

        private BannerAdapterHolder(View itemView) {
            super(itemView);
            listText = itemView.findViewById(R.id.banner_category_name);
            newsText = itemView.findViewById(R.id.banner_main_news_text);
            listText.setTypeface(MyApplication.getIranianSansFont(context));
            newsText.setTypeface(MyApplication.getIranianSansFont(context));
            slider = itemView.findViewById(R.id.banner_category_image);
            Slider.init(new PicassoImageLoadingService(context));
            slider.setAdapter(new MainSliderAdapter());
            slider.setSelectedSlide(0);
        }
    }
}
