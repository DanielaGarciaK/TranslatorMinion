package com.example.wishlistapplication.API;

import com.example.wishlistapplication.Model.Translation;
import com.google.gson.annotations.SerializedName;

public class TranslatorResponse {

    //@SerializedName("translated")
    String translated;


    //@SerializedName("text")
    String text;

    public Translation getTranslation(){
        return new Translation(text, translated);
    }

}
