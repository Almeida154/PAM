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

public class Exer1038Activity extends AppCompatActivity {
    EditText Qtd, Codiguin;
    TextView Resposta, txtCod, txtCodgin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exer1038);
        txtCod = findViewById(R.id.txtCod);
        txtCodgin = findViewById(R.id.txtCodgin);
        Qtd = findViewById(R.id.qntd);
        Codiguin = findViewById(R.id.cod);
        Resposta = findViewById(R.id.resp);
        Qtd.setTextColor(ContextCompat.getColor(this, R.color.inputs));
        Codiguin.setTextColor(ContextCompat.getColor(this, R.color.inputs));

        Typeface font = Typeface.createFromAsset(getAssets(), "JosefinSans-Medium.ttf");
        txtCod.setTypeface(font);
        Qtd.setTypeface(font);
        txtCodgin.setTypeface(font);
        Codiguin.setTypeface(font);
    }

    public void goTo1164(View view) {
        Intent goTo1164 = new Intent(getApplicationContext(), Exer1164Activity.class);
        startActivity(goTo1164);
    }

    public void CalcPreco(View view) {

        String qtd = Qtd.getText().toString();
        String codi = Codiguin.getText().toString();

        if (codi.trim().equals("")){
            Codiguin.setError("Preencha este campo!");
            return;
        }

        if (qtd.trim().equals("")){
            Qtd.setError("Preencha este campo!");
            return;
        }

        int quantidade = Integer.parseInt(Qtd.getText().toString());
        int codigo = Integer.parseInt(Codiguin.getText().toString());
        double total = 0;
        String answer;
        String desc = "";
        boolean continuar = true;

        switch (codigo){
            case 1:
                desc = "Hot Dog";
                total = quantidade * 4;
                break;
            case 2:
                desc = "X-Salada";
                total = quantidade * 4.50;
                break;
            case 3:
                desc = "X-Bacon";
                total = quantidade * 5;
                break;
            case 4:
                desc = "Torrada Simples";
                total = quantidade * 2;
                break;
            case 5:
                desc = "Refrigerante";
                total = quantidade * 1.50;
                break;
            default:
                continuar = false;
        }

        if(continuar){
            answer = String.format("R$%.2f", total);
            Resposta.setText("Produto: "+desc+"\nQuantidade: "+quantidade+"\nTotal: " + answer);
        }
        else{
            Toast.makeText(this, "Item não existente!", Toast.LENGTH_LONG).show();
        }

        Qtd.setText("");
        Codiguin.setText("");

    }
}
