package com.example.wishlistapplication.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wishlistapplication.Model.NoteCard;
import com.example.wishlistapplication.Repository.Repository;
import com.example.wishlistapplication.ui.List.ListFragment;

import java.util.List;

public class NoteCardViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<NoteCard>> allNotes;

    public NoteCardViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allNotes = repository.getAllNotes();
    }

    public void insert(NoteCard note) {
        repository.insert(note);
    }

    public void update(NoteCard note) {
        repository.update(note);
    }

    public void delete(NoteCard note) {
        repository.delete(note);
    }

    public LiveData<List<NoteCard>> getAllNotes() {
        return allNotes;
    }
}
