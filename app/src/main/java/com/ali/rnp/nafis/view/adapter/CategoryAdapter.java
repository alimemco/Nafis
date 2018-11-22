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
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.HomeAdapterHolder>{

    private Context context;
    private List<Category> categories;

    public CategoryAdapter(Context context){
        this.context=context;
    }

    public void SetupCategoryAdapter(List<Category> categories){
        this.categories=categories;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_item,null,false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);

        return new HomeAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapterHolder holder, int position) {

        Category category = categories.get(position);
        holder.nameCategory.setText(category.getName());
        holder.description.setText(category.getDescription());
        Picasso.get().load(category.getImage()).into(holder.imageCategory);

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class HomeAdapterHolder extends RecyclerView.ViewHolder {
        private ImageView imageCategory;
        private TextView nameCategory;
        private TextView description;
        public HomeAdapterHolder(View itemView) {
            super(itemView);
            imageCategory=itemView.findViewById(R.id.category_item_image);
            nameCategory=itemView.findViewById(R.id.category_item_name);
            description =itemView.findViewById(R.id.category_item_description);
        }
    }
}
