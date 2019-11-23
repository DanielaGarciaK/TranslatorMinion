package com.example.wishlistapplication.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class NoteCard {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String translartion;

    private String notes;


    public NoteCard(String title, String translartion, String notes) {
        this.title = title;
        this.translartion = translartion;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTranslartion() {
        return translartion;
    }

    public String getNotes() {
        return notes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTranslartion(String translartion) {
        this.translartion = translartion;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}