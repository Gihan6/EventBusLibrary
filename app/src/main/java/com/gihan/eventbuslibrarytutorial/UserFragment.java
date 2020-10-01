package com.gihan.eventbuslibrarytutorial;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;


public class UserFragment extends Fragment {


    private Button btnSubmit;
    private EditText etMessage;
    private TextView messageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Register the event to subscribe.
        GlobalBus.getBus().register(this);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        setClickListener(view);


    }

    private void initView(View view) {
        btnSubmit = (Button) view.findViewById(R.id.submit);
        etMessage = (EditText) view.findViewById(R.id.editText);
        messageView = (TextView) getView().findViewById(R.id.message);

    }

    public void setClickListener(final View view) {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToMainActivity();
            }
        });
    }

    private void sendDataToMainActivity() {
        // We are broadcasting the message here to listen to the subscriber.
        Events.FragmentActivityMessage fragmentActivityMessageEvent =
                new Events.FragmentActivityMessage(
                        String.valueOf(etMessage.getText()));

        GlobalBus.getBus().post(fragmentActivityMessageEvent);
    }

    @Subscribe
    public void getMessage(Events.ActivityFragmentMessage activityFragmentMessage) {
        //Write code to perform action after event is received.
        messageView.setText(activityFragmentMessage.getMessage());
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        // Unregister the registered event.
        GlobalBus.getBus().unregister(this);

    }
}