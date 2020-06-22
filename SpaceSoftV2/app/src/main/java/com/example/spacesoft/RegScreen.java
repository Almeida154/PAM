package com.example.spacesoft;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class RegScreen extends AppCompatActivity {
    public static ArrayList<User> users;
    TextView RegisterName, back;
    EditText createUser, createPassword, confirmPassword, createBalance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_screen);
        RegisterName = findViewById(R.id.txvRegRegister);
        createUser = findViewById(R.id.edtRegUser);
        createPassword = findViewById(R.id.edtRegPassword);
        confirmPassword = findViewById(R.id.edtRegPasswordAgain);
        createBalance = findViewById(R.id.edtBalance);
        back = findViewById(R.id.volts);

        Typeface itc = Typeface.createFromAsset(getAssets(), "ITC Avant Garde Gothic LT Bold.otf");
        Typeface js = Typeface.createFromAsset(getAssets(), "JosefinSans-Medium.ttf");

        RegisterName.setTypeface(itc);
        createUser.setTypeface(js);
        createPassword.setTypeface(js);
        confirmPassword.setTypeface(js);
        createBalance.setTypeface(js);
        back.setTypeface(js);

        users = new ArrayList<>();
    }

    public void reg(View view) {

        String user = createUser.getText().toString();
        String senha = createPassword.getText().toString();
        String senhadnv = confirmPassword.getText().toString();
        String saldao = createBalance.getText().toString();

        if(user.trim().isEmpty() && senha.trim().isEmpty() && senhadnv.trim().isEmpty() && saldao.trim().isEmpty()){
            createUser.setError("Insert all fields");
            createPassword.setError("Insert all fields");
            confirmPassword.setError("Insert all fields");
            createBalance.setError("Insert all fields");
            return;
        }else if(senha.trim().isEmpty() && senhadnv.trim().isEmpty() && saldao.trim().isEmpty()){
            createPassword.setError("Insert all fields");
            confirmPassword.setError("Insert all fields");
            createBalance.setError("Insert all fields");
            return;
        }else if(senhadnv.trim().isEmpty() && saldao.trim().isEmpty()){
            confirmPassword.setError("Insert all fields");
            createBalance.setError("Insert all fields");
            return;
        }else if(saldao.trim().isEmpty()){
            createBalance.setError("Insert all fields");
            return;
        }else{
            if(senha.equals(senhadnv)){
                users.add(new User(user, senha, saldao));
                createUser.setText("");
                createPassword.setText("");
                confirmPassword.setText("");
                createBalance.setText("");
                Toast.makeText(this, "Registered User, back to Login", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Different passwords", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void backToHome(View view) {
        Intent voltar = new Intent(getApplicationContext(), LoginScreen.class);
        startActivity(voltar);
    }

}
