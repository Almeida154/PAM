package com.example.healthmind;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;

public class NewAppointmentActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    public static ArrayList<Appointment> appointments;
    public static ArrayList<Schedule> schedules;
    private static final int NOTIFICATION_ID = 50;
    EditText edtCpfAppointment, edtDescription;
    TextView txvPrice, txvDate, txvHour;
    String currentDay = null, dateSchedule = null, timeSchedule = null;
    SeekBar sbPrice;
    Boolean validHourAppointment = false, validDate = false, validHour = false, sameAppointment, userFound = false;
    int dayNumber, monthNumber, yearNumber, hourNumber, minuteNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_appointment);
        edtCpfAppointment = findViewById(R.id.edtCpfAppointment);
        edtDescription = findViewById(R.id.edtDescription);
        txvPrice = findViewById(R.id.txvPrice);
        txvDate = findViewById(R.id.txvDate);
        txvHour = findViewById(R.id.txvHour);
        sbPrice = findViewById(R.id.sbPrice);
        txvPrice.setText("(R$50,00)");
        edtCpfAppointment.addTextChangedListener(MaskEditUtil.mask(edtCpfAppointment, MaskEditUtil.FORMAT_CPF));
        sbPrice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txvPrice.setText("(R$ "+ progress +",00)");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        if(VerifyAppointmentArrayList.isFirstTime()) {
            appointments = new ArrayList<>();
            schedules = new ArrayList<>();
            VerifyAppointmentArrayList.setFirstTime(false);
        }
    }

    public void txvDate(View view) {
        DialogFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String days[] = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        this.currentDay = days[c.get(Calendar.DAY_OF_WEEK) - 1];
        this.dayNumber = c.get(Calendar.DAY_OF_MONTH);
        this.monthNumber = c.get(Calendar.MONTH) + 1;
        this.yearNumber = c.get(Calendar.YEAR);
        String dayC = Integer.toString(dayNumber);
        String monthC = Integer.toString(monthNumber);
        String yearC = Integer.toString(yearNumber);
        if(dayOfMonth < 10) dayC = "0" + dayC;
        if(month < 10) monthC = "0" + monthC;
        dateSchedule = dayC + "/" + monthC + "/" + yearC;
        txvDate.setText(dateSchedule + "  (Click to change it)");
    }

    public void txvHour(View view) {
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "timePicker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String hour = Integer.toString(hourOfDay);
        String min = Integer.toString(minute);
        if(hourOfDay < 10) hour = "0" + hour;
        if(minute < 10) min = "0" + min;
        this.hourNumber = hourOfDay;
        this.minuteNumber = minute;
        this.timeSchedule = hour + ":" + min;
        txvHour.setText(timeSchedule + "  (Click to change it)");
    }

    public void btnSchedule(View view) {

        if(txvDate.getText().toString().equals("Enter the appointment's date...")) this.validDate = false;
        else validDate = true;

        if(txvHour.getText().toString().equals("Enter the appointment's hour...")) this.validHour = false;
        else validHour = true;

        toValidateField(edtCpfAppointment);
        toValidateField(edtDescription);

        if(!validDate || !validHour){
            if(!validDate){
                Toast.makeText(this, "Enter a date!", Toast.LENGTH_SHORT).show();
            }
            if(!validHour){
                Toast.makeText(this, "Enter a hour!", Toast.LENGTH_SHORT).show();
            }
        }else if(toValidateField(edtCpfAppointment) && toValidateField(edtDescription)){
            if(this.hourNumber < 9 || this.hourNumber > 21) validHourAppointment = false;
            else validHourAppointment = true;
            if(!currentDay.equals("Sunday") && this.validHourAppointment){
                String cpf = edtCpfAppointment.getText().toString();
                String desc = edtDescription.getText().toString();
                int price = sbPrice.getProgress();
                if(edtCpfAppointment.getText().toString().length() == 14){
                    for(Patient patient : RegisterPatientActivity.patients){
                        if(cpf.equals(patient.getCpf())){
                            this.userFound = true;
                            if(schedules != null && !schedules.isEmpty()){
                                for(int i = 0; i < schedules.size(); i++){
                                    if(this.monthNumber == schedules.get(i).getMonth() && this.dayNumber == schedules.get(i).getDay()){

                                        if(schedules.get(i).getMinute() > 0){
                                            if(this.hourNumber > schedules.get(i).getHour() && this.minuteNumber >
                                                    schedules.get(i).getMinute()) this.sameAppointment = false;
                                            else this.sameAppointment = true;
                                        }

                                        else if(this.hourNumber == schedules.get(i).getHour()) this.sameAppointment = true;
                                        else this.sameAppointment = false;

                                    } else this.sameAppointment = false;
                                }

                                if(sameAppointment){
                                    DialogNotice dialogNotice = new DialogNotice("Sorry, we already have an appointment at this time!", "Error");
                                    dialogNotice.show(getSupportFragmentManager(), "error");
                                }
                                else newAppointment(patient, price, desc);

                            } else newAppointment(patient, price, desc);
                        }
                    }

                    if(!this.userFound){
                        DialogNotice dialogNotice = new DialogNotice("User not found!", "Error");
                        dialogNotice.show(getSupportFragmentManager(), "error");
                    }

                } else {
                    edtCpfAppointment.setError("Cpf too short!");
                    edtCpfAppointment.requestFocus();
                }
            }

            if(currentDay.equals("Sunday")) {
                DialogNotice dialogNotice = new DialogNotice("Day unavailable!", "Error");
                dialogNotice.show(getSupportFragmentManager(), "error");
            }
            if(!this.validHourAppointment) {
                DialogNotice dialogNotice = new DialogNotice("I don't work this time!", "Error");
                dialogNotice.show(getSupportFragmentManager(), "error");
            }
        }
    }

    private void newAppointment(final Patient patient, final int price, final String desc){
        new AlertDialog.Builder(this, R.style.DialogStyle)
                .setIcon(R.drawable.ic_confirm)
                .setTitle("Confirm")
                .setMessage("\nName: " + patient.getName() + "\n" +
                            "Date: " + this.dateSchedule + " (" + this.currentDay + ")\n" +
                            "Hour: " + this.timeSchedule + "\n" +
                            "Price: R$" + price + ",00")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        patient.setAppointments(patient.getAppointments() + 1);
                        patient.setSpent(patient.getSpent() + price);
                        appointments.add(new Appointment(patient, dateSchedule, timeSchedule, desc, currentDay));
                        schedules.add(new Schedule(dayNumber, monthNumber, hourNumber, minuteNumber));
                        Toast.makeText(getApplicationContext(), "Schedule registered!", Toast.LENGTH_SHORT).show();
                        clean();
                    }
                })
                .show();
        viewNotification(patient);
    }

    private boolean toValidateField(EditText edtField){
        if(!TextUtils.isEmpty(edtField.getText().toString())) return true;
        else {
            edtField.setError("Insert this field");
            edtField.requestFocus();
            return false;
        }
    }

    public void viewNotification(Patient patient){
        Intent notificationIntent = new Intent(this, MyAppointmentsActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this, NOTIFICATION_ID, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(this, com.example.healthmind.Notification.getChannelId(this));
        builder.setContentTitle("New appointment scheduled")
               .setContentText("With " + patient.getName() + " at " + this.timeSchedule + " hours.\nDate: " + this.dateSchedule)
               .setSmallIcon(R.drawable.ic_iconapp)
               .setAutoCancel(true)
               .setContentIntent(notificationPendingIntent);
        Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if(notificationManager != null){
            notificationManager.notify(NOTIFICATION_ID, notification);
        }
    }

    private  void clean(){
        edtCpfAppointment.setText("");
        txvDate.setText("Enter the appointment's date...");
        txvHour.setText("Enter the appointment's hour...");
        edtDescription.setText("");
        sbPrice.setProgress(50);
    }
}