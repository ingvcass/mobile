package com.example.lab13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText an, bn, resn;
    String mode = "degrees";
    Database database;
    ListView historyl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        an = findViewById(R.id.anum);
        bn = findViewById(R.id.bnum);
        resn = findViewById(R.id.resnum);
        database = new Database(this);
        historyl = findViewById(R.id.historylist);

        findViewById(R.id.plus).setOnClickListener((v) -> {
            makeBinaryRequest("sum");
        });
        findViewById(R.id.minus).setOnClickListener((v) -> {
            makeBinaryRequest("min");
        });
        findViewById(R.id.multy).setOnClickListener((v) -> {
            makeBinaryRequest("mul");
        });

        findViewById(R.id.div).setOnClickListener((v) -> {
            makeBinaryRequest("deli");
        });

        findViewById(R.id.cel).setOnClickListener((v) -> {
            makeBinaryRequest("cel");
        });
        findViewById(R.id.ost).setOnClickListener((v) -> {
            makeBinaryRequest("ost");
        });
        findViewById(R.id.power).setOnClickListener((v) -> {
            makeBinaryRequest("ste");
        });


        findViewById(R.id.sin).setOnClickListener((v) -> {
            makeUnaryRequest("sin");
        });
        findViewById(R.id.cos).setOnClickListener((v) -> {
            makeUnaryRequest("cos");
        });
        findViewById(R.id.tan).setOnClickListener((v) -> {
            makeUnaryRequest("tg");
        });
        findViewById(R.id.stqr).setOnClickListener((v) -> {
            makeUnaryRequest("sqrt");
        });


        Spinner dropdown = findViewById(R.id.drop);
        String[] items = new String[]{"degrees", "radian"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mode = items[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mode = "degrees";
            }
        });


        findViewById(R.id.history).setOnClickListener((e) -> {
            Log.e("asd", database.getAll().size() + "");
            ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, database.getAll());
            historyl.setAdapter(adapter1);
        });
    }


    public void makeBinaryRequest(String sign) {
        float first = Float.parseFloat(an.getText().toString());
        float second = Float.parseFloat(bn.getText().toString());

        HttpRequest request = new HttpRequest(this, (res) -> {
            database.add(first + " " + sign + " " + second + " = " + res);
            resn.setText(res);
        }, (error) ->
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show());

        request.makeRequest("https://ingvcass.pythonanywhere.com/" + sign + "/" + first + "/" + second);
    }

    public void makeUnaryRequest(String sign) {
        float first = Float.parseFloat(an.getText().toString());

        HttpRequest request = new HttpRequest(this, (res) -> {
            database.add(sign + " " + first + " = " +res);
            resn.setText(res);
        }, (error) ->
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show());

        request.makeRequest("https://ingvcass.pythonanywhere.com/" + sign + "/" + first + "?mode=" + mode);
    }

}