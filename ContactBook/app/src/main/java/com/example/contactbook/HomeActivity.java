package com.example.contactbook;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    private EditText edtName, edtEmail, edtTelephone, edtPoint, edtMessage;
    private ContactDAO contactDAO;
    private Connection db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtTelephone = findViewById(R.id.edtTelephone);
        edtPoint = findViewById(R.id.edtPoint);
        edtMessage = findViewById(R.id.edtMessage);

        contactDAO = new ContactDAO(this);
        db = new Connection(this);
    }

    public void btnRegister(View view) {

        toValidateField(edtName);
        toValidateField(edtEmail);
        toValidateField(edtTelephone);
        toValidateField(edtPoint);
        toValidateField(edtMessage);

        if(toValidateField(edtName) && toValidateField(edtName) && toValidateField(edtName) && toValidateField(edtName)
                && toValidateField(edtName)){

            String name = edtName.getText().toString();
            String email = edtEmail.getText().toString().trim();
            String telephone = edtTelephone.getText().toString();
            String point = edtPoint.getText().toString();
            String message = edtMessage.getText().toString();

            Boolean registered = db.verifyEmail(email);

            if(!registered){
                Contact contact = new Contact(name, email, telephone, point, message);
                contactDAO.insert(contact);
                toClean();
                Toast.makeText(this, "Contact registered!", Toast.LENGTH_SHORT).show();
            }else{
                edtEmail.setText("");
                edtEmail.setError("Email already registered");
            }

        }
    }

    private boolean toValidateField(EditText edtField){
        if(!TextUtils.isEmpty(edtField.getText().toString())) return true;
        else {
            edtField.setError("Insert this field");
            edtField.requestFocus();
            return false;
        }
    }

    private void toClean(){
        edtName.setText("");
        edtEmail.setText("");
        edtTelephone.setText("");
        edtPoint.setText("");
        edtMessage.setText("");
    }

    public void txvAllRegisters(View view) {
        Intent toRegister = new Intent(HomeActivity.this, RegistersActivity.class);
        startActivity(toRegister);
        finish();
    }

    public void txvDeleteRegisters(View view) {
        Intent toRegister = new Intent(HomeActivity.this, DeleteRegistersActivity.class);
        startActivity(toRegister);
        finish();
    }
}