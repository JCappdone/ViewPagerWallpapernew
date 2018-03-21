package com.divinevine.moonwallpaper;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by shriji on 11/3/18.
 */

public class ImageModel implements Parcelable {

    private String itemId;
    private String itemImage;

    public ImageModel(String itemId, String itemImage) {
        this.itemId = itemId;
        this.itemImage = itemImage;
    }

    public ImageModel() {
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.itemId);
        dest.writeString(this.itemImage);
    }

    protected ImageModel(Parcel in) {
        this.itemId = in.readString();
        this.itemImage = in.readString();
    }

    public static final Parcelable.Creator<ImageModel> CREATOR = new Parcelable.Creator<ImageModel>() {
        @Override
        public ImageModel createFromParcel(Parcel source) {
            return new ImageModel(source);
        }

        @Override
        public ImageModel[] newArray(int size) {
            return new ImageModel[size];
        }
    };
}
