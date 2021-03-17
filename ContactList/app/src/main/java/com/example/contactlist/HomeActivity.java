package com.example.contactlist;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import com.example.contactlist.Database.ContactDAO;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import static maes.tech.intentanim.CustomIntent.customType;

public class HomeActivity extends AppCompatActivity {

    public static androidx.appcompat.widget.Toolbar toolbar;
    private ContactDAO contactDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Searching for toolbar and adding some important codes

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contactDAO = new ContactDAO(this);

        if(contactDAO.countTB() > 0) this.replaceFragment(false);
        else this.replaceFragment(true);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public void fabNewContact(View view) {
        Intent it = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(it);
        customType(HomeActivity.this, "left-to-right");
        finish();
    }

    private void replaceFragment(Boolean emptyDatabase){
        if(!emptyDatabase){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.flContactList, new FragmentListDatabase());
            fragmentTransaction.commit();
        }else{
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.flContactList, new FragmentEmptyDatabase());
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this, R.style.CustomDialog);
        builder.setTitle("EXIT").setMessage("Are you sure?").setIcon(R.drawable.ic_baseline_exit_to_app_24);
        builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog_bg));
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
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
}