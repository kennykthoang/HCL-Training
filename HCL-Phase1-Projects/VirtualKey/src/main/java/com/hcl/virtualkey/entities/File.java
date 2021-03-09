package com.hcl.virtualkey.entities;

public class File implements java.lang.Comparable<File>{

    String name;

    String path;

    public File(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    // Override Comparable method so that we can use Collections.sort method to sort the files
	@Override
	public int compareTo(File otherFile) {
		// TODO Auto-generated method stub
		return name.compareTo(otherFile.getName());
	}


}
