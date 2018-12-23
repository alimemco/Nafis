package com.ali.rnp.nafis.view.adapter;

import java.util.List;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;


public class ProductSliderAdapter extends SliderAdapter {

    private List<String> imageUrls;

    public ProductSliderAdapter(List<String> imageUrls){
        this.imageUrls = imageUrls ;
    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder imageSlideViewHolder) {
        switch (position) {
            case 0:
                imageSlideViewHolder.bindImageSlide(imageUrls.get(0));
                break;
            case 1:
                imageSlideViewHolder.bindImageSlide(imageUrls.get(1));
                break;
            case 2:
                imageSlideViewHolder.bindImageSlide(imageUrls.get(2));
                break;

            case 3:
                imageSlideViewHolder.bindImageSlide(imageUrls.get(3));
                break;

            case 4:
                imageSlideViewHolder.bindImageSlide(imageUrls.get(4));
                break;
        }
    }
}
