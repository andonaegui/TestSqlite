package com.example.testsqlite.testsqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private EditText noteText;
    private ListView noteList;
    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startComponents();
        startEvents();

    }

    private void startComponents() {
        saveBtn = (Button) findViewById(R.id.saveBtn);
        noteText = (EditText) findViewById(R.id.editText);
        noteList = (ListView) findViewById(R.id.listView);
    }

    private void startEvents() {

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });

        getCurrentNoteList();
    }


    private void addNote() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }



    private void getCurrentNoteList() {

    }
}
