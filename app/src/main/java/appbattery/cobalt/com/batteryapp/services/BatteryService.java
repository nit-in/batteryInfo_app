package appbattery.cobalt.com.batteryapp.services;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;

public class BatteryService extends Service{
    private static final String TAG = "BatteryStateService";

    private Messenger serviceMessenger = new Messenger((Handler)new IncomingHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return this.serviceMessenger.getBinder();
    }


    @SuppressLint("HandlerLeak")
    private class IncomingHandler extends Handler {

        @Override
        public void handleMessage(final Message msg) {
            switch (msg.what) {

            }
        }
    }
}
