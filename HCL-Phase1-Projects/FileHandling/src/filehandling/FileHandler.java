package filehandling;


import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class FileHandler
{
	// Local variables
	private File myFile;

	// Methods
    public File createFile(String fileName)
    {

        try {
            myFile = new File(fileName);
            if (myFile.exists()) {
                System.out.println("File " + fileName + " already exists, new file not created.");
                return (myFile);
            }

            myFile.createNewFile();
            System.out.println("New file: " + fileName + " created.");

        }
        catch( Exception ex) {
            System.err.println(ex.getMessage());
        }
        return myFile;
    }
    
    public boolean readFile(String fileName) {
        try 
        {
        	BufferedReader br = new BufferedReader(new FileReader(fileName));
        	String str;
        	System.out.println("Below are the contents of \""+ fileName + "\":");
        	while((str = br.readLine()) != null)
        	{
        		System.out.println(str);
        	}
        	br.close();
        }
        catch(IOException iox)
        {
        	System.err.println(iox.getMessage());
        }
        return true;
    }
    
    public boolean writeFile(String text, String fileName) {

        try (PrintWriter pw = new PrintWriter(
                Files.newBufferedWriter(Paths.get(fileName)))) {
            // Stream.of(text).forEach(pw::println);
        	pw.println(text);
        }
        catch (IOException  ex) {
            System.err.println(ex.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean appendToFile(String text, String fileName)
    {
    	try{
    		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
    		pw.println(text);
    		pw.close();
    		System.out.println("Text sucessfully appended to file " + fileName);
    		
    	}
    	catch(IOException ex)
    	{
    		System.err.println(ex.getMessage());
    		return false;
    	}
    	return true;
    }
    
    public static void startMenu(){
		System.out.println("\nFile Handler Menu:\n 1. Read in a file.\n 2. Write to a file.\n 3. Append to a file.\n"
				+ " Press \"0\" to exit.");
	}
}