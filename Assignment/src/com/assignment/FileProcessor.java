package com.assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileProcessor {
	String fileName;
	File fileExample;
	Scanner myScanner;
    PrintWriter pwInput;
    FileWriter fwInput;
	
	// Constructor
	FileProcessor (String fileName) {
		
		this.fileName = fileName;
		
	}
	
	// get a connection to the file
	void connectToFile() {
		fileExample = new File(fileName);
	}

	// Read the file, returning a string of lines.
	ArrayList<String> readFile() {
    
		ArrayList<String> values = new ArrayList<String>();
    	
	    try {
	    	int i = 0;
	    	myScanner = new Scanner(fileExample); 
			while (myScanner.hasNextLine()) {
			      
				 values.add(i, myScanner.nextLine());
			      i++;
			}
			myScanner.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("run time error " + e.getMessage());
		}
	    finally {
	        return values;
	    }
    }
    
    
	// get hold of a Print writer object
    void getFileWriter() {
    	try {
    		fwInput = new FileWriter(fileExample, true);
    		pwInput = new PrintWriter(fwInput);
    	}
  		catch (FileNotFoundException e) {
  			System.out.println("run time error " + e.getMessage());
  		} 
    	catch (IOException e) {
			// TODO Auto-generated catch block
  			System.out.println("run time error " + e.getMessage());
		}
    	
    }	

	// write a string to the file
    void writeLineToFile(String line) {
       System.out.println("Added " + line);
       pwInput.append(line);
    }	

    // close the scanner. Good to have this as a separate method as "closing" is different to readin
    void closeReadFile() {
		 myScanner.close();
    }

    // close the PrintWriter. Good to have this as a separate method as "closing" is different to writing. 
    void closeWriteFile() {
		 pwInput.close();
    }


    
    
 

}
