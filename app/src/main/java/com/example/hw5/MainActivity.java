package com.example.hw5;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private long timeWhenStopped = 0;
    Chronometer chrono;
    ScrollView scroll;
    Button start, pause, record, conti, reset;
    LinearLayout linear;
    int index = 0;
    LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams( //리니어 생성
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    public void textViewCreate(String time) {
        parms.setMargins(10, 10, 10, 10);
        TextView markText = new TextView(this);//textview 객체생성
        markText.setLayoutParams(parms);
        markText.setGravity(Gravity.CENTER);
        markText.setTextSize(25);
        markText.setText(time);
        linear.addView(markText, 0);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chrono = (Chronometer) findViewById(R.id.chrono);
        scroll = (ScrollView) findViewById(R.id.scroll);
        start = (Button) findViewById(R.id.start);
        pause = (Button) findViewById(R.id.pause);
        record = (Button) findViewById(R.id.record);
        conti = (Button) findViewById(R.id.conti);
        reset = (Button) findViewById(R.id.reset);
        linear = (LinearLayout) findViewById(R.id.linear);


        linear.setVisibility(View.INVISIBLE);
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear.setVisibility(View.VISIBLE);
                textViewCreate(chrono.getText().toString()); //함수호출
                index++;
                scroll.fullScroll(ScrollView.FOCUS_UP);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 0;
                chrono.setBase(SystemClock.elapsedRealtime());
                chrono.start();
                start.setVisibility(View.INVISIBLE);
                pause.setVisibility(View.VISIBLE);
                record.setVisibility(View.VISIBLE);

            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeWhenStopped = chrono.getBase() - SystemClock.elapsedRealtime();
                chrono.stop();
                pause.setVisibility(View.INVISIBLE);
                record.setVisibility(View.INVISIBLE);
                conti.setVisibility(View.VISIBLE);
                reset.setVisibility(View.VISIBLE);

            }
        });
        conti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chrono.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                chrono.start();
                conti.setVisibility(View.INVISIBLE);
                reset.setVisibility(View.INVISIBLE);
                pause.setVisibility(View.VISIBLE);
                record.setVisibility(View.VISIBLE);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chrono.setBase(SystemClock.elapsedRealtime());
                timeWhenStopped = 0;
                pause.setVisibility(View.GONE);
                record.setVisibility(View.GONE);
                conti.setVisibility(View.GONE);
                reset.setVisibility(View.GONE);
                start.setVisibility(View.VISIBLE);
                linear.removeAllViews();

            }
        });


    }

}