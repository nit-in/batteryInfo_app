package appbattery.cobalt.com.batteryapp;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import me.itangqi.waveloadingview.WaveLoadingView;

public class MainActivity extends AppCompatActivity {
    private TextView batteryLevel;
    private TextView voltLevel;
    private TextView tempLevel;
    private TextView chargingCurrent;
    private TextView chargingState;
    private WaveLoadingView pbar;

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
        registerReceiver(this.batInfoReciver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));


    }

    BroadcastReceiver batInfoReciver = new BroadcastReceiver() {
        @SuppressLint("NewApi")
        @Override
        public void onReceive(Context context, Intent intent) {

            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            // int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            //float batteryPct = level / (float)scale;
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                    status == BatteryManager.BATTERY_STATUS_FULL;
            int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);
            int temp = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
            BatteryManager batman = (BatteryManager) context.getSystemService(Context.BATTERY_SERVICE);
            int amps = batman.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW);
            int current = amps / 1000;
            double temps = (double) temp / 10;
            String str = "";


            if (level>= 90){

                pbar.setBorderColor(Color.rgb(76, 175, 80));//same as charging  ok
                pbar.setWaveColor(Color.rgb(76, 175, 80));

            }else if(level>=65 || level<90){
                pbar.setBorderColor(Color.rgb(139, 195, 74));//light green ok
                pbar.setWaveColor(Color.rgb(139, 195, 74));


            }else if(level>=40 || level <65){
                pbar.setBorderColor(Color.rgb(3, 169, 244));//blue ok
                pbar.setWaveColor(Color.rgb(3, 169, 244));


            }else if (level>=15 || level <40)
            {
                pbar.setBorderColor(Color.rgb(255, 64, 129));//pink  ok
                pbar.setWaveColor(Color.rgb(255, 64, 129));

            }else{
                pbar.setBorderColor(Color.rgb(255, 82, 82));//red ok
                pbar.setWaveColor(Color.rgb(255, 82, 82));

            }

            if (isCharging) {
                str = "Charging";
                pbar.setAmplitudeRatio(60);
                pbar.setAnimDuration(1500);
                pbar.setCenterTitle(str);
                pbar.setProgressValue(level);
                pbar.setCenterTitleSize(55);
                pbar.setCenterTitleColor(Color.rgb(69, 90, 100));
                pbar.startAnimation();
                pbar.setBorderColor(Color.rgb(76, 175, 80));
                pbar.setWaveColor(Color.rgb(76, 175, 80));

            } else {
                str = "Discharging";
                pbar.pauseAnimation();
                pbar.setAnimDuration(3000);
                pbar.setAmplitudeRatio(60);
                pbar.resumeAnimation();
                pbar.setProgressValue(level);
                pbar.setCenterTitleSize(55);
                pbar.setCenterTitle(String.valueOf(level)+ " %");
                pbar.setCenterTitleColor(Color.rgb(69, 90, 100));

            }

            batteryLevel.setText("Battery Level :" + String.valueOf(level) + " %");
            tempLevel.setText("Temp :" + String.valueOf(temps) + " C");
            chargingState.setText("State :" + String.valueOf(str));
            chargingCurrent.setText("Current :" + current + " mA");
            voltLevel.setText("Voltage: " + String.valueOf(voltage) + " mV");




        }
    };
}
