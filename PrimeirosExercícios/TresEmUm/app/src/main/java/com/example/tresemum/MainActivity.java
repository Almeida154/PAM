package com.example.tresemum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {
    RadioButton fib, fat, pot;
    EditText v1, v2;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v1 = findViewById(R.id.edtV1);
        v2 = findViewById(R.id.edtV2);
        fib = findViewById(R.id.rbFibonacci);
        fat = findViewById(R.id.rbFatorial);
        pot = findViewById(R.id.rbPotencia);
        result = findViewById(R.id.answer);

    }

    public void btnVerificar(View view) {

        int valor1, valor2;

        valor1 = Integer.parseInt(v1.getText().toString());

        if(!fat.isChecked() && !fib.isChecked() && !pot.isChecked()){
            Toast.makeText(getApplicationContext(), "Cuidado campeão, escolha uma opção de cálculo!", Toast.LENGTH_LONG).show();
        }

        //--Fatorial--//

        if(fat.isChecked()){
            result.setText("Resultado:");
            int f = 1, i = 1;
            while (i <= valor1) {
                f = f * i;
                i++;
            }
            result.append(" "+f);
            v1.setText("");
        }


        //--Fibonacci--//

        if(fib.isChecked() && valor1 > 20){
            Toast.makeText(getApplicationContext(), "Vai com calma amigão, defina um número menor que 20", Toast.LENGTH_LONG).show();
            v1.setText("");
        }

        else if(fib.isChecked()){
            result.setText("Resultado:");
            int vetor[] = new int [valor1];
            vetor[0] = 0;
            vetor[1] = 1;
            int i = 2, n1 = 0, n2 = 1, n3;

            while(i < valor1){
                n3 = n1 + n2;
                vetor[i] = n3;
                n1 = n2;
                n2 = n3;
                i++;
            }

            for(int j=0; j<valor1; j++) {
                result.append(" "+vetor[j]);
            }
            v1.setText("");
        }

        //--Potência--//

        if(pot.isChecked()){
            valor2 = Integer.parseInt(v2.getText().toString());
            result.setText("Resultado:");
            int i, p = 1;
            for(i = 1; i<=valor2; i++){
                p *= valor1;
            }
            result.append(" "+p);
            v1.setText("");
            v2.setText("");
        }
    }
}