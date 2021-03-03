package com.hcl.virtualkey.services;

import com.hcl.virtualkey.screens.FileOptionsScreen;
import com.hcl.virtualkey.screens.Screen;
import com.hcl.virtualkey.screens.WelcomeScreen;

public class ScreenService {

    public static WelcomeScreen WelcomeScreen = new WelcomeScreen();
    public static FileOptionsScreen FileOptionsScreen = new FileOptionsScreen();



    public static Screen CurrentScreen = WelcomeScreen;

    public static Screen getCurrentScreen() {
        return CurrentScreen;
    }

    public static void setCurrentScreen(Screen currentScreen) {
        CurrentScreen = currentScreen;
    }
}
