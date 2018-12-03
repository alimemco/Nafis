package com.ali.rnp.nafis.view.adapter;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MainSliderAdapter extends SliderAdapter {
    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder imageSlideViewHolder) {

        switch (position){
            case 0:
            imageSlideViewHolder.bindImageSlide("http://hph.co.ir/data/upload/mirasmlm/slide/491786d7485a7a4c56f8f0a99e0690be1534758633.jpg");
            break;

            case 1:
                imageSlideViewHolder.bindImageSlide("http://hph.co.ir/data/upload/mirasmlm/slide/bc888e9d24a10a4d9692366e246203241533556434.jpg");
                break;

            case 2:
                imageSlideViewHolder.bindImageSlide("http://hph.co.ir/data/upload/mirasmlm/slide/0b0e30e39d7ff8c00498acc298afc64a1533557477.jpg");
                break;

            case 3:
                imageSlideViewHolder.bindImageSlide("http://hph.co.ir/data/upload/mirasmlm/slide/c5d59b673166e7056172f51872248c4e1541427849.jpg");
                break;

            case 4:
                imageSlideViewHolder.bindImageSlide("http://hph.co.ir/data/upload/mirasmlm/slide/cc1b367873c39051db23cdfa7a73ad941541427066.jpg");
                break;

            case 5:
                imageSlideViewHolder.bindImageSlide("http://hph.co.ir/data/upload/mirasmlm/slide/d1984e372bab36d414ab6e1d89d50ac91542719648.jpg");
                break;
        }

    }
}
