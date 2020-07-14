package com.example.authenticbank;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogExchanger extends DialogFragment implements DialogInterface.OnClickListener {

    String title, emailORpassword;

    public DialogExchanger(String title, String emailORpassword) {
        this.setTitle(title);
        this.setEmailORpassword(emailORpassword);
    }
    private EditText edtExchanger;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DialogStyle);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);
        builder.setView(view)
               .setIcon(R.drawable.ic_escudo)
               .setTitle(this.getTitle())
               .setPositiveButton("To change", this)
               .setNegativeButton("Cancel", this);
        edtExchanger = view.findViewById(R.id.edtExchanger);
        switch(getEmailORpassword()){
            case "email":
                edtExchanger.setHint("Your new email");
                edtExchanger.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                break;
            case "password": edtExchanger.setHint("Your new password");
                break;
        }
        return builder.create();
    }

    public String getTitle() { return this.title; }

    public void setTitle(String title) { this.title = title; }

    public String getEmailORpassword() { return this.emailORpassword; }

    public void setEmailORpassword(String emailORpassword) { this.emailORpassword = emailORpassword; }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which == DialogInterface.BUTTON_POSITIVE){
            switch(getEmailORpassword()){
                case "email":
                    if((edtExchanger.getText().toString()).length() == 0) Toast.makeText(getActivity(), "Put a valid email", Toast.LENGTH_SHORT).show();
                    else{
                        if((edtExchanger.getText().toString()).length() > 9){
                            boolean registered = false;
                            for(int i = 0; i < RegisterActivity.users.size(); i++){
                                if(edtExchanger.getText().toString().equals(RegisterActivity.users.get(i).getEmail())) registered = true;
                            }
                            if(!registered){
                                RegisterActivity.users.get(LoginActivity.userPos).setEmail(edtExchanger.getText().toString());
                                Toast.makeText(getActivity(), "Email successfully exchanged", Toast.LENGTH_SHORT).show();
                                SettingsActivity.txvEmailSettings.setText(edtExchanger.getText().toString());
                            }else Toast.makeText(getActivity(), "Email already registered, try again", Toast.LENGTH_SHORT).show();
                        }else Toast.makeText(getActivity(), "Email must be at least 10 characters", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case "password":
                    if((edtExchanger.getText().toString()).length() == 0) Toast.makeText(getActivity(), "Put a valid password", Toast.LENGTH_SHORT).show();
                    else{
                        RegisterActivity.users.get(LoginActivity.userPos).setPassword(edtExchanger.getText().toString());
                        Toast.makeText(getActivity(), "Password successfully exchanged", Toast.LENGTH_SHORT).show();
                        SettingsActivity.txvPasswordSettings.setText(edtExchanger.getText().toString());
                    }
                    break;
            }
        }
    }
}
