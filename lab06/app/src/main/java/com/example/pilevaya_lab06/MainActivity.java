package com.example.pilevaya_lab06;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter <note> adp;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adp = new ArrayAdapter <note>(this, android.R.layout.simple_list_item_1);
        ListView lis = findViewById(R.id.idList);
        lis.setAdapter(adp);

        lis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                count = position;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if (data != null)
        {
            int pos = data.getIntExtra("my-note-index", 1);
            String title = data.getStringExtra("my-note-title");
            String text = data.getStringExtra("my-note-text");
            note n = adp.getItem(pos);
            n.title = title;
            n.text = text;
            adp.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void on_new(View v)
    {
        note n = new note();
        n.title = "new note";
        n.text = "text";
        adp.add(n);
        int pos = adp.getPosition(n);
        Intent i = new Intent(this, neus.class);
        i.putExtra("my-note-index", pos);
        i.putExtra("my-note-title", n.title);
        i.putExtra("my-note-text", n.text);
        startActivityForResult(i, 12345);
    }
    public void on_edit(View v)
    {
        note n = adp.getItem(count);
        Intent i = new Intent(this, neus.class);
        i.putExtra("my-note-index", count);
        i.putExtra("my-note-title", n.title);
        i.putExtra("my-note-text", n.text);
        startActivityForResult(i, 12345);
    }
    public void on_delite(View v)
    {
        adp.remove(adp.getItem(count));
    }
}