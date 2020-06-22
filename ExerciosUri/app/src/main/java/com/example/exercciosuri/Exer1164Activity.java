package com.example.exercciosuri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Exer1164Activity extends AppCompatActivity {
    EditText perfectN;
    TextView result, txtP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exer1164);
        txtP = findViewById(R.id.txtP);
        perfectN = findViewById(R.id.nperfect);
        result = findViewById(R.id.result);
        perfectN.setTextColor(ContextCompat.getColor(this, R.color.inputs));

        Typeface font = Typeface.createFromAsset(getAssets(), "JosefinSans-Medium.ttf");
        perfectN.setTypeface(font);
        result.setTypeface(font);
        txtP.setTypeface(font);
    }

    public void goHome(View view) {
        Intent home = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(home);
    }

    public void calcperfect(View view) {

        String perfectERROR = perfectN.getText().toString();

        if(perfectERROR.trim().equals("")){
            perfectN.setError("Preencha este campo!");
            return;
        }

        int soma = 0;
        int n = Integer.parseInt(perfectN.getText().toString());
        for(int i = 1; i < n; i++){
            if(n % i == 0) soma += i;
        }
        if(soma == n) result.setText("Resultado: "+n+" eh perfeito");
        else result.setText("Resultado: "+n+" nao eh perfeito");

        perfectN.setText("");
    }
}
