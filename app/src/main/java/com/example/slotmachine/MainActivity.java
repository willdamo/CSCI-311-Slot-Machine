package com.example.slotmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.media.metrics.Event;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView timeView;
    TextView timeView2;
    TextView timeView3;
    Button startButton;
    Button stopButton;
    int time;
    int time2;
    int time3;

    CountEvent event;
    CountEvent2 event2;
    CountEvent3 event3;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeView = findViewById(R.id.timeView);
        timeView2 = findViewById(R.id.timeView2);
        timeView3 = findViewById(R.id.timeView3);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);

        time = 0;
        time2 = 0;
        time3 = 0;

        event = new CountEvent();
        event2 = new CountEvent2();
        event3 = new CountEvent3();

        handler = new Handler();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.postDelayed(event2, 500);
                handler.postDelayed(event, 1000);
                handler.postDelayed(event3, 1500);
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacks(event2);
                handler.removeCallbacks(event);
                handler.removeCallbacks(event3);

                if(time == time2 && time2 == time3){
                    Toast t = Toast.makeText(getApplicationContext(), "WINNER!!!", Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });
    }

    private class CountEvent implements Runnable{
        public void run(){
            timeView.setText(time+"");
            if(time==3){
                time=0;
            }else{
                time++;
            }
            handler.postDelayed(event, 1000);
        }
    }

    private class CountEvent2 implements Runnable{
        public void run(){
            timeView2.setText(time2+"");
            if(time2==3){
                time2=0;
            }else{
                time2++;
            }
            handler.postDelayed(event2, 500);
        }
    }

    private class CountEvent3 implements Runnable{
        public void run(){
            timeView3.setText(time3+"");
            if(time3==3){
                time3=0;
            }else{
                time3++;
            }
            handler.postDelayed(event3, 1500);
        }
    }
}