package com.hcl.virtualkey.services;

import java.util.Collections;

import com.hcl.virtualkey.entities.Directory;
// import com.hcl.virtualkey.entities.File;
import java.io.File;

public class DirectoryService {

    private static Directory fileDirectory = new Directory();


    public static void AddTestData(){
        fileDirectory.addFile(new File("test1.txt"));
        fileDirectory.addFile(new File("test2.txt"));
        fileDirectory.addFile(new File("test3.txt"));
        fileDirectory.addFile(new File("test4.txt"));

    }

    public static void PrintFiles() {
    	
    	Collections.sort(DirectoryService.getFileDirectory().getFiles());
    	
    	System.out.println("Current files: ");
        for (java.io.File file : DirectoryService.getFileDirectory().getFiles())
        {
            System.out.println(file.getName());
        }
        System.out.println();
    }
    public static Directory getFileDirectory() {
        return fileDirectory;
    }

    public static void setFileDirectory(Directory fileDirectory) {
        DirectoryService.fileDirectory = fileDirectory;
    }


}
