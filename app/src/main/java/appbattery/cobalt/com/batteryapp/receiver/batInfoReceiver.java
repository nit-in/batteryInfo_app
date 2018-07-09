package appbattery.cobalt.com.batteryapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import appbattery.cobalt.com.batteryapp.batStats;

public class batInfoReceiver extends BroadcastReceiver{


    public String plugState=null;
    public String chargingState=null;
    public int level;
    public double temps;
    public int voltage;
    public int current;
    batStats bs = new batStats();

    @Override
    public void onReceive(Context context, Intent intent) {


        level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        // int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        //float batteryPct = level / (float)scale;
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;
        int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
        voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);
        int temp = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
        BatteryManager batman = (BatteryManager) context.getSystemService(Context.BATTERY_SERVICE);
        int amps = batman.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW);
       // int fullVoltage;
       // fullVoltage = intent.getIntExtra(String.valueOf(BatteryManager.BATTERY_PROPERTY_CAPACITY), -1);
        current = amps / 1000;
        temps = (double) temp / 10;
        String str = "";

        if (isCharging) {
            chargingState = "Charging";

            if (usbCharge) {
                plugState = "U.S.B.";

            } else if (acCharge) {
                plugState = "A.C.";
            } else {
                plugState = "Unknown";
            }



        } else {
            chargingState = "Discharging";

        }



        bs.batStat(level,temps,voltage,current,chargingState,plugState);
    }

}
