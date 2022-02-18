package com.example.notesapp;


public class NoteData {
    private int id;
    private String title;
    private String content;
    private int color;
    private String last_edited;


    public  NoteData(){
    }
    public NoteData(int id, String title, String content, String last_edited, int color) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.last_edited = last_edited;
        this.color = color;
    }

    public NoteData( String title, String content, String last_edited, int color) {
        this.title = title;
        this.content = content;
        this.last_edited = last_edited;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLastEdited() {
        return last_edited;
    }

    public void setLastEdited(String last_edited) {
        this.last_edited = last_edited;
    }

    public int getColor() { return color;  }

    public void setColor(int color) { this.color = color;  }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
