package com.example.fengshui_admin.model;

import android.net.Uri;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;


public class Image implements Serializable {

    private String imgURL;
    private Uri imageUri;

    public Image(String imgURL) {
        this.imgURL = imgURL;
    }

    public Uri getImageUri(){
        imageUri = Uri.parse(imgURL).buildUpon().scheme("https").build();
        return imageUri;
    }

    public Uri getUriRoot()
    {
        return Uri.parse(imgURL);
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }
}