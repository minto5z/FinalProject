package com.blogspot.larn4android.finalproject.M_Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Minto on 9/1/2016.
 */
public class PostFish implements Parcelable {
    private String fishPoatTitle,fishPostDescription,fishPostImage;

    public PostFish(String fishPoatTitle, String fishPostDescription, String fishPostImage) {
        this.fishPoatTitle = fishPoatTitle;
        this.fishPostDescription = fishPostDescription;
        this.fishPostImage = fishPostImage;
    }

    public PostFish() {
    }

    public PostFish(Parcel in) {

        String[] data = new String[3];

        in.readStringArray(data);
        this.fishPoatTitle = data[0];
        this.fishPostDescription = data[1];
        this.fishPostImage = data[2];
    }

    public String getFishPoatTitle() {
        return fishPoatTitle;
    }

    public void setFishPoatTitle(String fishPoatTitle) {
        this.fishPoatTitle = fishPoatTitle;
    }

    public String getFishPostDescription() {
        return fishPostDescription;
    }

    public void setFishPostDescription(String fishPostDescription) {
        this.fishPostDescription = fishPostDescription;
    }

    public String getFishPostImage() {
        return fishPostImage;
    }

    public void setFishPostImage(String fishPostImage) {
        this.fishPostImage = fishPostImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeStringArray(new String[] {this.fishPoatTitle,
                this.fishPostDescription,
                this.fishPostImage});
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public PostFish createFromParcel(Parcel in) {
            return new PostFish(in);
        }

        public PostFish[] newArray(int size) {
            return new PostFish[size];
        }
    };
}
