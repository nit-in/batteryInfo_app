package appbattery.cobalt.com.batteryapp;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.BatteryManager;

import static appbattery.cobalt.com.batteryapp.MainActivity.batteryLevel;
import static appbattery.cobalt.com.batteryapp.MainActivity.chargingCurrent;
import static appbattery.cobalt.com.batteryapp.MainActivity.chargingState;
import static appbattery.cobalt.com.batteryapp.MainActivity.pbar;
import static appbattery.cobalt.com.batteryapp.MainActivity.tempLevel;
import static appbattery.cobalt.com.batteryapp.MainActivity.voltLevel;

public class batInfoReciever extends BroadcastReceiver{


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

            pbar.setBorderColor(Color.rgb(76, 175, 80));//same as charging  ok
            pbar.setWaveColor(Color.rgb(76, 175, 80));
            batteryLevel.setTextColor(Color.rgb(76, 175, 80));
            tempLevel.setTextColor(Color.rgb(76, 175, 80));
            chargingCurrent.setTextColor(Color.rgb(76, 175, 80));
            chargingState.setTextColor(Color.rgb(76, 175, 80));
            voltLevel.setTextColor(Color.rgb(76, 175, 80));

        }else if(level>=65 || level<90){
            pbar.setBorderColor(Color.rgb(139, 195, 74));//light green ok
            pbar.setWaveColor(Color.rgb(139, 195, 74));
            batteryLevel.setTextColor(Color.rgb(139, 195, 74));
            tempLevel.setTextColor(Color.rgb(139, 195, 74));
            chargingCurrent.setTextColor(Color.rgb(139, 195, 74));
            chargingState.setTextColor(Color.rgb(139, 195, 74));
            voltLevel.setTextColor(Color.rgb(139, 195, 74));


        }else if(level>=40 || level <65){
            pbar.setBorderColor(Color.rgb(3, 169, 244));//blue ok
            pbar.setWaveColor(Color.rgb(3, 169, 244));
            batteryLevel.setTextColor(Color.rgb(3, 169, 244));
            tempLevel.setTextColor(Color.rgb(3, 169, 244));
            chargingCurrent.setTextColor(Color.rgb(3, 169, 244));
            chargingState.setTextColor(Color.rgb(3, 169, 244));
            voltLevel.setTextColor(Color.rgb(3, 169, 244));


        }else if (level>=15 || level <40)
        {
            pbar.setBorderColor(Color.rgb(255, 64, 129));//pink  ok
            pbar.setWaveColor(Color.rgb(255, 64, 129));
            batteryLevel.setTextColor(Color.rgb(255, 64, 129));
            tempLevel.setTextColor(Color.rgb(255, 64, 129));
            chargingCurrent.setTextColor(Color.rgb(255, 64, 129));
            chargingState.setTextColor(Color.rgb(255, 64, 129));
           voltLevel.setTextColor(Color.rgb(255, 64, 129));

        }else{
            pbar.setBorderColor(Color.rgb(255, 82, 82));//red ok
            pbar.setWaveColor(Color.rgb(255, 82, 82));
            batteryLevel.setTextColor(Color.rgb(255, 82, 82));
            tempLevel.setTextColor(Color.rgb(255, 82, 82));
            chargingCurrent.setTextColor(Color.rgb(255, 82, 82));
            chargingState.setTextColor(Color.rgb(255, 82, 82));
            voltLevel.setTextColor(Color.rgb(255, 82, 82));

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
            batteryLevel.setTextColor(Color.rgb(76, 175, 80));
            tempLevel.setTextColor(Color.rgb(76, 175, 80));
            chargingCurrent.setTextColor(Color.rgb(76, 175, 80));
            chargingState.setTextColor(Color.rgb(76, 175, 80));
            voltLevel.setTextColor(Color.rgb(76, 175, 80));
            if(usbCharge){
                plugState = "U.S.B.";

            }else if (acCharge){
                plugState= "A.C.";
            }
            else{
                plugState="Unknown";
            }
            MainActivity.chargingState.setText("State : " + str+"("+plugState+")");

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
            MainActivity.chargingState.setText("State : " + str);

        }


        MainActivity.batteryLevel.setText("Battery Level :" + String.valueOf(level) + " %");
        MainActivity.tempLevel.setText("Temp :" + String.valueOf(temps) + " C");
        MainActivity.chargingCurrent.setText("Current :" + current + " mA");
        MainActivity.voltLevel.setText("Voltage :" + String.valueOf(voltage) + " mV");
    }
}
