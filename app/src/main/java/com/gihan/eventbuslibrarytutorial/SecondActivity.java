package com.gihan.eventbuslibrarytutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Events.ActivityActivityMessage stickyEvent = GlobalBus.getBus().
                getStickyEvent(Events.ActivityActivityMessage.class);

        GlobalBus.getBus().register(this);
    }


    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getMessage(Events.ActivityActivityMessage activityActivityMessage) {
        TextView messageView = (TextView) findViewById(R.id.messageReceived);
        messageView.setText(activityActivityMessage.getMessage());

    }

    @Override
    protected void onStop() {
        super.onStop();
        Events.ActivityActivityMessage stickyEvent = GlobalBus.getBus().
                removeStickyEvent(Events.ActivityActivityMessage.class);


        GlobalBus.getBus().unregister(this);
    }
}