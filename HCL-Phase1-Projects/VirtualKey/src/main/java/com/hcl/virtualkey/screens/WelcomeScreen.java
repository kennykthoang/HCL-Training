package com.hcl.virtualkey.screens;

import com.hcl.virtualkey.services.DirectoryService;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.hcl.virtualkey.services.ScreenService;

public class WelcomeScreen implements Screen {

    private String welcomeText = "Welcome to VirtualKey!";
    private String developerText = "Developer: Kenny Hoang";

    private ArrayList<String> options = new ArrayList<>();


    public WelcomeScreen() {
    	System.out.println(welcomeText);
        System.out.println(developerText);
        //System.out.println("\n");
        
        options.add("1. Show Files");
        options.add("2. Show File Options Menu");
        options.add("3. Quit");

    }
    
    
    @Override
    public void Show()
    {   
    	System.out.println("Main Menu: ");
        for (String s : options)  {
            System.out.println(s);
        }
    }

    @Override
    public int GetUserInput()
    {
        int selectedOption;
        int fileOption = 0;
        while (true) {
        	selectedOption = this.getOption();
            fileOption = ScreenService.getCurrentScreen().NavigateOption(selectedOption);
            if(fileOption == 5)
            	break;
            this.Show();
        }
        return selectedOption;
    }

    @Override
    public int NavigateOption(int option)
    {
        switch(option) {

            case 1: // Show Files
                this.ShowFiles();
                break;
            case 2: // Show Sub-menu
                ScreenService.setCurrentScreen(ScreenService.FileOptionsScreen);
                ScreenService.getCurrentScreen().Show();
                option = ScreenService.getCurrentScreen().GetUserInput();
                break;
            case 3:
            	System.out.println("Exiting program...");
            	System.exit(0);
            	break;
            default:
                System.out.println("Invalid Option! Please try again.");
                break;

        }
        return option;
    }

    public void ShowFiles() {

        //TODO: Get the files from the Directory
        DirectoryService.PrintFiles();
    }
    public void AddFile() {
        System.out.println("Please Enter the Filename:");

        String fileName = this.getInputString();

        System.out.println("You are adding a file named: " + fileName);

    }

    private String getInputString()
    {
    	String input = null;
    	Scanner in = new Scanner(System.in);
    	input = in.nextLine();
        return input;
    }

    public int getOption()
    {
    	System.out.print("Enter your main menu selection: ");
    	Scanner in = new Scanner(System.in);
        int returnOption = 0;
        try {
            returnOption = in.nextInt();
        }
        catch (InputMismatchException ex) {

        }
        return returnOption;

    }
}
