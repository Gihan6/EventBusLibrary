package com.gihan.eventbuslibrarytutorial;

import org.greenrobot.eventbus.EventBus;

//Create a GlobalBus class which returns an instance of EventBus throughout application as below.


public class GlobalBus {
    private static EventBus sBus;
    public static EventBus getBus() {
        if (sBus == null)
            sBus = EventBus.getDefault();
        return sBus;
    }
}
