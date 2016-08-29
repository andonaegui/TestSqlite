package com.example.testsqlite.testsqlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText noteText;
    private ListView noteList;
    private Button saveBtn;
    private ArrayList<String> notesArray;
    private ArrayAdapter<String> notesListAdapter;
    private Button clearBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startComponents();
        startEvents();

    }

    private void startComponents() {
        notesArray  = new ArrayList<String>();
        notesListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 ,notesArray);
        saveBtn = (Button) findViewById(R.id.saveBtn);
        clearBtn = (Button) findViewById(R.id.clearBtn);
        noteText = (EditText) findViewById(R.id.editText);
        noteList = (ListView) findViewById(R.id.listView);
    }

    private void startEvents() {
        noteList.setAdapter(notesListAdapter);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearNotes();
            }
        });

        getCurrentNoteList();
    }


    private void addNote() {
        String note_text = noteText.getText().toString();
        Db database = new Db(getApplicationContext());
        database.createRecord(note_text);
        notesArray.add(note_text);
        getCurrentNoteList();
    }

    private void clearNotes() {
        Toast.makeText(this, "Limpiando notas", Toast.LENGTH_LONG).show();

        Db database = new Db(getApplicationContext());
        database.deleteRecords();
        notesArray.clear();
        notesListAdapter.notifyDataSetChanged();
    }

    private void getCurrentNoteList() {
        notesArray.clear();
        Db database = new Db(getApplicationContext());
        Cursor records = database.selectRecords();
        while(records.moveToNext()){
            String uname = records.getString(records.getColumnIndex("note"));
            notesArray.add(uname);
        }
        notesListAdapter.notifyDataSetChanged();
    }
}
