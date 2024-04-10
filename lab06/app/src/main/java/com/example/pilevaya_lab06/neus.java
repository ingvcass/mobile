package com.example.pilevaya_lab06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class neus extends AppCompatActivity {
    EditText ttitle;
    EditText ttext;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neus);

        ttitle = findViewById(R.id.titletext);
        ttext = findViewById(R.id.texttext);

        Intent i = getIntent();
        pos = i.getIntExtra("my-note-index", -1);
        ttitle.setText(i.getStringExtra("my-note-title"));
        ttext.setText(i.getStringExtra("my-note-text"));
    }
    public void on_del(View v)
    {
        finish();
    }
    public void on_ok(View v)
    {
        Intent i = new Intent();
        i.putExtra("my-note-index", pos);
        i.putExtra("my-note-title", ttitle.getText().toString());
        i.putExtra("my-note-text", ttext.getText().toString());
        setResult(RESULT_OK, i);
        finish();
    }
}