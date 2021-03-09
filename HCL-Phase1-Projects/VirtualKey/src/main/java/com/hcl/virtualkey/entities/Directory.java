package com.hcl.virtualkey.entities;

import java.util.ArrayList;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.FileSystems;
import java.io.IOException;

public class Directory {

    String name;
    
    private static final String filePath = "src/main/resources/";

    //TODO: Possibly use a HashMap?
    ArrayList<File> files = new ArrayList<File>();

    //TODO: Possibly use a HashMap?
    ArrayList<Directory> directories = new ArrayList<Directory>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<File> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<File> files) {
        this.files = files;
    }

    public ArrayList<Directory> getDirectories() {
        return directories;
    }

    public void setDirectories(ArrayList<Directory> directories) {
        this.directories = directories;
    }

    public File searchFile(String filename) {
        for (File f : files) {
            if (f.getName().toLowerCase().equals(filename.toLowerCase())) {
                return f;
            }
        }
        return null;
    }

    public void deleteFile(File file) {
        //TODO: Delete file
    	Path path = FileSystems.getDefault().getPath(filePath, file.getName());
    	File myFile = path.toFile();
    	if(myFile.delete())
    	{
    		System.out.println(file.getName() + " was deleted.");
    		files.remove(myFile);
    	}
    	else
    	{
    		System.out.println(file.getName() + " does not exist.");
    	}
    }
    
    public void addFile(File file){
        //TODO: Add file
    	try {
    			Path path = FileSystems.getDefault().getPath(filePath, file.getName());
            	File myFile = path.toFile();
            	if (myFile.createNewFile()) {
            		System.out.println("New file: " + file.getName() + " created.");
	            }
	            else
	            {
	//            	myFile.createNewFile();
	            	System.out.println("File " + file.getName() + " already exists, new file not created.");
	            }
	            
	            files.add(myFile);
        }
        catch(IOException ex) 
    	{
            System.err.println(ex.getMessage());
        }

    }
    
    public void deleteDirectory(Directory directory) {
        //TODO: Delete directory
    }
    public void addDirectory(Directory directory) {
        //TODO: Add directory
    }



}
