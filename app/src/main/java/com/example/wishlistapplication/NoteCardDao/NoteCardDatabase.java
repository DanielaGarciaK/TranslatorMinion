package com.example.wishlistapplication.NoteCardDao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.wishlistapplication.Model.NoteCard;


@Database(entities = {NoteCard.class}, version = 1)
public abstract class NoteCardDatabase extends RoomDatabase {


    private static NoteCardDatabase instance;

    public abstract NoteCardDao noteDao();

    public static synchronized NoteCardDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteCardDatabase.class, "note_database")
                    .build();
        }
        return instance;
    }
}
