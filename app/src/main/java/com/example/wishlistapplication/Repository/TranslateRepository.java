package com.example.wishlistapplication.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.wishlistapplication.API.ServiceGenerator;
import com.example.wishlistapplication.API.TranslatorApi;
import com.example.wishlistapplication.API.TranslatorResponse;
import com.example.wishlistapplication.Model.Translation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TranslateRepository {

    private static TranslateRepository instance;
    private MutableLiveData<Translation> translation;

    private TranslateRepository(){
        translation = new MutableLiveData<>();
    }

    public static synchronized  TranslateRepository getInstance(){
        if(instance == null){
            instance = new TranslateRepository();
        }
        return instance;
    }
    public LiveData<Translation> getTranslation(){
        return translation;
    }

    public void updateTranslation (String text){
        TranslatorApi translatorApi = ServiceGenerator.getTranslatorApi();
        Call<TranslatorResponse> call = translatorApi.getTranslation(text);
        call.enqueue(new Callback<TranslatorResponse>() {
            @Override
            public void onResponse(Call<TranslatorResponse> call, Response<TranslatorResponse> response) {
                if (response.code() == 200){
                    translation.setValue(response.body().getTranslation());
                    Log.i("Daniela","hello");
                }
            }

            @Override
            public void onFailure(Call<TranslatorResponse> call, Throwable t) {
                Log.i("Retrofit", "something went wrong");
            }
        });
    }

}
