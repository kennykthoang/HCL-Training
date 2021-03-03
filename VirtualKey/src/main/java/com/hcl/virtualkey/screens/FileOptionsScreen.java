package com.hcl.virtualkey.screens;

import com.hcl.virtualkey.services.DirectoryService;
import com.hcl.virtualkey.services.ScreenService;
// import com.hcl.virtualkey.entities.File;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;


public class FileOptionsScreen implements Screen {

    private ArrayList<String> options = new ArrayList<>();

    public FileOptionsScreen() {
        options.add("1. Add a File");
        options.add("2. Delete A File");
        options.add("3. Search A File");
        options.add("4. Return to Main Menu");
        options.add("5. Quit");

    }

    @Override
    public void Show()
    {
    	System.out.println("File Options Menu: ");
        for (String s : options)  {
            System.out.println(s);
        }

    }

    @Override
    public int GetUserInput()
    {
        int selectedOption;
        int fileOption = 0;
        while (true) 
        {
        	selectedOption = this.getOption();
            fileOption = this.NavigateOption(selectedOption);
            if(fileOption == 5)
            	break;
            ScreenService.getCurrentScreen().Show();
        }
        return selectedOption;
    }

    @Override
    public int NavigateOption(int option)
    {
        switch(option) {

            case 1: // Add File
                this.addFile();
                break;
            case 2: // Delete File
            	this.deleteFile();
            	break;
            case 3: // Search File
            	this.searchFile();
            	break;
            case 4: // Return to Main Menu
            	ScreenService.setCurrentScreen(ScreenService.WelcomeScreen);
                ScreenService.getCurrentScreen().Show();
                ScreenService.getCurrentScreen().GetUserInput();
            	break;
            case 5: // Quit
            	System.out.println("Exiting program...");
            	System.exit(0);
            	break;
            default:
                System.out.println("Invalid Option");
                break;

        }
        return option;
    }

    private void addFile() 
    {
        System.out.print("Please Enter the Filename: ");

        String fileName = this.getInputString();
        
        DirectoryService.getFileDirectory().addFile(new File(fileName));

        System.out.println(fileName + " added.");

    }
    
    private void deleteFile()
    {
    	System.out.println("Please select a file to delete: ");
    	DirectoryService.PrintFiles();
    	System.out.print("Please Enter the Filename: ");

        String fileName = this.getInputString();
        
        DirectoryService.getFileDirectory().deleteFile(new File(fileName));
    }
    
    private void searchFile()
    {
    	System.out.print("Please Enter the Filename: ");

        String fileName = this.getInputString();
        
        if(DirectoryService.getFileDirectory().searchFile(fileName) != null)
        {
        	System.out.println(fileName + " found!");
        }
        else
        {
        	System.out.println("Could not find " + fileName + " in the directory.");
        }
    }
    
    private String getInputString()
    {
    	Scanner in = new Scanner(System.in);
        return(in.nextLine());

    }

    public int getOption()
    {
    	System.out.print("Enter your file menu selection: ");
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
