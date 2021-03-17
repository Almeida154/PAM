package com.example.contactlist;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import com.example.contactlist.Database.ContactDAO;
import com.example.contactlist.Model.Contact;
import com.example.contactlist.Model.Mask;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.List;
import static com.example.contactlist.Model.Mask.FORMAT_FONE;
import static com.example.contactlist.Model.Mask.FORMAT_LANDLINE;
import static maes.tech.intentanim.CustomIntent.customType;

public class UpdateActivity extends AppCompatActivity {

    // Attributes

    private TextInputEditText edtNameUp;
    private TextInputEditText edtNumberUp;
    private TextInputEditText edtEmailUp;
    private TextInputEditText edtLandlineUp;
    private TextInputEditText edtNicknameUp;

    private TextInputLayout edtLayoutNameUp;
    private TextInputLayout edtLayoutNumberUp;
    private TextInputLayout edtLayoutEmailUp;
    private TextInputLayout edtLayoutNickNameUp;
    private TextInputLayout edtLayoutLandLineUp;

    private ContactDAO contactDAO;
    private String numberContact = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        // Searching for ids

        edtNameUp = findViewById(R.id.edtNameUp);
        edtNumberUp = findViewById(R.id.edtNumberUp);
        edtEmailUp = findViewById(R.id.edtEmailUp);
        edtLandlineUp = findViewById(R.id.edtLandlineUp);
        edtNicknameUp = findViewById(R.id.edtNicknameUp);
        edtLayoutNameUp = findViewById(R.id.edtLayoutNameUp);
        edtLayoutNumberUp = findViewById(R.id.edtLayoutNumberUp);
        edtLayoutEmailUp = findViewById(R.id.edtLayoutEmailUp);
        edtLayoutLandLineUp = findViewById(R.id.edtLayoutLandLineUp);
        edtLayoutNickNameUp = findViewById(R.id.edtLayoutNickNameUp);

        // Adding Masks

        edtNumberUp.addTextChangedListener(Mask.mask(edtNumberUp, FORMAT_FONE));
        edtLandlineUp.addTextChangedListener(Mask.mask(edtLandlineUp, FORMAT_LANDLINE));

        // Adding listeners to InputTexts may have error

        edtNameUp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listenerEndIcon(edtNameUp, edtLayoutNameUp);
            }
            @Override
            public void afterTextChanged(Editable s) {
                cleanErrors(edtLayoutNameUp);
            }
        });
        edtNumberUp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                cleanErrors(edtLayoutNumberUp);
            }
        });
        edtEmailUp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listenerEndIcon(edtEmailUp, edtLayoutEmailUp);
            }

            @Override
            public void afterTextChanged(Editable s) {
                cleanErrors(edtLayoutEmailUp);
            }
        });
        edtLandlineUp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listenerEndIcon(edtLandlineUp, edtLayoutLandLineUp);
            }

            @Override
            public void afterTextChanged(Editable s) {
                cleanErrors(edtLayoutLandLineUp);
            }
        });
        edtNicknameUp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listenerEndIcon(edtNicknameUp, edtLayoutNickNameUp);
            }

            @Override
            public void afterTextChanged(Editable s) {
                cleanErrors(edtLayoutNickNameUp);
            }
        });

        contactDAO = new ContactDAO(this);

        Intent it = getIntent();
        Bundle b = it.getExtras();
        this.numberContact = (String) (b != null ? b.getString("numberContact") : null);
        List<Contact> contact = contactDAO.selectDB(numberContact);

        edtNameUp.setText(contact.get(0).getNameContact());
        edtNumberUp.setText(contact.get(0).getNumberContact());
        edtEmailUp.setText(contact.get(0).getEmailContact());
        edtLandlineUp.setText(contact.get(0).getLandlineContact());
        edtNicknameUp.setText(contact.get(0).getNicknameContact());

    }

    // Save button

    public void btnUpdate(View view) {
        validateField(edtNameUp, edtLayoutNameUp);
        validateField(edtNumberUp, edtLayoutNumberUp);
        validateField(edtLandlineUp, edtLayoutLandLineUp);
        validateField(edtNicknameUp, edtLayoutNickNameUp);
        validateField(edtEmailUp, edtLayoutEmailUp);
        if(validateField(edtNameUp, edtLayoutNameUp) && validateField(edtNumberUp, edtLayoutNumberUp) && validateField(edtLandlineUp, edtLayoutLandLineUp)
                && validateField(edtNicknameUp, edtLayoutNickNameUp) && validateField(edtEmailUp, edtLayoutEmailUp)){

            Contact contact = new Contact(
                    edtNameUp.getText().toString(),
                    edtNumberUp.getText().toString(),
                    edtEmailUp.getText().toString(),
                    edtLandlineUp.getText().toString(),
                    edtNicknameUp.getText().toString()
            );

            contactDAO.updateDB(contact, this.numberContact);
            this.cleanFields();
            Intent it = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(it);
            customType(UpdateActivity.this, "right-to-left");
            finish();
        }
    }

    // Discard button

    public void btnCancel(View view) {
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
        if(TextUtils.isEmpty(edtField.getText().toString()) && edtField != edtLandlineUp && edtField != edtLandlineUp
                && edtField != edtEmailUp && edtField != edtNicknameUp) {
            this.auxValidateField(edtLayout, "Required field!");
            return false;
        }else{
            if(edtField == edtNumberUp && edtField.length() != 15){
                this.auxValidateField(edtLayout, "Invalid number, try again!");
                return false;
            }else if(edtField == edtLandlineUp && edtField.length() > 0 && edtField.length() < 14){
                this.auxValidateField(edtLayoutLandLineUp, "Invalid number, try again!");
                return false;
            }else if(edtField == edtNumberUp && contactDAO.countTB(edtField.getText().toString()) > 0
                    && !contactDAO.selectDB(edtField.getText().toString()).get(0).getNameContact().equals(this.edtNameUp.getText().toString())){
                this.auxValidateField(edtLayout, "Number already registered, try again!");
                return false;
            }else if((edtField == edtNameUp || edtField == edtEmailUp || edtField == edtNicknameUp)
                    && edtField.length() > 25) {
                if (edtNameUp == edtField)
                    this.auxValidateField(edtLayout, "Invalid name, try again!");
                else if (edtEmailUp == edtField)
                    this.auxValidateField(edtLayout, "Invalid email, try again!");
                else if (edtNicknameUp == edtField)
                    this.auxValidateField(edtLayout, "Invalid nickname, try again!");
                return false;
            }else return true;
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
        if(this.verifyField(edtNameUp) || this.verifyField(edtNumberUp) || this.verifyField(edtEmailUp)
                || this.verifyField(edtLandlineUp) || this.verifyField(edtNicknameUp)){

            // Dialog confirming discard

            String title = "<h1><font>DISCARD?</font></h1>";
            String message = "<big><font>Are you sure? This contact won't be updated...</font></big>";

            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this, R.style.CustomDialog2);
            builder.setTitle(Html.fromHtml(title)).setMessage(Html.fromHtml(message));
            builder.setIcon(R.drawable.ic_baseline_delete_24);
            builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog_bg));
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent it = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(it);
                    customType(UpdateActivity.this, "right-to-left");
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
            customType(UpdateActivity.this, "right-to-left");
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
        edtNameUp.setText(null);
        edtNumberUp.setText(null);
        edtEmailUp.setText(null);
        edtLandlineUp.setText(null);
        edtNicknameUp.setText(null);
    }

    @Override
    public void onBackPressed() {
        this.dialogDiscard();
    }
}