package com.example.lab02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText A;
    EditText B;
    EditText Res;
    float numbA, numbB;
    Button Add, Minus, Mult, Del, Sin, Cos, Tg, Sqrt, Pow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        A = findViewById(R.id.editA);
        B = findViewById(R.id.editB);
        Res = findViewById(R.id.resalt);

        Add = findViewById(R.id.btnAdd);
        Minus = findViewById(R.id.btnMinus);
        Mult = findViewById(R.id.btnMult);
        Del = findViewById(R.id.btnDel);

        Sin = findViewById(R.id.btnSin);
        Cos = findViewById(R.id.btnCos);
        Tg = findViewById(R.id.btnTg);

        Sqrt = findViewById(R.id.btnSqrt);
        Pow = findViewById(R.id.btnPow);
    }

    public void btnActive(View v)
    {
        try
        {
            numbA = Float.parseFloat(A.getText().toString());
            if (v == Sin || v == Cos || v == Tg)
            {
                numbA = (float)(numbA * 180 / Math.PI);
            }
            if (v != Sqrt && v != Sin && v != Cos && v != Tg)
            {
                numbB = Float.parseFloat(B.getText().toString());
            }
        }
        catch (Exception ex)
        {
            Res.setText("Ошибка: " + ex);
            return;
        }

        float res = 0;

        if (v == Add)
        {
            res = numbA + numbB;
        }
        if (v == Minus)
        {
            res = numbA - numbB;
        }
        if (v == Mult)
        {
            res = numbA * numbB;
        }
        if (v == Del)
        {
            if (numbB != 0)
            {
                res = numbA / numbB;
            }
            else
            {
                Res.setText("Нельзя делить на 0");
                return;
            }
        }
        if (v == Sin)
        {
            res = (float)Math.sin(numbA);
        }
        if (v == Cos)
        {
            res = (float)Math.cos(numbA);
        }
        if (v == Tg)
        {
            res = (float)Math.tan(numbA);
        }
        if (v == Sqrt)
        {
            if (numbA >= 0)
            {
                res = (float)Math.sqrt(numbA);
            }
            else
            {
                Res.setText("Корень меньше 0");
                return;
            }
        }
        if (v == Pow)
        {
            res = (float)Math.pow(numbA, numbB);
        }

        Res.setText(" " + String.valueOf(res));
    }
}