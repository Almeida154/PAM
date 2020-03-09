package com.example.calculadorahype;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText v1;
    EditText v2;
    Button Sum;
    Button Sub;
    Button Div;
    Button Mult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        v1 = findViewById(R.id.edtValorA);
        v2 = findViewById(R.id.edtValorB);
        Sum = findViewById(R.id.btnSum);
        Sub = findViewById(R.id.btnSub);
        Div = findViewById(R.id.btnDiv);
        Mult = findViewById(R.id.btnMult);

        Sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soma, a, b;
                a = Integer.parseInt(v1.getText().toString());
                b = Integer.parseInt(v2.getText().toString());
                soma = a + b;
                Toast.makeText(getApplicationContext(), "A soma de A e B, é: "+soma, Toast.LENGTH_LONG).show();
                v1.setText("");
                v2.setText("");
            }
        });

        Div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int div, a, b;
                a = Integer.parseInt(v1.getText().toString());
                b = Integer.parseInt(v2.getText().toString());
                div = a / b;
                Toast.makeText(getApplicationContext(), "A divisão de A e B, é: "+div, Toast.LENGTH_LONG).show();
                v1.setText("");
                v2.setText("");
            }
        });

        Sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sub, a, b;
                a = Integer.parseInt(v1.getText().toString());
                b = Integer.parseInt(v2.getText().toString());
                sub = a - b;
                Toast.makeText(getApplicationContext(), "A subtração de A e B, é: "+sub, Toast.LENGTH_LONG).show();
                v1.setText("");
                v2.setText("");
            }
        });
        Mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mult, a, b;
                a = Integer.parseInt(v1.getText().toString());
                b = Integer.parseInt(v2.getText().toString());
                mult = a * b;
                Toast.makeText(getApplicationContext(), "A multiplicação de A e B, é: "+mult, Toast.LENGTH_LONG).show();
                v1.setText("");
                v2.setText("");
            }
        });

    }
}
