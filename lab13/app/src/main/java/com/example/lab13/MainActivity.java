package com.example.lab13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText firstNumberField, secondNumberField;
    TextView result;
    String mode = "degrees";
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNumberField = findViewById(R.id.first_number);
        secondNumberField = findViewById(R.id.second_number);
        result = findViewById(R.id.result);
        database = new Database(this);

        findViewById(R.id.plus).setOnClickListener((v) -> {
            makeBinaryRequest("sum");
        });
        findViewById(R.id.minus).setOnClickListener((v) -> {
            makeBinaryRequest("sub");
        });
        findViewById(R.id.mul).setOnClickListener((v) -> {
            makeBinaryRequest("mul");
        });

        findViewById(R.id.div).setOnClickListener((v) -> {
            makeBinaryRequest("div");
        });

        findViewById(R.id.div_int).setOnClickListener((v) -> {
            makeBinaryRequest("div_int");
        });
        findViewById(R.id.mod).setOnClickListener((v) -> {
            makeBinaryRequest("mod");
        });
        findViewById(R.id.power).setOnClickListener((v) -> {
            makeBinaryRequest("sqr");
        });


        findViewById(R.id.sin).setOnClickListener((v) -> {
            makeUnaryRequest("sin");
        });
        findViewById(R.id.cos).setOnClickListener((v) -> {
            makeUnaryRequest("cos");
        });
        findViewById(R.id.tan).setOnClickListener((v) -> {
            makeUnaryRequest("tan");
        });
        findViewById(R.id.sqrt).setOnClickListener((v) -> {
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
            Intent i = new Intent(getApplicationContext(), MainActivity2.class);
            startActivity(i);
        });
    }


    public void makeBinaryRequest(String sign) {
        float first = Float.parseFloat(firstNumberField.getText().toString());
        float second = Float.parseFloat(secondNumberField.getText().toString());

        HttpRequest request = new HttpRequest(this, (res) -> {
            database.add(first + " " + sign + " " + second + " = " + res);
            result.setText(res);
        }, (error) ->
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show());

        request.makeRequest("http://85.159.230.57:1001/" + sign + "/" + first + "/" + second);
    }

    public void makeUnaryRequest(String sign) {
        float first = Float.parseFloat(firstNumberField.getText().toString());

        HttpRequest request = new HttpRequest(this, (res) -> {
            database.add(sign + " " + first + " = " +res);
            result.setText(res);
        }, (error) ->
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show());

        request.makeRequest("http://85.159.230.57:1001/" + sign + "/" + first + "?mode=" + mode);
    }
}