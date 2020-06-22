package com.example.exercciosuri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Double.parseDouble;

public class Exer1012Activity extends AppCompatActivity {
    EditText a, b, c;
    TextView txt1, txt2, txt3, answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exer1012);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        a = findViewById(R.id.valorA);
        b = findViewById(R.id.valorB);
        c = findViewById(R.id.valorC);
        a.setTextColor(ContextCompat.getColor(this, R.color.inputs));
        b.setTextColor(ContextCompat.getColor(this, R.color.inputs));
        c.setTextColor(ContextCompat.getColor(this, R.color.inputs));
        answer = findViewById(R.id.res);

        Typeface font = Typeface.createFromAsset(getAssets(), "JosefinSans-Medium.ttf");
        a.setTypeface(font);
        b.setTypeface(font);
        c.setTypeface(font);
        txt1.setTypeface(font);
        txt2.setTypeface(font);
        txt3.setTypeface(font);
        answer.setTypeface(font);
    }

    public void goTo1038(View view) {
        Intent goTo1038 = new Intent(getApplicationContext(), Exer1038Activity.class);
        startActivity(goTo1038);
    }

    public void CalcArea(View view) {

        String aERROR = a.getText().toString();
        String bERROR = b.getText().toString();
        String cERROR = c.getText().toString();

        if(aERROR.trim().isEmpty()) {
            a.setError("Valor obrigatório!");
            return;
        }
        if(bERROR.trim().isEmpty()) {
            b.setError("Valor obrigatório!");
            return;
        }
        if(cERROR.trim().isEmpty()) {
            c.setError("Valor obrigatório!");
            return;
        }

        double valA = parseDouble(a.getText().toString());
        double valB = parseDouble(b.getText().toString());
        double valC = parseDouble(c.getText().toString());

        double tri=(valA*valC)/2; String triangulo = String.format(" %.3f", tri);
        double cir=(valC*valC)*3.14159; String circulo = String.format(" %.3f", cir);
        double tra=((valA+valB)/2)*valC; String trapezio = String.format(" %.3f", tra);
        double qua=valB*valB; String quadrado = String.format(" %.3f", qua);
        double ret=valA*valB; String retangulo = String.format(" %.3f", ret);

        answer.setText("TRIANGULO:"+triangulo+"\n" +
                        "CIRCULO:"+circulo+"\n" +
                        "TRAPEZIO:"+trapezio+"\n" +
                        "QUADRADO:"+quadrado+"\n" +
                        "RETANGULO:"+retangulo);

        a.setText("");
        b.setText("");
        c.setText("");
    }

}
