package com.example.lab04;


import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    CheckBox[] checkBoxes = new CheckBox[4];
    EditText[] counts = new EditText[4];
    EditText[] prices = new EditText[4];
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBoxes[0] = findViewById(R.id.Apple);
        checkBoxes[1] = findViewById(R.id.Strawberry);
        checkBoxes[2] = findViewById(R.id.Blueberry);
        checkBoxes[3] = findViewById(R.id.Potatoes);
        counts[0] = findViewById(R.id.Apple_count);
        counts[1] = findViewById(R.id.Strawberry_count);
        counts[2] = findViewById(R.id.Blueberry_count);
        counts[3] = findViewById(R.id.Potatoes_count);
        prices[0] = findViewById(R.id.Apple_price);
        prices[1] = findViewById(R.id.Strawberry_price);
        prices[2] = findViewById(R.id.Blueberry_price);
        prices[3] = findViewById(R.id.Potatoes_price);

    }
    public void onCalc(View view)
    {
        RadioButton adialog = findViewById(R.id.dialog);
        RadioButton atoast = findViewById(R.id.toast);
        StringBuilder builder = new StringBuilder();
        double res = 0;
        for (int i = 0; i < checkBoxes.length; i++) 
        {

            if (!checkBoxes[i].isChecked()) continue;
            try {                
                double value = Double.parseDouble(counts[i].getText().toString());
                double price = Double.parseDouble(prices[i].getText().toString());
                if(value <= 0 || price <= 0) 
                {                    
                    Toast.makeText(view.getContext(), "Price or Count can not be less than 1. Skipped", Toast.LENGTH_LONG).show();
                continue;                }
                double tempRes = value * price;
                res += tempRes;
                builder.append(String.format("%d: %.2f * %s = %.2f * %.2f = %.2f\n", i, value, checkBoxes[i].getText().toString(), value, price, tempRes));
            }
            catch (Exception e)
            {
                Toast.makeText(view.getContext(), String.format("Field %s incorrect. Skipped", checkBoxes[i].getText()), Toast.LENGTH_LONG).show();            }
            }
        builder.append(String.format("total - %.2f", res));
        if (res>0)
        {
            if (adialog.isChecked()) {
                AlertDialog.Builder anser = new AlertDialog.Builder(this);
                AlertDialog ans = anser.create();
                ans.setTitle("result");
                ans.setIcon(R.drawable.iconinf);
                ans.setMessage(builder);
                ans.show();
            } else if (atoast.isChecked()) {
                Toast.makeText(view.getContext(), builder, Toast.LENGTH_LONG).show();
            }
        }
    }
}
