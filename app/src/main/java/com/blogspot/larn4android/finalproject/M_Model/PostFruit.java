package com.blogspot.larn4android.finalproject.M_Model;

/**
 * Created by Minto on 9/7/2016.
 */

import android.os.Parcel;
import android.os.Parcelable;
/**
 * Created by Minto on 9/1/2016.
 */
public class PostFruit implements Parcelable {
    private String fruitPostTitle,fruitPostDescription,fruitPostImage;

    public PostFruit(String fruitPostTitle, String fruitPostDescription, String fruitPostImage) {
        this.fruitPostTitle = fruitPostTitle;
        this.fruitPostDescription = fruitPostDescription;
        this.fruitPostImage = fruitPostImage;
    }

    public PostFruit() {
    }

    public PostFruit(Parcel in) {

        String[] data = new String[3];

        in.readStringArray(data);
        this.fruitPostTitle = data[0];
        this.fruitPostDescription = data[1];
        this.fruitPostImage = data[2];
    }

    public String getfruitPostTitle() {
        return fruitPostTitle;
    }

    public void setfruitPostTitle(String fruitPostTitle) {
        this.fruitPostTitle = fruitPostTitle;
    }

    public String getfruitPostDescription() {
        return fruitPostDescription;
    }

    public void setfruitPostDescription(String fruitPostDescription) {
        this.fruitPostDescription = fruitPostDescription;
    }

    public String getfruitPostImage() {
        return fruitPostImage;
    }

    public void setfruitPostImage(String fruitPostImage) {
        this.fruitPostImage = fruitPostImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeStringArray(new String[] {this.fruitPostTitle,
                this.fruitPostDescription,
                this.fruitPostImage});
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public PostFruit createFromParcel(Parcel in) {
            return new PostFruit(in);
        }

        public PostFruit[] newArray(int size) {
            return new PostFruit[size];
        }
    };
}

