package com.example.authenticbank;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import java.text.NumberFormat;
import java.util.GregorianCalendar;
import java.util.Locale;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import maes.tech.intentanim.CustomIntent;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView btnv;
    TextView txvNameHome, txvSalutationHome, txvBalance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnv = findViewById(R.id.bottom_navigation);
        txvNameHome = findViewById(R.id.txvNameHome);
        txvSalutationHome = findViewById(R.id.txvSalutationHome);
        txvBalance = findViewById(R.id.txvBalance);
        btnv.setSelectedItemId(R.id.itHome);
        txvNameHome.setText(RegisterActivity.users.get(LoginActivity.userPos).getFirstName());
        txvBalance.setText(toBrazilianCoin(RegisterActivity.users.get(LoginActivity.userPos).getAvailableBalance()));
        setSalutation();
        fragmentSaveMoney(RegisterActivity.users.get(LoginActivity.userPos).isSaveMoneyStatus());
        fragmentCard(RegisterActivity.users.get(LoginActivity.userPos).isCardStatus());

        btnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.itHome:
                        return true;
                    case R.id.itWallet:
                        finish();
                        Intent toWallet = new Intent(getApplicationContext(), WalletActivity.class);
                        startActivity(toWallet);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.itToPay:
                        finish();
                        Intent toToPay = new Intent(getApplicationContext(), ToPayActivity.class);
                        startActivity(toToPay);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.itSettings:
                        finish();
                        Intent toSettings = new Intent(getApplicationContext(), SettingsActivity.class);
                        startActivity(toSettings);
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    public static String toBrazilianCoin(double balance){
        Locale ptBr = new Locale("pt", "BR");
        String newBalance = NumberFormat.getCurrencyInstance(ptBr).format(balance);
        return newBalance;
    }

    public void setSalutation(){
        GregorianCalendar gc = new GregorianCalendar();
        int hour = gc.get(gc.HOUR_OF_DAY);
        if(hour < 12) txvSalutationHome.setText("Good Morning,");
        else if(hour < 19) txvSalutationHome.setText("Good Afternoon,");
        else if(hour < 25) txvSalutationHome.setText("Good Evening,");
    }

    public void fragmentCard(boolean status){
        if(status){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragmentContainer, new WithCardFragment());
            fragmentTransaction.commit();
        }else{
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragmentContainer, new WithoutCardFragment());
            fragmentTransaction.commit();
        }
    }

    public void fragmentSaveMoney(boolean status){
        if(status){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.container_saveMoney, new SaveMoneyFragment());
            fragmentTransaction.commit();
        }
    }

    public void linearSaveMoney(View view) {
        if(RegisterActivity.users.get(LoginActivity.userPos).getAvailableBalance() < 10) {
            DialogError saveMoney = new DialogError("Error!", "You must have at least R$10,00.");
            saveMoney.show(getSupportFragmentManager(), "saveMoney");
        }
        else{
            finish();
            Intent toSaveMoney = new Intent(HomeActivity.this, SaveMoneyActivity.class);
            startActivity(toSaveMoney);
        }
    }

    public void ibProfileHome(View view) {
        Intent toProfile = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(toProfile);
        CustomIntent.customType(HomeActivity.this, "up-to-bottom");
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this, R.style.DialogStyle)
                .setIcon(R.drawable.ic_saida)
                .setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}