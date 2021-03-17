package com.example.rightnowrgb;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class IndexActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor accelerometer;
    LinearLayout linearLayout;
    Button btnSelect;
    int r, g, b;
    int h, s, v;
    boolean canChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(IndexActivity.this, accelerometer, 200000);
        linearLayout = findViewById(R.id.ll);
        btnSelect = findViewById(R.id.btnSelect);
        canChange = true;

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float[] axes = new float[]{sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]};

        int h = getHSV((float) (axes[0] / 1.3), 360, 7, 7);
        int s = getHSV((float) (axes[0] / 1.3), 100, 7, 7);
        int v = getHSV((float) (axes[1] / 1.3), 100, 9, 2);

        /*
        if(axes[1] > 0) { v = (int) (axes[1] * 50 / 8) - 50; v *= -1; }
        else {
            float toPos = axes[1] * -1;
            v = (int) (toPos * 50 / 2) + 50;
        }
        */

        if(h >= 360) h = 360;
        if(s >= 100) s = 100;
        if(v >= 100) v = 100;

        if(h < 1) h = 1;
        if(s < 1) s = 1;
        if(v < 1) v = 1;

        float[] hsv = new float[]{h, s, v};
        final int currentColor = Color.HSVToColor(hsv);

        this.h = (int) hsv[0];
        this.s = (int) hsv[1];
        this.v = (int) hsv[2];

        final String hex = getHEXbyHSV(h, s, v);
        int i = Integer.decode(hex.toUpperCase());
        final int[] rgb = new int[]{(i >> 16) & 0xFF, (i >> 8) & 0xFF, i & 0xFF};

        this.r = Color.red(currentColor);
        this.g = Color.green(currentColor);
        this.b = Color.blue(currentColor);

        if(canChange) {
            linearLayout.setBackgroundColor(Color.argb(130, Color.red(currentColor), Color.green(currentColor), Color.blue(currentColor)));
            getWindow().setStatusBarColor(Color.argb(90, Color.red(currentColor), Color.green(currentColor), Color.blue(currentColor)));
            btnSelect.setTextColor(Color.argb(255, Color.red(currentColor), Color.green(currentColor), Color.blue(currentColor)));
        }
        btnSelect.setElevation(15);
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog();
            }
        });
    }

    public void dialog(){
        canChange = false;

        final String hex = getHEXbyHSV(h, s, v);
        int i = Integer.decode(hex.toUpperCase());
        final int[] rgb = new int[]{(i >> 16) & 0xFF, (i >> 8) & 0xFF, i & 0xFF};

        String colors = "<font color=\""+ String.format("#%02x%02x%02x", this.r, this.g, this.b) +"\">It has been selected!</font><br><br>" +
                "<big><font color=\"#FFFFFF\">RGB: </font>"
                + "<font color=\"#8E8E8E\">" + rgb[0] + ", " + rgb[1] + ", " + rgb[2] + "</font><br>"
                + "<font color=\"#FFFFFF\">HSV: </font>"
                + "<font color=\"#8E8E8E\">" + this.h + "°, " + this.s + "%, " + this.v + "%</font><br>"
                + "<font color=\"#FFFFFF\">HEX: </font>"
                + "<font color=\"#8E8E8E\">" + hex + "</font><br>";

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this, R.style.CustomDialog);
        builder.setTitle(Html.fromHtml("<h1><font>YOUR COLOR</font>"));
        builder.setMessage(Html.fromHtml(colors));
        builder.setBackground(getResources().getDrawable(R.drawable.alertbg));
        builder.setIcon(R.drawable.ic_pallete);
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                canChange = true;
            }
        });
        builder.setPositiveButton("All right", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                canChange = true;
            }
        });
        builder.setNegativeButton("Copy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                canChange = true;
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText(
                        "lable", "RGB: " + rgb[0] + ", " + rgb[1] + ", " + rgb[2] +
                                " | HSV: " + h + ", " + s + ", " + v + " | HEX: " + hex
                );
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(IndexActivity.this, "Copied :)", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }

    private int getHSV(float axis, int measure, int maxInclination, double minInclination){
        float value;
        if(axis > 0) { value = (int) (axis * (measure / 2) / maxInclination) - (measure / 2); value *= -1; return (int) value; }
        else {
            float toPos = axis * -1;
            value = (int) (toPos * (measure / 2) / minInclination) + (measure / 2);
            return (int) value;
        }
    }
    private String getHEXbyHSV(float h, float s, float v) {
        float r, g, b;

        h /= 360f;
        s /= 100f;
        v /= 100f;

        if (s == 0) {
            r = v * 255;
            g = v * 255;
            b = v * 255;
        }else{
            float var_h = h * 6;
            if(var_h == 6) var_h = 0;
            int var_i = (int) Math.floor((double) var_h);

            float var_1 = v * (1 - s);
            float var_2 = v * (1 - s * (var_h - var_i));
            float var_3 = v * (1 - s * (1 - (var_h - var_i)));

            float var_r;
            float var_g;
            float var_b;

            switch(var_i){
                case 0:
                    var_r = v;
                    var_g = var_3;
                    var_b = var_1;
                    break;
                case 1:
                    var_r = var_2;
                    var_g = v;
                    var_b = var_1;
                    break;
                case 2:
                    var_r = var_1;
                    var_g = v;
                    var_b = var_3;
                    break;
                case 3:
                    var_r = var_1;
                    var_g = var_2;
                    var_b = v;
                    break;
                case 4:
                    var_r = var_3;
                    var_g = var_1;
                    var_b = v;
                    break;
                default:
                    var_r = v;
                    var_g = var_1;
                    var_b = var_2;
            }
            r = var_r * 255;
            g = var_g * 255;
            b = var_b * 255;
        }

        String rs = Integer.toHexString((int) (r));
        String gs = Integer.toHexString((int) (g));
        String bs = Integer.toHexString((int) (b));

        if (rs.length() == 1) rs = "0" + rs;
        if (gs.length() == 1) gs = "0" + gs;
        if (bs.length() == 1) bs = "0" + bs;
        return "#" + rs + gs + bs;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onBackPressed() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this, R.style.CustomDialog);
        builder.setTitle(Html.fromHtml("<h1><font>EXIT</font>"));
        builder.setMessage(Html.fromHtml("<font><big>Are you sure?</big></font>"));
        builder.setBackground(getResources().getDrawable(R.drawable.alertbg));
        builder.setIcon(R.drawable.ic_pallete);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.show();
    }


}