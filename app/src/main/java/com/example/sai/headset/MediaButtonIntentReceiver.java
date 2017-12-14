package com.example.sai.headset;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * Created by sai on 13/12/17.
 */

public class MediaButtonIntentReceiver extends BroadcastReceiver {

    public MediaButtonIntentReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast toast = Toast.makeText(context,"Button click send",Toast.LENGTH_SHORT);
        toast.show();
        abortBroadcast();
        String intentAction = intent.getAction();
        if (!Intent.ACTION_MEDIA_BUTTON.equals(intentAction)) {
            toast = Toast.makeText(context,"The Button has error",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        KeyEvent event = (KeyEvent)intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
        if (event == null) {
            return;
        }
        int action = event.getAction();
        if (action == KeyEvent.ACTION_DOWN) {
            // do something
            toast = Toast.makeText(context,"The Button has clicked",Toast.LENGTH_SHORT);
            toast.show();
        }

    }




}
