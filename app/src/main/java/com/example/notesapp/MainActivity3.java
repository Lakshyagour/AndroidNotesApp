package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity3 extends AppCompatActivity {
    private RelativeLayout relativeLayout;
    private ImageButton back,undo,redo,delete,save;
    private EditText mtitle,mcontent;
    private int color;
    private DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        db = new DatabaseHandler(this);
        db.getWritableDatabase();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            color = bundle.getInt("color");
        }

        relativeLayout = findViewById(R.id.rl);
        back = findViewById(R.id.back);
        undo = findViewById(R.id.undo);
        redo = findViewById(R.id.redo);
        delete = findViewById(R.id.delete);
        save = findViewById(R.id.save);
        mtitle = findViewById(R.id.title);
        mcontent = findViewById(R.id.content);


        relativeLayout.setBackgroundColor(ContextCompat.getColor(this,color));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity3.this,MainActivity2.class);
                i.putExtra("color",color);
                finish();
                startActivity(i);

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity3.this,MainActivity2.class);
                i.putExtra("color",color);
                finish();
                startActivity(i);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote(mtitle.getText().toString(),mcontent.getText().toString(),color);
                Intent i = new Intent(MainActivity3.this,MainActivity2.class);
                finish();
                startActivity(i);
            }
        });

    }

    private void saveNote(String title, String content, int color) {
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        db.addNote(new NoteData(title,content,date,color));
    }

}