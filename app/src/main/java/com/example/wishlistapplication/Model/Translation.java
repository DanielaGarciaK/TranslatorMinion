package com.example.wishlistapplication.Model;

public class Translation {
    private String toTranslate;
    private String translation;

    public Translation(String toTranslate,  String translation) {
        this.toTranslate = toTranslate;

        this.translation = translation;
    }

    public String getToTranslate() {
        return toTranslate;
    }

    public void setToTranslate(String toTranslate) {
        this.toTranslate = toTranslate;
    }



    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
