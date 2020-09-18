package com.example.contactbook;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import static maes.tech.intentanim.CustomIntent.customType;

public class RegistersActivity extends AppCompatActivity {
    private Connection db;
    private TextView txvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registers);
        txvTitle = findViewById(R.id.txvTitle);
        db = new Connection(this);
        viewData();
    }

    private void viewData(){
        Cursor cursor = db.viewData();
        if(cursor.getCount() == 0) replaceFrag(true);
        else {
            replaceFrag(false);
            txvTitle.setVisibility(View.VISIBLE);
        }
    }

    private void replaceFrag(Boolean empty){
        if(!empty){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.flListView, new ListViewFragment());
            fragmentTransaction.commit();
        }else{
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.flListView, new EmptyDBFragment());
            fragmentTransaction.commit();
        }
    }

    public void txvBack(View view) {
        Intent toHome = new Intent(RegistersActivity.this, HomeActivity.class);
        startActivity(toHome);
        finish();
        customType(RegistersActivity.this, "right-to-left");
    }

    @Override
    public void onBackPressed() {
        Intent toHome = new Intent(RegistersActivity.this, HomeActivity.class);
        startActivity(toHome);
        finish();
        customType(RegistersActivity.this, "right-to-left");
    }
}