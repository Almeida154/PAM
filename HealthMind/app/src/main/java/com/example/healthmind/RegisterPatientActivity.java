package com.example.healthmind;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;

public class RegisterPatientActivity extends AppCompatActivity {
    public static ArrayList<Patient> patients;
    EditText edtName, edtCpf, edtEmail;
    Spinner spnGender;
    String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_patient);
        edtName = findViewById(R.id.edtName);
        edtCpf = findViewById(R.id.edtCpf);
        edtEmail = findViewById(R.id.edtEmail);
        spnGender = findViewById(R.id.spnGender);
        edtCpf.addTextChangedListener(MaskEditUtil.mask(edtCpf, MaskEditUtil.FORMAT_CPF));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.genders, R.layout.spinner_color);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spnGender.setAdapter(adapter);

        spnGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        gender = "Nothing";
                        break;
                    case 1:
                        gender = "Male";
                        break;
                    case 2:
                        gender = "Female";
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        if(VerifyPatientsArrayList.isFirstTime()) {
            patients = new ArrayList<>();
            VerifyPatientsArrayList.setFirstTime(false);
        }
    }

    public void btnRegister(View view) {
        toValidateField(edtName);
        toValidateField(edtEmail);
        toValidateField(edtCpf);
        if(toValidateField(edtName) && toValidateField(edtEmail) && toValidateField(edtCpf)) {
            if(gender.equals("Nothing")) Toast.makeText(this, "Enter a gender!", Toast.LENGTH_SHORT).show();
            else {
                if(edtEmail.getText().toString().length() < 10) {
                    edtEmail.setError("Email too short!");
                    edtEmail.requestFocus();
                }
                else if(edtCpf.getText().toString().length() < 14){
                    edtCpf.setError("Cpf too short!");
                    edtCpf.requestFocus();
                } else {
                    boolean registered = false;
                    for(int i = 0; i < patients.size(); i++) {
                        if(edtCpf.getText().toString().equals(patients.get(i).getCpf()))
                            registered = true;
                    }
                    if(!registered) {
                        String cpf = edtCpf.getText().toString();
                        patients.add(new Patient(edtName.getText().toString(), gender, edtEmail.getText().toString(), cpf));
                        DialogNotice dialogNotice = new DialogNotice("User registered!", "Success");
                        dialogNotice.show(getSupportFragmentManager(), "success");
                        clean();
                    } else {
                        edtCpf.setError("Cpf already registered!");
                        edtCpf.requestFocus();
                        edtCpf.setText("");
                    }
                }
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

    private void clean(){
        edtName.setText("");
        edtEmail.setText("");
        edtCpf.setText("");
        spnGender.setSelection(0);
    }
}