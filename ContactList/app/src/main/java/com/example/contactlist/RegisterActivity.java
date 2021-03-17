package com.example.contactlist;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import com.example.contactlist.Database.ContactDAO;
import com.example.contactlist.Model.Contact;
import com.example.contactlist.Model.Mask;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import static com.example.contactlist.Model.Mask.FORMAT_FONE;
import static com.example.contactlist.Model.Mask.FORMAT_LANDLINE;
import static maes.tech.intentanim.CustomIntent.customType;

public class RegisterActivity extends AppCompatActivity {

    // Attributes

    private TextInputEditText edtName;
    private TextInputEditText edtNumber;
    private TextInputEditText edtEmail;
    private TextInputEditText edtLandline;
    private TextInputEditText edtNickname;

    private TextInputLayout edtLayoutName;
    private TextInputLayout edtLayoutNumber;
    private TextInputLayout edtLayoutEmail;
    private TextInputLayout edtLayoutNickName;
    private TextInputLayout edtLayoutLandLine;

    private ContactDAO contactDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Searching for ids

        edtName = findViewById(R.id.edtName);
        edtNumber = findViewById(R.id.edtNumber);
        edtEmail = findViewById(R.id.edtEmail);
        edtLandline = findViewById(R.id.edtLandline);
        edtNickname = findViewById(R.id.edtNickname);
        edtLayoutName = findViewById(R.id.edtLayoutName);
        edtLayoutNumber = findViewById(R.id.edtLayoutNumber);
        edtLayoutEmail = findViewById(R.id.edtLayoutEmail);
        edtLayoutLandLine = findViewById(R.id.edtLayoutLandLine);
        edtLayoutNickName = findViewById(R.id.edtLayoutNickName);

        // Adding Masks

        edtNumber.addTextChangedListener(Mask.mask(edtNumber, FORMAT_FONE));
        edtLandline.addTextChangedListener(Mask.mask(edtLandline, FORMAT_LANDLINE));

        // Adding listeners to InputTexts may have error

        edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listenerEndIcon(edtName, edtLayoutName);
            }
            @Override
            public void afterTextChanged(Editable s) {
                cleanErrors(edtLayoutName);
            }
        });
        edtNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                cleanErrors(edtLayoutNumber);
            }
        });
        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listenerEndIcon(edtEmail, edtLayoutEmail);
            }

            @Override
            public void afterTextChanged(Editable s) {
                cleanErrors(edtLayoutEmail);
            }
        });
        edtLandline.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listenerEndIcon(edtLandline, edtLayoutLandLine);
            }

            @Override
            public void afterTextChanged(Editable s) {
                cleanErrors(edtLayoutLandLine);
            }
        });
        edtNickname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listenerEndIcon(edtNickname, edtLayoutNickName);
            }

            @Override
            public void afterTextChanged(Editable s) {
                cleanErrors(edtLayoutNickName);
            }
        });

        contactDAO = new ContactDAO(this);
    }

    // Save button

    public void btnSave(View view) {
        validateField(edtName, edtLayoutName);
        validateField(edtNumber, edtLayoutNumber);
        validateField(edtLandline, edtLayoutLandLine);
        validateField(edtNickname, edtLayoutNickName);
        validateField(edtEmail, edtLayoutEmail);
        if(validateField(edtName, edtLayoutName) && validateField(edtNumber, edtLayoutNumber) && validateField(edtLandline, edtLayoutLandLine)
                && validateField(edtNickname, edtLayoutNickName) && validateField(edtEmail, edtLayoutEmail)){

            Contact contact = new Contact(
                    edtName.getText().toString(),
                    edtNumber.getText().toString(),
                    edtEmail.getText().toString(),
                    edtLandline.getText().toString(),
                    edtNickname.getText().toString()
            );

            contactDAO.insertBD(contact);
            this.cleanFields();
            Intent it = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(it);
            customType(RegisterActivity.this, "right-to-left");
            finish();
        }
    }

    // Discard button

    public void btnDiscard(View view) {
        this.dialogDiscard();
    }

    // Methods

    private void auxValidateField(TextInputLayout edtLayout, String message){
        edtLayout.setError(message);
        int[][] states = {{0}};
        int[] colors = {Color.parseColor("#6C6C6C")};
        ColorStateList colorStateList = new ColorStateList(states, colors);
        edtLayout.setCounterTextColor(colorStateList);
    }

    private boolean validateField(TextInputEditText edtField, TextInputLayout edtLayout){

        if(TextUtils.isEmpty(edtField.getText().toString()) && edtField != edtLandline && edtField != edtEmail
                && edtField != edtNickname) {
            this.auxValidateField(edtLayout, "Required field!");
            return false;
        }else{
            if(edtField == edtNumber && edtField.length() != 15){
                this.auxValidateField(edtLayout, "Invalid number, try again!");
                return false;
            }else if(edtField == edtLandline && edtField.length() > 0 && edtField.length() < 14){
                this.auxValidateField(edtLayout, "Invalid number, try again!");
                return false;
            }else if(edtField == edtNumber && contactDAO.countTB(edtField.getText().toString()) > 0){
                this.auxValidateField(edtLayout, "Number already registered, try again!");
                return false;
            }else if((edtField == edtName || edtField == edtEmail || edtField == edtNickname)
                    && edtField.length() > 25){
                if(edtName == edtField) this.auxValidateField(edtLayout, "Invalid name, try again!");
                else if(edtEmail == edtField) this.auxValidateField(edtLayout, "Invalid email, try again!");
                else if(edtNickname == edtField) this.auxValidateField(edtLayout, "Invalid nickname, try again!");
                return false;
            } else return true;
        }
    }

    private boolean verifyField(TextInputEditText edtField){
        if(!TextUtils.isEmpty(edtField.getText().toString())) return true;
        else return false;
    }

    private void listenerEndIcon(TextInputEditText edtField, TextInputLayout edtLayout){
        int[][] states = {{0}};
        int[] colors = {Color.RED, Color.parseColor("#fbcf23")};

        ColorStateList[] csl = new ColorStateList[2];
        csl[0] = new ColorStateList(states, new int[]{colors[0]});
        csl[1] = new ColorStateList(states, new int[]{colors[1]});

        if(edtField.length() > 25) edtLayout.setEndIconTintList(csl[0]);
        else edtLayout.setEndIconTintList(csl[1]);
    }

    private void dialogDiscard(){
        if(this.verifyField(edtName) || this.verifyField(edtNumber) || this.verifyField(edtEmail)
                || this.verifyField(edtLandline) || this.verifyField(edtNickname)){

            // Dialog confirming discard

            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this, R.style.CustomDialog2);
            builder.setTitle("DISCARD").setMessage("Are you sure? This contact won't be saved...").setIcon(R.drawable.ic_baseline_delete_24);
            builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog_bg));
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent it = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(it);
                    customType(RegisterActivity.this, "right-to-left");
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.show();
        }
        else{
            Intent it = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(it);
            customType(RegisterActivity.this, "right-to-left");
            finish();
        }
    }

    private void cleanErrors(TextInputLayout edtLayout){
        int[][] states = {{0}};
        int[] colors = {Color.parseColor("#fbcf23")};
        ColorStateList colorStateList = new ColorStateList(states, colors);
        edtLayout.setError(null);
        edtLayout.setCounterTextColor(colorStateList);
    }

    private void cleanFields(){
        edtName.setText(null);
        edtNumber.setText(null);
        edtEmail.setText(null);
        edtLandline.setText(null);
        edtNickname.setText(null);
    }

    @Override
    public void onBackPressed() {
        this.dialogDiscard();
    }
}