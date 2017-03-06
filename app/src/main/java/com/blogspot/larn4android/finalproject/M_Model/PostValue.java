package com.blogspot.larn4android.finalproject.M_Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Minto on 9/1/2016.
 */
public class PostValue implements Parcelable {
    
    
    private String valuePostTitle,valuePostDescription,valuePostImage;

    public PostValue(String valuePostTitle, String valuePostDescription, String valuePostImage) {
        this.valuePostTitle = valuePostTitle;
        this.valuePostDescription = valuePostDescription;
        this.valuePostImage = valuePostImage;
    }

    public PostValue() {
    }

    public PostValue(Parcel in) {

        String[] data = new String[3];

        in.readStringArray(data);
        this.valuePostTitle = data[0];
        this.valuePostDescription = data[1];
        this.valuePostImage = data[2];
    }

    public String getvaluePostTitle() {
        return valuePostTitle;
    }

    public void setvaluePostTitle(String valuePostTitle) {
        this.valuePostTitle = valuePostTitle;
    }

    public String getvaluePostDescription() {
        return valuePostDescription;
    }

    public void setvaluePostDescription(String valuePostDescription) {
        this.valuePostDescription = valuePostDescription;
    }

    public String getvaluePostImage() {
        return valuePostImage;
    }

    public void setvaluePostImage(String valuePostImage) {
        this.valuePostImage = valuePostImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeStringArray(new String[] {this.valuePostTitle,
                this.valuePostDescription,
                this.valuePostImage});
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public PostValue createFromParcel(Parcel in) {
            return new PostValue(in);
        }

        public PostValue[] newArray(int size) {
            return new PostValue[size];
        }
    };
}
