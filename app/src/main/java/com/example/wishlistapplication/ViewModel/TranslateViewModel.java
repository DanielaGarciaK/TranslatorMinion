package com.example.wishlistapplication.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.wishlistapplication.Repository.TranslateRepository;
import com.example.wishlistapplication.Model.Translation;

public class TranslateViewModel extends ViewModel {

    TranslateRepository repository;

    public TranslateViewModel(){
        repository = TranslateRepository.getInstance();
    }
    public LiveData<Translation> getTranslation(){
        return repository.getTranslation();
    }
    public void updateTranslation(String s){
        repository.updateTranslation(s);
    }
}
