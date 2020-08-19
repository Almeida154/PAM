package com.example.healthmind;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void btnRegisterPatient(View view) {
        Intent toRegisterPatient = new Intent(getApplicationContext(), RegisterPatientActivity.class);
        startActivity(toRegisterPatient);
    }

    public void btnNewAppointment(View view) {
        if(RegisterPatientActivity.patients != null && !RegisterPatientActivity.patients.isEmpty()){
            Intent toNewAppointment = new Intent(getApplicationContext(), NewAppointmentActivity.class);
            startActivity(toNewAppointment);
        } else {
            DialogNotice dialogNotice = new DialogNotice( "You must need to create a patient first!", "Error");
            dialogNotice.show(getSupportFragmentManager(), "error");
        }
    }

    public void btnMyAppointments(View view) {
        if((RegisterPatientActivity.patients != null && !RegisterPatientActivity.patients.isEmpty()) &&
                (NewAppointmentActivity.appointments != null && !NewAppointmentActivity.appointments.isEmpty())){
            Intent toMyAppointments = new Intent(getApplicationContext(), MyAppointmentsActivity.class);
            startActivity(toMyAppointments);
        } else {
            DialogNotice dialogNotice = new DialogNotice("You haven't appointment yet!", "Error");
            dialogNotice.show(getSupportFragmentManager(), "error");
        }
    }
}