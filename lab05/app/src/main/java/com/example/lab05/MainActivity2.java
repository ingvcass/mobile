package com.example.lab05;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
public class MainActivity2 extends AppCompatActivity {

    EditText text;
    SwitchCompat f, s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        text = findViewById(R.id.text_data);

        f = findViewById(R.id.f);
        s = findViewById(R.id.s);

        Intent i = getIntent();

        text.setText(i.getStringExtra("text"));
        f.setChecked(i.getBooleanExtra("f", false));
        s.setChecked(i.getBooleanExtra("s", false));
    }

    public void onOk(View w) {
        Intent i = new Intent();

        String text = this.text.getText().toString();
        i.putExtra("text", text);
        i.putExtra("f", f.isChecked());
        i.putExtra("s", s.isChecked());

        setResult(RESULT_OK, i);
        finish();
    }

    public void onCancel(View w) {
        setResult(RESULT_CANCELED);
        finish();
    }
}