package filehandling;

import java.util.Scanner;

public class FileHandlerApplication {

	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		String fileName = null, input = null;
		int menuSelection = -1;
		FileHandler filehandler = new FileHandler();
		
		System.out.println("Hello welcome to the file handler application!");
		while(menuSelection != 0)
		{
			do {
				FileHandler.startMenu();
				input = scan.nextLine();
			}
			while(!isInteger(input));
			
			menuSelection = Integer.parseInt(input);
			
			if(menuSelection < 0 || menuSelection > 3)
			{
				System.out.println("Error! Invalid menu selection!\n");
			}
			else if(menuSelection != 0)
			{
				System.out.println("Please input a filename: ");
				fileName = scan.nextLine();
				//scan.nextLine();
			}
			
			switch(menuSelection)
			{
				case 0:	
				{
					System.out.println("Exiting...");
					break;
				}
				case 1:
					filehandler.readFile(fileName);
					break;
				case 2:
				{
					filehandler.createFile(fileName);
					System.out.println("Please input to write to file: ");
					input = scan.nextLine();
					filehandler.writeFile(input, fileName);
					break;
				}
				case 3:
				{
					filehandler.createFile(fileName);
					System.out.println("Appending to \"" + fileName + "\":");
					System.out.println("Please input to write to file: ");
					input = scan.nextLine();
					filehandler.appendToFile(input, fileName);
					break;
				}
			}
			
		}
		scan.close();
	}
	
	// Check if String s is a integer
	// @ param s a String input by the user
	// @ return boolean true or false
	public static boolean isInteger(String s){
		try
		{
			Integer.parseInt(s);
		}
		catch(NumberFormatException e)
		{
			System.out.println(s);
			System.out.println("Please enter an integer only.");
			return false;
		}
		return true;
	} // end isInteger

}
