package com.blogspot.larn4android.finalproject.M_Model;

/**
 * Created by Minto on 9/4/2016.
 */

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Minto on 9/1/2016.
 */
public class PostProjokty implements Parcelable {


    private String proPostTitle,proPostDescription,proPostImage;

    public PostProjokty(String proPostTitle, String proPostDescription, String proPostImage) {
        this.proPostTitle = proPostTitle;
        this.proPostDescription = proPostDescription;
        this.proPostImage = proPostImage;
    }

    public PostProjokty() {
    }

    public PostProjokty(Parcel in) {

        String[] data = new String[3];

        in.readStringArray(data);
        this.proPostTitle = data[0];
        this.proPostDescription = data[1];
        this.proPostImage = data[2];
    }

    public String getproPostTitle() {
        return proPostTitle;
    }

    public void setproPostTitle(String proPostTitle) {
        this.proPostTitle = proPostTitle;
    }

    public String getproPostDescription() {
        return proPostDescription;
    }

    public void setproPostDescription(String proPostDescription) {
        this.proPostDescription = proPostDescription;
    }

    public String getproPostImage() {
        return proPostImage;
    }

    public void setproPostImage(String proPostImage) {
        this.proPostImage = proPostImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeStringArray(new String[] {this.proPostTitle,
                this.proPostDescription,
                this.proPostImage});
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public PostProjokty createFromParcel(Parcel in) {
            return new PostProjokty(in);
        }

        public PostProjokty[] newArray(int size) {
            return new PostProjokty[size];
        }
    };
}
