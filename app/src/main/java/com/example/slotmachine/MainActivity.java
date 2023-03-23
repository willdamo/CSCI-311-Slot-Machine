package com.example.slotmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.media.metrics.Event;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView slotImage;
    ImageView slotImage2;
    ImageView slotImage3;
    Drawable cherry;
    Drawable grape;
    Drawable pear;
    Drawable strawberry;
    Button startButton;
    Button stopButton;
    Button restartButton;
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

        slotImage = findViewById(R.id.slotImage);
        slotImage2 = findViewById(R.id.slotImage2);
        slotImage3 = findViewById(R.id.slotImage3);

        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);
        restartButton = findViewById(R.id.restartButton);

        cherry = getDrawable(R.drawable.cherry);
        grape = getDrawable(R.drawable.grape);
        pear = getDrawable(R.drawable.pear);
        strawberry = getDrawable(R.drawable.strawberry);

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
                handler.postDelayed(event, 700);
                handler.postDelayed(event3, 900);
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacks(event2);
                handler.removeCallbacks(event);
                handler.removeCallbacks(event3);

                if(slotImage.getDrawable().equals(slotImage2.getDrawable()) && slotImage2.getDrawable().equals(slotImage3.getDrawable())){
                    Toast t = Toast.makeText(getApplicationContext(), "WINNER!!!", Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time = 0;
                time2 = 0;
                time3 = 0;

                handler.postDelayed(event2, 500);
                handler.postDelayed(event, 700);
                handler.postDelayed(event3, 900);
            }
        });
    }

    private class CountEvent implements Runnable{
        public void run(){
            if(time==3){
                time=0;
            }else if(time==2){
                time++;
                slotImage.setImageDrawable(strawberry);
            }else if(time==1){
                time++;
                slotImage.setImageDrawable(pear);
            }else if(time==0){
                time++;
                slotImage.setImageDrawable(grape);
            }
            handler.postDelayed(event, 250);
        }
    }

    private class CountEvent2 implements Runnable{
        public void run(){
            if(time2==3){
                time2=0;
            }else if(time2==2){
                time2++;
                slotImage2.setImageDrawable(strawberry);
            }else if(time2==1){
                time2++;
                slotImage2.setImageDrawable(pear);
            }else if(time2==0){
                time2++;
                slotImage2.setImageDrawable(grape);
            }
            handler.postDelayed(event2, 500);
        }
    }

    private class CountEvent3 implements Runnable{
        public void run(){
            if(time3==3){
                time3=0;
                slotImage3.setImageDrawable(cherry);
            }else if(time3==2){
                time3++;
                slotImage3.setImageDrawable(strawberry);
            }else if(time3==1){
                time3++;
                slotImage3.setImageDrawable(pear);
            }else if(time3==0){
                time3++;
                slotImage3.setImageDrawable(grape);
            }

            handler.postDelayed(event3, 750);
        }
    }
}