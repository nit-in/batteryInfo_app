package appbattery.cobalt.com.batteryapp;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.BatteryManager;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import me.itangqi.waveloadingview.WaveLoadingView;

public class batInfoReceiver extends BroadcastReceiver{


    public  TextView batteryLevel;
    public  TextView voltLevel;
    public  TextView tempLevel;
    public  TextView chargingCurrent;
    public  TextView chargingState;
    public WaveLoadingView pbar;
    @Override
    public void onReceive(Context context, Intent intent) {


        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        // int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        //float batteryPct = level / (float)scale;
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;
        int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
        int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);
        int temp = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
        BatteryManager batman = (BatteryManager) context.getSystemService(Context.BATTERY_SERVICE);
        int amps = batman.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW);
        int current = amps / 1000;
        double temps = (double) temp / 10;
        String str = "";
        String plugState ="";


        if (level>= 90){

            pbar.setBorderColor(ContextCompat.getColor(context,R.color.charging));//same as charging  ok
            pbar.setWaveColor(ContextCompat.getColor(context,R.color.charging));
            batteryLevel.setTextColor(ContextCompat.getColor(context,R.color.charging));
            tempLevel.setTextColor(ContextCompat.getColor(context,R.color.charging));
            chargingCurrent.setTextColor(ContextCompat.getColor(context,R.color.charging));
            chargingState.setTextColor(ContextCompat.getColor(context,R.color.charging));
            voltLevel.setTextColor(ContextCompat.getColor(context,R.color.charging));

        }else if(level>=65 || level<90){
            pbar.setBorderColor(ContextCompat.getColor(context,R.color.okBattery));//light green ok
            pbar.setWaveColor(ContextCompat.getColor(context,R.color.okBattery));
            batteryLevel.setTextColor(ContextCompat.getColor(context,R.color.okBattery));
            tempLevel.setTextColor(ContextCompat.getColor(context,R.color.okBattery));
            chargingCurrent.setTextColor(ContextCompat.getColor(context,R.color.okBattery));
            chargingState.setTextColor(ContextCompat.getColor(context,R.color.okBattery));
            voltLevel.setTextColor(ContextCompat.getColor(context,R.color.okBattery));


        }else if(level>=40 || level <65){
            pbar.setBorderColor(ContextCompat.getColor(context,R.color.normalBattery));//blue ok
            pbar.setWaveColor(ContextCompat.getColor(context,R.color.normalBattery));
            batteryLevel.setTextColor(ContextCompat.getColor(context,R.color.normalBattery));
            tempLevel.setTextColor(ContextCompat.getColor(context,R.color.normalBattery));
            chargingCurrent.setTextColor(ContextCompat.getColor(context,R.color.normalBattery));
            chargingState.setTextColor(ContextCompat.getColor(context,R.color.normalBattery));
            voltLevel.setTextColor(ContextCompat.getColor(context,R.color.normalBattery));


        }else if (level>=15 || level <40)
        {
            pbar.setBorderColor(ContextCompat.getColor(context,R.color.lowBattery));//pink  ok
            pbar.setWaveColor(ContextCompat.getColor(context,R.color.lowBattery));
            batteryLevel.setTextColor(ContextCompat.getColor(context,R.color.lowBattery));
            tempLevel.setTextColor(ContextCompat.getColor(context,R.color.lowBattery));
            chargingCurrent.setTextColor(ContextCompat.getColor(context,R.color.lowBattery));
            chargingState.setTextColor(ContextCompat.getColor(context,R.color.lowBattery));
            voltLevel.setTextColor(ContextCompat.getColor(context,R.color.lowBattery));

        }else{
            pbar.setBorderColor(ContextCompat.getColor(context,R.color.criticalBattery));//red ok
            pbar.setWaveColor(ContextCompat.getColor(context,R.color.criticalBattery));
            batteryLevel.setTextColor(ContextCompat.getColor(context,R.color.criticalBattery));
            tempLevel.setTextColor(ContextCompat.getColor(context,R.color.criticalBattery));
            chargingCurrent.setTextColor(ContextCompat.getColor(context,R.color.criticalBattery));
            chargingState.setTextColor(ContextCompat.getColor(context,R.color.criticalBattery));
            voltLevel.setTextColor(ContextCompat.getColor(context,R.color.criticalBattery));

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
            pbar.setBorderColor(ContextCompat.getColor(context,R.color.charging));
            pbar.setWaveColor(ContextCompat.getColor(context,R.color.charging));
            batteryLevel.setTextColor(ContextCompat.getColor(context,R.color.charging));
            tempLevel.setTextColor(ContextCompat.getColor(context,R.color.charging));
            chargingCurrent.setTextColor(ContextCompat.getColor(context,R.color.charging));
            chargingState.setTextColor(ContextCompat.getColor(context,R.color.charging));
            voltLevel.setTextColor(ContextCompat.getColor(context,R.color.charging));
            if(usbCharge){
                plugState = "U.S.B.";

            }else if (acCharge){
                plugState= "A.C.";
            }
            else{
                plugState="Unknown";
            }
           chargingState.setText("State : " + str+"("+plugState+")");

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
            chargingState.setText("State : " + str);

        }


         batteryLevel.setText("Battery Level :" + String.valueOf(level) + " %");
         tempLevel.setText("Temp :" + String.valueOf(temps) + " C");
         chargingCurrent.setText("Current :" + current + " mA");
         voltLevel.setText("Voltage :" + String.valueOf(voltage) + " mV");
    }
}
