package com.ali.rnp.nafis.view.DataModel;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {

    private int id;
    private String title;
    private String status;
    private String price;
    private String regular_price;
    private String description;
    private String short_description;
    private String categories;
    private Boolean in_stock;
    private String img_src;
    private String img_src_gallery_one;
    private String img_src_gallery_two;
    private String img_src_gallery_three;
    private String img_src_gallery_four;
    private String img_src_gallery_five;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRegular_price() {
        return regular_price;
    }

    public void setRegular_price(String regular_price) {
        this.regular_price = regular_price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getImg_src() {
        return img_src;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }

    public Boolean getIn_stock() {
        return in_stock;
    }

    public void setIn_stock(Boolean in_stock) {
        this.in_stock = in_stock;
    }

    public String getImg_src_gallery_one() {
        return img_src_gallery_one;
    }

    public void setImg_src_gallery_one(String img_src_gallery_one) {
        this.img_src_gallery_one = img_src_gallery_one;
    }

    public String getImg_src_gallery_two() {
        return img_src_gallery_two;
    }

    public void setImg_src_gallery_two(String img_src_gallery_two) {
        this.img_src_gallery_two = img_src_gallery_two;
    }

    public String getImg_src_gallery_three() {
        return img_src_gallery_three;
    }

    public void setImg_src_gallery_three(String img_src_gallery_three) {
        this.img_src_gallery_three = img_src_gallery_three;
    }

    public String getImg_src_gallery_four() {
        return img_src_gallery_four;
    }

    public void setImg_src_gallery_four(String img_src_gallery_four) {
        this.img_src_gallery_four = img_src_gallery_four;
    }

    public String getImg_src_gallery_five() {
        return img_src_gallery_five;
    }

    public void setImg_src_gallery_five(String img_src_gallery_five) {
        this.img_src_gallery_five = img_src_gallery_five;
    }

    public Product(){

    }

    protected Product(Parcel in) {
        id = in.readInt();
        title = in.readString();
        status = in.readString();
        price = in.readString();
        regular_price = in.readString();
        description = in.readString();
        short_description = in.readString();
        categories = in.readString();
        byte in_stockVal = in.readByte();
        in_stock = in_stockVal == 0x02 ? null : in_stockVal != 0x00;
        img_src = in.readString();
        img_src_gallery_one = in.readString();
        img_src_gallery_two = in.readString();
        img_src_gallery_three = in.readString();
        img_src_gallery_four = in.readString();
        img_src_gallery_five = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(status);
        dest.writeString(price);
        dest.writeString(regular_price);
        dest.writeString(description);
        dest.writeString(short_description);
        dest.writeString(categories);
        if (in_stock == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (in_stock ? 0x01 : 0x00));
        }
        dest.writeString(img_src);
        dest.writeString(img_src_gallery_one);
        dest.writeString(img_src_gallery_two);
        dest.writeString(img_src_gallery_three);
        dest.writeString(img_src_gallery_four);
        dest.writeString(img_src_gallery_five);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}