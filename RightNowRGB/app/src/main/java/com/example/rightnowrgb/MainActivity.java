package com.example.rightnowrgb;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txvAppName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txvAppName = findViewById(R.id.txvAppName);

        /* stronger
        String html = "<span><font color=\"#FF0000\">R</font></span>" +
                "<span><font color=\"#EB0013\">i</font></span>" +
                "<span><font color=\"#D70027\">g</font></span>" +
                "<span><font color=\"#C4003A\">h</font></span>" +
                "<span><font color=\"#B0004E\">t</font></span>" +
                "<span><font color=\"#890075\"> N</font></span>" +
                "<span><font color=\"#750089\">o</font></span>" +
                "<span><font color=\"#62009C\">w</font></span>" +
                "<span><font color=\"#3A00C4\"> R</font></span>" +
                "<span><font color=\"#2700D7\">G</font></span>" +
                "<span><font color=\"#1300EB\">B</font></span>";
        */

        String html = "<span><font color=\"#FF6363\">R</font></span>" +
                "<span><font color=\"#F6646F\">i</font></span>" +
                "<span><font color=\"#EE667B\">g</font></span>" +
                "<span><font color=\"#E56887\">h</font></span>" +
                "<span><font color=\"#DD6A93\">t</font></span>" +
                "<span><font color=\"#CC6DAB\"> N</font></span>" +
                "<span><font color=\"#C36FB7\">o</font></span>" +
                "<span><font color=\"#BB71C3\">w</font></span>" +
                "<span><font color=\"#AA74DB\"> R</font></span>" +
                "<span><font color=\"#A176E7\">G</font></span>" +
                "<span><font color=\"#9978F3\">B</font></span>";
        txvAppName.setText(Html.fromHtml(html));

        // Splash

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent it = new Intent(getApplicationContext(), IndexActivity.class);
                startActivity(it);
                finish();
            }
        }, 1000);
    }
}