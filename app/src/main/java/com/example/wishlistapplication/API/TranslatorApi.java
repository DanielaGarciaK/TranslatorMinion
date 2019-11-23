package com.example.wishlistapplication.API;


import retrofit2.Call;
import retrofit2.http.GET;

import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TranslatorApi {
        @GET("translate/minion.json?")
        Call<TranslatorResponse> getTranslation(@Query("text") String text);

}
