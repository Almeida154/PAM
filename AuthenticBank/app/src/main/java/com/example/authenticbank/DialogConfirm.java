package com.example.authenticbank;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogConfirm extends DialogFragment implements DialogInterface.OnClickListener {

    String title, message, savemoneyORcreditcard;

    public DialogConfirm(String title, String message, String savemoneyORcreditcard) {
        this.setTitle(title);
        this.setMessage(message);
        this.setSavemoneyORcreditcard(savemoneyORcreditcard);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DialogStyle);
        builder.setTitle(this.getTitle())
               .setIcon(R.drawable.ic_confirm)
               .setMessage(this.getMessage())
               .setPositiveButton("Yes", this)
               .setNegativeButton("Cancel", this);
        return builder.create();
    }

    public String getTitle() { return this.title; }

    public void setTitle(String title) { this.title = title; }

    public String getMessage() { return this.message; }

    public void setMessage(String message) { this.message = message; }

    public String getSavemoneyORcreditcard() { return this.savemoneyORcreditcard; }

    public void setSavemoneyORcreditcard(String savemoneyORcreditcard) { this.savemoneyORcreditcard = savemoneyORcreditcard; }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which == DialogInterface.BUTTON_POSITIVE){
            switch(this.getSavemoneyORcreditcard()) {
                case "saveMoney":
                    RegisterActivity.users.get(LoginActivity.userPos).setBalance(
                            RegisterActivity.users.get(LoginActivity.userPos).getAvailableBalance() + RegisterActivity.users.get(LoginActivity.userPos).getSaveMoney()
                    );
                    RegisterActivity.users.get(LoginActivity.userPos).setSaveMoneyStatus(false);
                    break;
                case "creditCard":
                    if(RegisterActivity.users.get(LoginActivity.userPos).getCardInvoice() > 0){
                        Toast.makeText(getActivity(), "Pay your invoice first", Toast.LENGTH_SHORT).show();
                    }else{
                        RegisterActivity.users.get(LoginActivity.userPos).setCardStatus(false);
                        RegisterActivity.users.get(LoginActivity.userPos).setCardLimit(0);
                        RegisterActivity.users.get(LoginActivity.userPos).setCardInvoice(0);
                    }
                    break;
            }
        }
    }
}
