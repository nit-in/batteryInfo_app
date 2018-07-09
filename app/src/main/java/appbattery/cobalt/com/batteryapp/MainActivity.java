package appbattery.cobalt.com.batteryapp;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import appbattery.cobalt.com.batteryapp.receiver.batInfoReceiver;

public class MainActivity extends AppCompatActivity {
    //TODO find a new way to make these available to Broadcast Reciever as this will cause memory leaks

    batInfoReceiver batInfo = new batInfoReceiver();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        batInfo.batteryLevel = findViewById(R.id.textView1);
        batInfo.voltLevel = findViewById(R.id.textView2);
        batInfo.tempLevel = findViewById(R.id.textView3);
        batInfo.chargingCurrent = findViewById(R.id.textView5);
        batInfo.chargingState = findViewById(R.id.textView4);
        batInfo.pbar = findViewById(R.id.progressBar1);
    }


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(batInfo,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        registerReceiver(batInfo,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(batInfo);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        unregisterReceiver(batInfo);


    }

 }
