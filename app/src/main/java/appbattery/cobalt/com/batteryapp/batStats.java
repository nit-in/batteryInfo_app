package appbattery.cobalt.com.batteryapp;
import android.app.Application;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import appbattery.cobalt.com.batteryapp.animations.WaveView;
import appbattery.cobalt.com.batteryapp.animations.WaveViewHelper;
import appbattery.cobalt.com.batteryapp.receiver.batInfoReceiver;
public class batStats {
    
    

    public void batStat(int Level, Double Temp, int Voltage, int Current, String ChargingState, String plugState) {
        
       /**  int chargingORGood = ContextCompat.getColor(c.getApplicationContext(), R.color.charging);
         int okLevel = ContextCompat.getColor(c.getApplicationContext(), R.color.okBattery);
         int normalLevel = ContextCompat.getColor(c.getApplicationContext(), R.color.normalBattery);
         int lowLevel = ContextCompat.getColor(c.getApplicationContext(), R.color.lowBattery);
         int criticalLevel = ContextCompat.getColor(c.getApplicationContext(), R.color.criticalBattery);

         int chargingORGoodLite = ContextCompat.getColor(c.getApplicationContext(), R.color.chargingLight);
         int okLevelLite = ContextCompat.getColor(c.getApplicationContext(), R.color.okBatteryLight);
         int normalLevelLite = ContextCompat.getColor(c.getApplicationContext(), R.color.normalBatteryLight);
         int lowLevelLite = ContextCompat.getColor(c.getApplicationContext(), R.color.lowBatteryLight);
         int criticalLevelLite = ContextCompat.getColor(c.getApplicationContext(), R.color.criticalBatteryLight);
         
        wvhelper = new WaveViewHelper(pbar);
        if (Level >= 90) {
            // color same as charging
            wvhelper.cancel();
            pbar.setBorder(mBorderWidth, chargingORGood);
            pbar.setWaveColor(chargingORGoodLite, chargingORGood);
            wvhelper.start();
            wvhelper.setBatteryPercentage(Level);

        } else if (Level >= 65 || Level < 90) {
            //color ---> okLevel
            wvhelper.cancel();
            pbar.setBorder(mBorderWidth, R.color.okBattery);
            pbar.setWaveColor(R.color.okBatteryLight, R.color.okBattery);
            wvhelper.start();
            wvhelper.setBatteryPercentage(Level);

        } else if (Level >= 40 || Level < 65) {
            //color---> normalLevel
            wvhelper.cancel();
            pbar.setBorder(mBorderWidth, R.color.normalBattery);
            pbar.setWaveColor( R.color.normalBatteryLight,  R.color.normalBattery);
            wvhelper.start();
            wvhelper.setBatteryPercentage(Level);

        } else if (Level >= 15 || Level < 40) {
            // color---> lowLevel
            wvhelper.cancel();
            pbar.setBorder(mBorderWidth,  R.color.lowBattery);
            pbar.setWaveColor( R.color.lowBatteryLight,R.color.lowBattery);
            wvhelper.start();
            wvhelper.setBatteryPercentage(Level);

        } else {
            //color---> criticalLevel
            wvhelper.cancel();
            pbar.setBorder(mBorderWidth,R.color.criticalBattery);
            pbar.setWaveColor(R.color.criticalBatteryLight, R.color.criticalBattery);
            wvhelper.start();
            wvhelper.setBatteryPercentage(Level);

        }
        **/
        MainActivity.batteryLevel.setText("Battery Level :" + String.valueOf(Level) + " %");
        MainActivity.tempLevel.setText("Temp :" + String.valueOf(Temp) + " C");
        MainActivity.chargingCurrent.setText("Current :" + Current + " mA");
        MainActivity.voltLevel.setText("Voltage :" + String.valueOf(Voltage) + " mV");
        MainActivity.chargingState.setText("State : " + ChargingState + "(" + plugState + ")");
    
    }
    
}
