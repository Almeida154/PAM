package com.example.authenticbank;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import maes.tech.intentanim.CustomIntent;

public class LoginActivity extends AppCompatActivity {
    EditText edtEmailLog, edtPasswordLog;
    public static int userPos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtEmailLog = findViewById(R.id.edtEmailLog);
        edtPasswordLog = findViewById(R.id.edtPasswordLog);
    }

    public void btnLogin(View view) {
        String email = edtEmailLog.getText().toString();
        String password = edtPasswordLog.getText().toString();
        if(email.length() == 0 || password.length() == 0){
            Toast.makeText(this, "Insert a user, please!", Toast.LENGTH_SHORT).show();
        }else if(RegisterActivity.users != null && !RegisterActivity.users.isEmpty()){
            int aux = 0;
            for(int i = 0; i < RegisterActivity.users.size(); i++){
                if(email.equals(RegisterActivity.users.get(i).getEmail()) && password.equals(RegisterActivity.users.get(i).getPassword())){
                    userPos = i;
                    Intent toHome = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(toHome);
                    edtEmailLog.setText("");
                    edtPasswordLog.setText("");
                }else{
                    aux++;
                }
                if(RegisterActivity.users.size() == aux) {
                    Toast.makeText(this, "Account not found!", Toast.LENGTH_SHORT).show();
                    edtEmailLog.setText("");
                    edtPasswordLog.setText("");
                }
            }
        }else{
            Toast.makeText(this, "First, create an account!", Toast.LENGTH_SHORT).show();
            edtEmailLog.setText("");
            edtPasswordLog.setText("");
        }
    }

    public void txvCreateAnAccount(View view) {
        finish();
        Intent toRegisterScreen = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(toRegisterScreen);
        CustomIntent.customType(LoginActivity.this, "left-to-right");
    }
}