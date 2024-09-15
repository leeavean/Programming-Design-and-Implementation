//FILE: MainProgram.java
//AUTHOR: Mohamed Leevan Ahmed
//USERNAME: Leevan
//UNIT: COMP1007
//PURPOSE: Serve as the entry point of the program coordinating all the functions by  
//calling all the methods in order
//
//REFERENCE: none
//COMMENTS:
//
//REQUIRES: ProgramOne.java ProgramTwo.java and Team.java
//
//LAST MOD: 15/10/2023

import java.io.*;
import java.util.*;

public class MainProgram {
    public static void main(String[] args) {
        ProgramOne.dataInput();
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the FIFA WWC Data Analysis Program");
        String fileStream = "N/A";
        boolean fileExists = false;

        // Ask for valid file name  
        while (!fileExists) {
            System.out.print("Enter the file name containing the data: ");
            fileStream = sc.nextLine();

            // Check if the file name is not empty
            if (fileStream.isEmpty()) {
                System.out.println("File name cannot be empty. Please enter a valid file name.");
            }
            
            // Check if the file exists
            if (!fileExists(fileStream + ".csv")) {
                System.out.println("File does not exist. Please enter a valid file name.");
            } else {
                fileExists = true;
            }
        }

        ProgramTwo.getData(fileStream);
        ProgramTwo.dataAnalysis();

        sc.close();
    }

    //NAME: fileExists
	//PURPOSE: Checks if file entered exists or if a file name is entered  
	//IMPORTS: fileStream
	//EXPORTS:  
	//ASSERTIONS:
		//PRE: file name is given 
		//POST: the file name passed through will be exist
	//REMARKS: 
    public static boolean fileExists(String fileStream) {
        File file = new File(fileStream);
        return file.exists() && !file.isDirectory();
    }
}
