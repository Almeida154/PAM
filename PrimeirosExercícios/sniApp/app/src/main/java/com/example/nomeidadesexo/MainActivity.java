package com.example.nomeidadesexo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText nome, idade;
    RadioButton masc, fem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.edtNome);
        idade = findViewById(R.id.edtId);
        masc = findViewById(R.id.rbMasculino);
        fem = findViewById(R.id.rbFeminino);

    }

    public void btnVerificar(View view) {
        String nomeF;
        int id;
        nomeF = nome.getText().toString();
        id = Integer.parseInt(idade.getText().toString());
        if(fem.isChecked()){
            Toast.makeText(getApplicationContext(), nomeF.toUpperCase()+", você tem "+id+" anos e é do sexo feminino", Toast.LENGTH_LONG).show();
        }
        else if(masc.isChecked()){
            Toast.makeText(getApplicationContext(), nomeF.toUpperCase()+", você tem "+id+" anos e é do sexo masculino", Toast.LENGTH_LONG).show();
        }
        else if(!masc.isChecked() && !fem.isChecked() && nome != null && idade != null){
            Toast.makeText(getApplicationContext(), "Você deve selecionar um gênero!", Toast.LENGTH_SHORT).show();
        }
    }
}
