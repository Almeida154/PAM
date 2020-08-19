package com.example.healthmind;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.ArrayList;

public class MyAppointmentsActivity extends AppCompatActivity {
    public static ArrayList<String> listPatients, listAppointments;
    public static int item = 0;
    Spinner spnFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appointments);
        spnFilter = findViewById(R.id.spnFilter);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.filter, R.layout.spinner_color);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spnFilter.setAdapter(adapter);
        spnFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.flListView, new NoFilterFragment());
                        fragmentTransaction.commit();
                        break;
                    case 1:
                        listPatients = new ArrayList<>();
                        if(RegisterPatientActivity.patients != null && !RegisterPatientActivity.patients.isEmpty()){
                            for(int i = 0; i < RegisterPatientActivity.patients.size(); i++)
                                listPatients.add(RegisterPatientActivity.patients.get(i).toString());
                            item = 1;
                            replaceFrag();
                        }
                        break;
                    case 2:
                        listAppointments = new ArrayList<>();
                        if(NewAppointmentActivity.appointments != null && !NewAppointmentActivity.appointments.isEmpty()){
                            for(int i = 0; i < NewAppointmentActivity.appointments.size(); i++)
                                listAppointments.add(NewAppointmentActivity.appointments.get(i).toString());
                            item = 2;
                            replaceFrag();
                        }
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void replaceFrag(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flListView, new ListViewFragment());
        fragmentTransaction.commit();
    }
}