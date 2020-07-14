package com.example.authenticbank;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import maes.tech.intentanim.CustomIntent;
import static com.example.authenticbank.HomeActivity.toBrazilianCoin;

public class IncreaseLimitActivity extends AppCompatActivity {
    CheckBox cbTermIncreaseLimit;
    EditText edtNewLimit;
    TextView txvCurrentLimit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_increase_limit);
        cbTermIncreaseLimit = findViewById(R.id.cbTermIncreaseLimit);
        edtNewLimit = findViewById(R.id.edtNewLimit);
        txvCurrentLimit = findViewById(R.id.txvCurrentLimit);
        cbTermIncreaseLimit.setChecked(false);
        txvCurrentLimit.setText(toBrazilianCoin(RegisterActivity.users.get(LoginActivity.userPos).getCardLimit()));
    }

    public void btnIncrease(View view) {
        if(edtNewLimit.length() == 0) {
            DialogError someAmount = new DialogError("Error!", "Put a new limit to continue.");
            someAmount.show(getSupportFragmentManager(), "someAmount");
            cbTermIncreaseLimit.setChecked(false);
        }else{
            if(!cbTermIncreaseLimit.isChecked()) Toast.makeText(this, "You need to accept the term", Toast.LENGTH_SHORT).show();
            else{
                if(Double.parseDouble(edtNewLimit.getText().toString()) > 10000) {
                    DialogError limit = new DialogError("Error!", "Limits greater than R$ 10.000,00 are not allowed.");
                    limit.show(getSupportFragmentManager(), "limit");
                    edtNewLimit.setText("");
                    cbTermIncreaseLimit.setChecked(false);
                }
                else{
                   if(Double.parseDouble(edtNewLimit.getText().toString()) < RegisterActivity.users.get(LoginActivity.userPos).getCardLimit()) {
                       DialogError lowerLimit = new DialogError("Error!", "You can not lower your limit.");
                       lowerLimit.show(getSupportFragmentManager(), "lowerLimit");
                       edtNewLimit.setText("");
                       cbTermIncreaseLimit.setChecked(false);
                   }
                   else{
                       RegisterActivity.users.get(LoginActivity.userPos).setCardLimit(Double.parseDouble(edtNewLimit.getText().toString()));
                       DialogSuccessful successful = new DialogSuccessful("Successful", "Amount saved successfully.");
                       successful.show(getSupportFragmentManager(), "successful");
                       txvCurrentLimit.setText(toBrazilianCoin(RegisterActivity.users.get(LoginActivity.userPos).getCardLimit()));
                       edtNewLimit.setText("");
                       cbTermIncreaseLimit.setChecked(false);
                   }
                }
            }
        }
    }

    public void ibProfileIl(View view) {
        Intent toProfile = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(toProfile);
        CustomIntent.customType(IncreaseLimitActivity.this, "up-to-bottom");
    }

    public void txvBackToHome(View view) {
        finish();
        Intent backToHome = new Intent(IncreaseLimitActivity.this, HomeActivity.class);
        startActivity(backToHome);
        CustomIntent.customType(IncreaseLimitActivity.this, "right-to-left");
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent backToHome = new Intent(IncreaseLimitActivity.this, HomeActivity.class);
        startActivity(backToHome);
        CustomIntent.customType(IncreaseLimitActivity.this, "right-to-left");
    }
}