package com.example.lab03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import  android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spi_f;
    Spinner spi_t;
    EditText in_fr;
    TextView str_t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spi_f=findViewById(R.id.from_spiner);

        spi_t=findViewById(R.id.to_spiner);

        in_fr=findViewById(R.id.from_inter);
        str_t=findViewById(R.id.to_inter);


        ArrayAdapter <String> adp = new <String> ArrayAdapter(this, android.R.layout.simple_list_item_1);

        adp.add("mm");
        adp.add("cm");
        adp.add("m");
        adp.add("km");

        spi_f.setAdapter(adp);
        spi_t.setAdapter(adp);


    }
    public void on_convert(View v)
    {
        try
        {
            float from = Float.parseFloat(in_fr.getText().toString());
            if(from < 0 )
            {
                Toast.makeText(this, "can not be less than 0.", Toast.LENGTH_LONG).show();
            }
            String sf = (String) spi_f.getSelectedItem();
            String sto = (String) spi_t.getSelectedItem();

            float tot = 0.0f;
            float m = 0.0f;
            if (sf.equals("mm")) m = from/1000.0f;
            if (sf.equals("cm")) m = from/100.0f;
            if (sf.equals("m")) m = from/1.0f;
            if (sf.equals("km")) m = from*1000.0f;

            if(sto.equals("mm")) tot = m*1000.0f;
            if(sto.equals("cm")) tot = m*100.0f;
            if(sto.equals("m")) tot = m*1.0f;
            if(sto.equals("km")) tot = m/1000.0f;

            str_t.setText(String.valueOf(tot));

        }
        catch (Exception e)
        {
            Toast.makeText (this , String.format("incorrect!"), Toast.LENGTH_LONG).show();            }
        }
    }
