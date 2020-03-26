package com.java.imagewithtextstoreinroom.utils;

import android.graphics.Bitmap;
import android.net.Uri;

public class MultiFileData {

    private Bitmap bitmapImage;
    private Uri uri;
    private String name;

    public MultiFileData(Bitmap bitmapImage, Uri uri, String name) {
        this.bitmapImage = bitmapImage;
        this.uri = uri;
        this.name = name;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getBitmapImage() {
        return bitmapImage;
    }

    public void setBitmapImage(Bitmap bitmapImage) {
        this.bitmapImage = bitmapImage;
    }
}
