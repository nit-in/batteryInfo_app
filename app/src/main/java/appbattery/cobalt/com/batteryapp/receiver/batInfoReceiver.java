package appbattery.cobalt.com.batteryapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import appbattery.cobalt.com.batteryapp.R;
import appbattery.cobalt.com.batteryapp.animations.WaveView;
import appbattery.cobalt.com.batteryapp.animations.WaveViewHelper;


public class batInfoReceiver extends BroadcastReceiver{


    public  TextView batteryLevel;
    public  TextView voltLevel;
    public  TextView tempLevel;
    public  TextView chargingCurrent;
    public  TextView chargingState;
    public WaveView pbar;
    public WaveViewHelper wvhelper;
    private int mBorderWidth = 5;



    @Override
    public void onReceive(Context context, Intent intent) {
        wvhelper = new WaveViewHelper(pbar);
        int chargingORGood = ContextCompat.getColor(context,R.color.charging);
        int okLevel = ContextCompat.getColor(context, R.color.okBattery);
        int normalLevel = ContextCompat.getColor(context,R.color.normalBattery);
        int lowLevel = ContextCompat.getColor(context,R.color.lowBattery);
        int criticalLevel = ContextCompat.getColor(context, R.color.criticalBattery);

        int chargingORGoodLite = ContextCompat.getColor(context,R.color.chargingLight);
        int okLevelLite = ContextCompat.getColor(context, R.color.okBatteryLight);
        int normalLevelLite = ContextCompat.getColor(context,R.color.normalBatteryLight);
        int lowLevelLite = ContextCompat.getColor(context,R.color.lowBatteryLight);
        int criticalLevelLite = ContextCompat.getColor(context, R.color.criticalBatteryLight);




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


        if (isCharging) {
            str = "Charging";
            wvhelper.cancel();
            pbar.setBorder(mBorderWidth, chargingORGood);
            pbar.setWaveColor(chargingORGoodLite, chargingORGood);

            if(usbCharge){
                plugState = "U.S.B.";

            }else if (acCharge){
                plugState= "A.C.";
            }
            else{
                plugState="Unknown";
            }
            wvhelper.start();
            wvhelper.setBatteryPercentage(level);
            
           chargingState.setText(str+"("+plugState+")");

        } else {
            wvhelper.cancel();
            str = "Discharging";

            chargingState.setText(str);

            if (level>= 90){
                // color same as charging
                wvhelper.cancel();
                pbar.setBorder(mBorderWidth, chargingORGood);
                pbar.setWaveColor(chargingORGoodLite, chargingORGood);
                wvhelper.start();
                wvhelper.setBatteryPercentage(level);

            }else if(level>=65 || level<90){
                //color ---> okLevel
                wvhelper.cancel();
                pbar.setBorder(mBorderWidth,okLevel );
                pbar.setWaveColor(okLevelLite, okLevel);
                wvhelper.start();
                wvhelper.setBatteryPercentage(level);

            }else if(level>=40 || level <65){
                //color---> normalLevel
                wvhelper.cancel();
                pbar.setBorder(mBorderWidth, normalLevel);
                pbar.setWaveColor(normalLevelLite, normalLevel);
                wvhelper.start();
                wvhelper.setBatteryPercentage(level);

            }else if (level>=15 || level <40)
            {
                // color---> lowLevel
                wvhelper.cancel();
                pbar.setBorder(mBorderWidth,lowLevel );
                pbar.setWaveColor(lowLevelLite, lowLevel);
                wvhelper.setBatteryPercentage(level);
                wvhelper.start();
                wvhelper.setBatteryPercentage(level);

            }else{
                //color---> criticalLevel
                wvhelper.cancel();
                pbar.setBorder(mBorderWidth, criticalLevel);
                pbar.setWaveColor(criticalLevelLite, criticalLevel);
                wvhelper.start();
                wvhelper.setBatteryPercentage(level);

            }

        }


         batteryLevel.setText( String.valueOf(level) + " %");
         tempLevel.setText(String.valueOf(temps) + " C");
         chargingCurrent.setText(current + " mA");
         voltLevel.setText(String.valueOf(voltage) + " mV");
    }
}
