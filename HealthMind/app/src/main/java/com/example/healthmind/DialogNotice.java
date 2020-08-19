package com.example.healthmind;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogNotice extends DialogFragment implements DialogInterface.OnClickListener {

    // Properties
    private String message, type;

    // Constructor
    public DialogNotice(String message, String type) {
        this.setMessage(message);
        this.setType(type);
    }

    // Methods
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DialogStyle);
        builder.setMessage("\n" + this.getMessage())
               .setPositiveButton("Ok", this);
        if(this.getType().equals("Error")){
            builder.setTitle("Error");
            builder.setIcon(R.drawable.ic_error);
        }else if(this.getType().equals("Success")){
            builder.setTitle("Success");
            builder.setIcon(R.drawable.ic_success);
        }
        return builder.create();
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}
