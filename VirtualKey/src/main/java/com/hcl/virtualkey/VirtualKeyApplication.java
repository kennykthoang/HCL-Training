package com.hcl.virtualkey;


import com.hcl.virtualkey.services.DirectoryService;
import com.hcl.virtualkey.services.ScreenService;

public class VirtualKeyApplication {


    public static String Greeting() {
        return("Hello World!");
    }

    public static void main(String[] args) {

        DirectoryService.AddTestData();
        // DirectoryService.PrintFiles();
        // WelcomeScreen screen = new WelcomeScreen();

        ScreenService.getCurrentScreen().Show();
        ScreenService.getCurrentScreen().GetUserInput();

    }
}
