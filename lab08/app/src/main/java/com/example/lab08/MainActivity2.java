package com.example.lab08;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    private Toolbar toolbar;
    EditText txtclt;
    int nid;
    String ntxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txtclt = findViewById(R.id.txt_content);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i = getIntent();
        nid = i.getIntExtra("note-id", 0);
        ntxt = i.getStringExtra("note-txt");
        txtclt.setText(ntxt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.delete) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("вы уверенны?");
            builder.setPositiveButton("да", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    g.notes.deleteNote(nid);
                    setResult(RESULT_OK);
                    finish();
                }
            });
            builder.setNegativeButton("нет", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                }
            });
            builder.create();
            builder.show();

        }
        else if (id == R.id.modify)
        {
            txtclt = findViewById(R.id.txt_content);
            ntxt = txtclt.getText().toString();
            ContentValues cv = new ContentValues();
            cv.put("txt", ntxt);
            g.notes.update(cv, nid);
        setResult(RESULT_OK);            finish();
        return true;        }
        return super.onOptionsItemSelected(item);
    }
}