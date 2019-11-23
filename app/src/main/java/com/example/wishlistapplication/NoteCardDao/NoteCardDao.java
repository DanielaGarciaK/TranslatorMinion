package com.example.wishlistapplication.NoteCardDao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.wishlistapplication.Model.NoteCard;

import java.util.List;

@Dao
public interface NoteCardDao {

    @Insert
    void insert(NoteCard note);

    @Update
    void update(NoteCard note);

    @Delete
    void delete(NoteCard note);

    @Query("SELECT * FROM note_table")
    LiveData<List<NoteCard>> getAllNotes();
}
