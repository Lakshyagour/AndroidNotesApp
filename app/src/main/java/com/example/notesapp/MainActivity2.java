package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity  {

    private ImageButton imageButton;
    private Button mred,mgreen,mblue,morange,mvoilet,mpink;
    private LinearLayout ll1;
    private LinearLayout ll2;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;

    List<NoteData> mNoteList = new ArrayList<NoteData>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        DatabaseHandler db = new DatabaseHandler(this);
        db.getWritableDatabase();

        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        db.addNote(new NoteData("First Note","In next Activity save button working",date,R.color.orange));
        db.addNote(new NoteData("Second Note","Add button working",date,R.color.voilet));
        db.addNote(new NoteData("Third Note","Note UI completed",date,R.color.voilet));
        db.addNote(new NoteData("Fourth Note","Splash Screen Working",date,R.color.voilet));



        mNoteList = db.getAllNotes();
        imageButton = findViewById(R.id.addnew);
        mred = findViewById(R.id.red);
        mblue = findViewById(R.id.blue);
        morange = findViewById(R.id.orange);
        mgreen = findViewById(R.id.green);
        mpink = findViewById(R.id.pink);
        mvoilet = findViewById(R.id.voilet);
        ll1 = findViewById(R.id.ll1);
        ll2 = findViewById(R.id.ll2);

        imageButton.setBackgroundResource(R.drawable.roundedbutton_white);
        imageButton.setImageResource(R.drawable.ic_baseline_add_24_black);

        mRecyclerView = findViewById(R.id.recyclerview);
        mGridLayoutManager = new GridLayoutManager(MainActivity2.this, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        MyAdapter myAdapter = new MyAdapter(MainActivity2.this, mNoteList);
        mRecyclerView.setAdapter(myAdapter);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNew();
            }
        });

        mred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewNoteActivity(R.color.red);
            }
        });
        mgreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewNoteActivity(R.color.green);
            }
        });
        mblue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewNoteActivity(R.color.blue);
            }
        });
        morange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewNoteActivity(R.color.orange);
            }
        });
        mvoilet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewNoteActivity(R.color.voilet);
            }
        });
        mpink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewNoteActivity(R.color.pink);
            }
        });
    }

    private void addNew() {
        if(ll1.getVisibility() == View.VISIBLE){
            ll1.setVisibility(View.GONE);
            ll2.setVisibility(View.VISIBLE);
            imageButton.setBackgroundResource(R.drawable.roundedbutton_black);
            imageButton.setImageResource(R.drawable.ic_baseline_add_24_white);
        }
        else {
            ll1.setVisibility(View.VISIBLE);
            ll2.setVisibility(View.GONE);
            imageButton.setBackgroundResource(R.drawable.roundedbutton_white);
            imageButton.setImageResource(R.drawable.ic_baseline_add_24_black);
        }
    }

    private void createNewNoteActivity(int color){
        Intent i = new Intent(MainActivity2.this,MainActivity3.class);
        i.putExtra("color",color);
        finish();
        startActivity(i);
    }
}