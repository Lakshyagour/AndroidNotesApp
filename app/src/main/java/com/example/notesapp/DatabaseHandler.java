package com.example.notesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "NotesManager";
    private static final String TABLE_NOTES = "notes";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_COLOR = "color";
    private static final String KEY_LAST_EDITED = "lastEdited";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTES_TABLE = "CREATE TABLE " + TABLE_NOTES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
                + KEY_CONTENT + " TEXT," + KEY_LAST_EDITED+ " TEXT," + KEY_COLOR + " INTEGER)";
        db.execSQL(CREATE_NOTES_TABLE);
        Log.e("CREATE : ",CREATE_NOTES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db);
    }


    void addNote(NoteData noteData) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, noteData.getTitle());
        values.put(KEY_CONTENT, noteData.getContent());
        values.put(KEY_LAST_EDITED, noteData.getLastEdited());
        values.put(KEY_COLOR, noteData.getColor());
        db.insert(TABLE_NOTES, null, values);
        db.close();
    }

    NoteData getNote(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NOTES, new String[] { KEY_ID,
                        KEY_TITLE, KEY_CONTENT,KEY_COLOR,KEY_LAST_EDITED }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        NoteData contact = new NoteData(cursor.getInt(0),
                cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getInt(4));
        return contact;
    }

    // code to get all contacts in a list view
    public List<NoteData> getAllNotes() {
        List<NoteData> contactList = new ArrayList<NoteData>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NOTES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                NoteData contact = new NoteData();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setTitle(cursor.getString(1));
                contact.setContent(cursor.getString(2));
                contact.setLastEdited(cursor.getString(3));
                contact.setColor(cursor.getInt(4));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        return contactList;
    }

    public int updateNote(NoteData noteData) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, noteData.getTitle());
        values.put(KEY_CONTENT, noteData.getContent());
        values.put(KEY_COLOR, noteData.getColor());
        values.put(KEY_LAST_EDITED, noteData.getLastEdited());

        return db.update(TABLE_NOTES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(noteData.getId()) });
    }

    public void deleteNote(NoteData noteData) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NOTES, KEY_ID + " = ?",
                new String[] { String.valueOf(noteData.getId()) });
        db.close();
    }

    public int getNoteCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NOTES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

}
