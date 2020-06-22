package com.example.spacesoft;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {
    TextView log, reg;
    EditText user, password;
    public static String HomeUser, HomeBalance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        log = findViewById(R.id.txvLogin);
        reg = findViewById(R.id.txvReg);
        user = findViewById(R.id.edtUser);
        password = findViewById(R.id.edtPassword);
        Typeface itc = Typeface.createFromAsset(getAssets(), "ITC Avant Garde Gothic LT Bold.otf");
        Typeface js = Typeface.createFromAsset(getAssets(), "JosefinSans-Medium.ttf");
        log.setTypeface(itc);
        reg.setTypeface(js);
        user.setTypeface(js);
        password.setTypeface(js);

    }

    public void RegisterScreen(View view) {
        Intent toRegister = new Intent(LoginScreen.this, RegScreen.class);
        startActivity(toRegister);
    }

    public void toHome(View view) {
        String senha = password.getText().toString();
        String nome = user.getText().toString();
        if (senha.length() == 0 || nome.length() == 0){
            Toast.makeText(this, "First, insert a user!", Toast.LENGTH_SHORT).show();
        } else if(RegScreen.users != null && !RegScreen.users.isEmpty()){
            int aux = 0;
            for (int i = 0; i < RegScreen.users.size(); i++) {
                if (senha.equals(RegScreen.users.get(i).getPassword()) && nome.equals(RegScreen.users.get(i).getUsername())) {
                    HomeUser = nome;
                    HomeBalance = RegScreen.users.get(i).getMoney();
                    Intent toHome = new Intent(getApplicationContext(), HomeScreen.class);
                    startActivity(toHome);
                }else{
                    aux++;
                }
                if(RegScreen.users.size() == aux){
                    Toast.makeText(this, "User not found!", Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            Toast.makeText(this, "First, create a user!", Toast.LENGTH_SHORT).show();
        }
    }
}
