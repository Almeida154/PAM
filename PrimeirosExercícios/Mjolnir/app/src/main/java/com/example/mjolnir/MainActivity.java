package com.example.mjolnir;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnCalc = findViewById(R.id.btnCalc);
        btnCalc.setOnClickListener(new View.OnClickListener() {
            EditText nome = findViewById(R.id.edtNome);
            @Override
            public void onClick(View v) {
                if(nome.getText().toString().equals("Thor") || nome.getText().toString().equals("thor")){
                    Toast.makeText(getApplicationContext(), nome.getText()+", você é digno!", Toast.LENGTH_SHORT).show();
                }
                else if(nome.getText().toString().equals("Capitao America") || nome.getText().toString().equals("capitao america") || nome.getText().toString().equals("Capitao america")){
                    Toast.makeText(getApplicationContext(), nome.getText()+", você é digno!", Toast.LENGTH_SHORT).show();
                }
                else if(nome.getText().toString().equals("Homem de ferro") || nome.getText().toString().equals("homem de ferro" ) || nome.getText().toString().equals("Homem de Ferro")){
                    Toast.makeText(getApplicationContext(), nome.getText()+", você é digno!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),  "Você NÃO é digno!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
