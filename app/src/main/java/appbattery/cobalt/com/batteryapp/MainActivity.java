package appbattery.cobalt.com.batteryapp;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import appbattery.cobalt.com.batteryapp.animations.WaveView;
import appbattery.cobalt.com.batteryapp.receiver.batInfoReceiver;
import appbattery.cobalt.com.batteryapp.batStats;

public class MainActivity extends AppCompatActivity {

    //TODO find a new way to make these available to Broadcast Reciever as this will cause memory leaks

    public static   TextView batteryLevel;
    public static TextView voltLevel;
    public  static TextView tempLevel;
    public static TextView chargingCurrent;
    public  static TextView chargingState;
    public WaveView pbar;

    batInfoReceiver batInfo = new batInfoReceiver();
    batStats bt = new batStats();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        batteryLevel = findViewById(R.id.textView1);
        voltLevel = findViewById(R.id.textView2);
        tempLevel = findViewById(R.id.textView3);
        chargingCurrent = findViewById(R.id.textView5);
        chargingState = findViewById(R.id.textView4);
        pbar = findViewById(R.id.progressBar1);
    }



    @Override
    protected void onResume() {
        super.onResume();
        this.registerReceiver(batInfo,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        this.registerReceiver(batInfo,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.unregisterReceiver(batInfo);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(batInfo);


    }


}
