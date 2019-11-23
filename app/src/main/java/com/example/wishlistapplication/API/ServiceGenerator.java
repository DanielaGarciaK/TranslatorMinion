package com.example.wishlistapplication.API;


import com.example.wishlistapplication.Model.Translation;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {


        private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl("https://api.funtranslations.com/")
                .addConverterFactory(GsonConverterFactory.create());

        private static Retrofit retrofit = retrofitBuilder.build();

        private static TranslatorApi translatorapi = retrofit.create(TranslatorApi.class);

        public static TranslatorApi getTranslatorApi() {
            return translatorapi;
        }
    }

