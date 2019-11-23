package com.example.wishlistapplication.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.wishlistapplication.Model.NoteCard;
import com.example.wishlistapplication.NoteCardDao.NoteCardDao;
import com.example.wishlistapplication.NoteCardDao.NoteCardDatabase;

import java.util.List;

public class Repository {
    private NoteCardDao notedao;
    private LiveData<List<NoteCard>> allNotes;

    public Repository(Application application) {
        NoteCardDatabase database = NoteCardDatabase.getInstance(application);
        notedao = database.noteDao();
        allNotes = notedao.getAllNotes();
    }

    public LiveData<List<NoteCard>> getAllNotes() {
        return allNotes;
    }

    public void insert(NoteCard note) {
        new InsertNoteAsyncTask(notedao).execute(note);
    }

    public void update(NoteCard note) {
        new UpdateNoteAsyncTask(notedao).execute(note);
    }

    public void delete(NoteCard note) {
        new DeleteNoteAsyncTask(notedao).execute(note);
    }

    private static class InsertNoteAsyncTask extends AsyncTask<NoteCard, Void, Void> {
        private NoteCardDao noteDao;

        private InsertNoteAsyncTask(NoteCardDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(NoteCard... noteCards) {
            noteDao.insert(noteCards[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<NoteCard, Void, Void> {
        private NoteCardDao noteDao;

        private UpdateNoteAsyncTask(NoteCardDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(NoteCard... noteCards) {
            noteDao.update(noteCards[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<NoteCard, Void, Void> {
        private NoteCardDao noteDao;

        private DeleteNoteAsyncTask(NoteCardDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(NoteCard... noteCards) {
            noteDao.delete(noteCards[0]);
            return null;
        }
    }
}
