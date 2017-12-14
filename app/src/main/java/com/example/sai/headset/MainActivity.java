package com.example.sai.headset;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    MediaSession audioSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv;
        tv =(TextView)findViewById(R.id.text1);
        audioSession = new MediaSession(getApplicationContext(), "TAG");
        audioSession.setCallback(new MediaSession.Callback() {

            @Override
            public boolean onMediaButtonEvent(final Intent mediaButtonIntent) {
                String intentAction = mediaButtonIntent.getAction();

                if (Intent.ACTION_MEDIA_BUTTON.equals(intentAction))
                {
                    KeyEvent event = (KeyEvent)mediaButtonIntent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
                    if (event != null)
                    {
                        int action = event.getAction();
                        if (action == KeyEvent.ACTION_DOWN) {
                            long stopTimeOfGame_millis = System.currentTimeMillis();
                            Context context = getApplicationContext();
                            Toast toast = Toast.makeText(context,"The Button Receiver has been unregistered",Toast.LENGTH_SHORT);
                            toast.show();

                        }
                       /* if (action == KeyEvent.ACTION_UP) {
                            long test = System.currentTimeMillis();
                            UtilsRG.info("time stopped up: " +test);

                        }*/
                    }

                }
                return super.onMediaButtonEvent(mediaButtonIntent);
            }



        });

        PlaybackState state = new PlaybackState.Builder()
                .setActions(PlaybackState.ACTION_PLAY_PAUSE)
                .setState(PlaybackState.STATE_PLAYING, 0, 0, 0)
                .build();
        audioSession.setPlaybackState(state);

        audioSession.setFlags(MediaSession.FLAG_HANDLES_MEDIA_BUTTONS | MediaSession.FLAG_HANDLES_TRANSPORT_CONTROLS);

        audioSession.setActive(true);
    }

    protected void onStop() {
        super.onStop();

        // In the onStop method you should unregister the receiver
        audioSession.release();
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context,"The Button Receiver has been unregistered",Toast.LENGTH_SHORT);
        toast.show();
    }

}
