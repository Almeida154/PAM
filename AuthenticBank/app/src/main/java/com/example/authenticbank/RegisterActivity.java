package com.example.authenticbank;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import maes.tech.intentanim.CustomIntent;

public class RegisterActivity extends AppCompatActivity {
    public static ArrayList<User> users;
    EditText edtFirstNameReg, edtLastNameReg, edtEmailReg, edtPasswordReg, edtRepeatPasswordReg;
    CheckBox cbTermsReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtFirstNameReg = findViewById(R.id.edtFirstNameReg);
        edtLastNameReg = findViewById(R.id.edtLastNameReg);
        edtEmailReg = findViewById(R.id.edtEmailReg);
        edtPasswordReg = findViewById(R.id.edtPasswordReg);
        edtRepeatPasswordReg = findViewById(R.id.edtRepeatPasswordReg);
        cbTermsReg = findViewById(R.id.cbTermsReg);
        if(VerifyArray.isFirstTime()) {
            users = new ArrayList<>();
            VerifyArray.setFirstTime(false);
        }
    }

    public void btnCreateAccount(View view) {
        String fn = edtFirstNameReg.getText().toString();
        String ln = edtLastNameReg.getText().toString();
        String em = edtEmailReg.getText().toString();
        String pswrd = edtPasswordReg.getText().toString();
        String repeatPswrd = edtRepeatPasswordReg.getText().toString();
        if(fn.trim().isEmpty() && ln.trim().isEmpty() && em.trim().isEmpty() && pswrd.trim().isEmpty() && repeatPswrd.trim().isEmpty()){
            edtFirstNameReg.setError("Insert all fields");
            edtLastNameReg.setError("Insert all fields");
            edtEmailReg.setError("Insert all fields");
            edtPasswordReg.setError("Insert all fields");
            edtRepeatPasswordReg.setError("Insert all fields");
            return;
        }else if(ln.trim().isEmpty() && em.trim().isEmpty() && pswrd.trim().isEmpty() && repeatPswrd.trim().isEmpty()){
            edtLastNameReg.setError("Insert all fields");
            edtEmailReg.setError("Insert all fields");
            edtPasswordReg.setError("Insert all fields");
            edtRepeatPasswordReg.setError("Insert all fields");
            return;
        }else if(em.trim().isEmpty() && pswrd.trim().isEmpty() && repeatPswrd.trim().isEmpty()){
            edtEmailReg.setError("Insert all fields");
            edtPasswordReg.setError("Insert all fields");
            edtRepeatPasswordReg.setError("Insert all fields");
            return;
        }else if(pswrd.trim().isEmpty() && repeatPswrd.trim().isEmpty()){
            edtPasswordReg.setError("Insert all fields");
            edtRepeatPasswordReg.setError("Insert all fields");
            return;
        }else if(repeatPswrd.trim().isEmpty()){
            edtRepeatPasswordReg.setError("Insert all fields");
            return;
        }else{
            if(pswrd.equals(repeatPswrd)){
                if(!cbTermsReg.isChecked()){
                    cbTermsReg.setError("Accept our terms");
                }else{
                    if(em.length() > 9){
                        boolean registered = false;
                        for(int i = 0; i < users.size(); i++){
                            if(em.equals(users.get(i).getEmail())) registered = true;
                        }
                        if(!registered){
                            users.add(new User(fn, ln, em, pswrd));
                            clean();
                            Toast.makeText(this, "User registered! Back to Login.", Toast.LENGTH_SHORT).show();
                        }else{
                            DialogError alreadyCreated = new DialogError("Error!", "Email already registered.");
                            alreadyCreated.show(getSupportFragmentManager(), "differentPasswords");
                            clean();
                        }
                    }else{
                        DialogError email10 = new DialogError("Error!", "Email must be at least 10 characters.");
                        email10.show(getSupportFragmentManager(), "email10");
                        edtEmailReg.setText("");
                    }
                }
            }else{
                DialogError alreadyCreated = new DialogError("Error!", "Different passwords.");
                alreadyCreated.show(getSupportFragmentManager(), "differentPasswords");
            }
        }
    }

    public void clean(){
        edtFirstNameReg.setText("");
        edtLastNameReg.setText("");
        edtEmailReg.setText("");
        edtPasswordReg.setText("");
        edtRepeatPasswordReg.setText("");
        cbTermsReg.setChecked(false);
    }

    public void txvBackToLogin(View view) {
        finish();
        Intent toLogin = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(toLogin);
        CustomIntent.customType(RegisterActivity.this, "right-to-left");
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent toLogin = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(toLogin);
        CustomIntent.customType(RegisterActivity.this, "right-to-left");
    }
}