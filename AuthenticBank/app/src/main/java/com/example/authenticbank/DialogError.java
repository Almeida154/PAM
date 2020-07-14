package com.example.authenticbank;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogError extends DialogFragment implements DialogInterface.OnClickListener {

    String title, message;

    public DialogError(String title, String message) {
        this.setTitle(title);
        this.setMessage(message);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DialogStyle);
        builder.setTitle(this.getTitle())
               .setIcon(R.drawable.ic_error)
               .setMessage(this.getMessage())
               .setPositiveButton("Ok", this);
        return builder.create();
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    @Override
    public void onClick(DialogInterface dialog, int which) { }
}
