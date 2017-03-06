package com.blogspot.larn4android.finalproject.M_Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Minto on 9/1/2016.
 */
public class PostAnimal implements Parcelable {
    private String animalPostTitle,animalPostDescription,animalPostImage;

    public PostAnimal(String animalPostTitle, String animalPostDescription, String animalPostImage) {
        this.animalPostTitle = animalPostTitle;
        this.animalPostDescription = animalPostDescription;
        this.animalPostImage = animalPostImage;
    }

    public PostAnimal() {
    }

    public PostAnimal(Parcel in) {

        String[] data = new String[3];

        in.readStringArray(data);
        this.animalPostTitle = data[0];
        this.animalPostDescription = data[1];
        this.animalPostImage = data[2];
    }

    public String getanimalPostTitle() {
        return animalPostTitle;
    }

    public void setanimalPostTitle(String animalPostTitle) {
        this.animalPostTitle = animalPostTitle;
    }

    public String getanimalPostDescription() {
        return animalPostDescription;
    }

    public void setanimalPostDescription(String animalPostDescription) {
        this.animalPostDescription = animalPostDescription;
    }

    public String getanimalPostImage() {
        return animalPostImage;
    }

    public void setanimalPostImage(String animalPostImage) {
        this.animalPostImage = animalPostImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeStringArray(new String[] {this.animalPostTitle,
                this.animalPostDescription,
                this.animalPostImage});
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public PostAnimal createFromParcel(Parcel in) {
            return new PostAnimal(in);
        }

        public PostAnimal[] newArray(int size) {
            return new PostAnimal[size];
        }
    };
}
