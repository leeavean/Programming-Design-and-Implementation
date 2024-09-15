//FILE: ProgramOne.java
//AUTHOR: Mohamed Leevan Ahmed
//USERNAME: Leevan
//UNIT: COMP1007
//PURPOSE: Asks user to specify number of teams for data to be entered and then saves data by creeating a csv file
//
//REFERENCE: none
//COMMENTS:
//
//REQUIRES: 
//
//LAST MOD: 15/10/2023

import java.io.*;
import java.util.*;

public class ProgramOne {

    //NAME: dataInput
	//PURPOSE: To ask for number of teams and call all methods in order
	//IMPORTS: 
	//EXPORTS: teamName, teamCode, goalsScored, goalsConceded, group and file name 
	//ASSERTIONS:
		//PRE: 
		//POST: Data is validated and then stored in a csv file
	//REMARKS: Handles all methods in ProgramOne
    public static void dataInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the FIFA WWC Data Entry Program");
        int numTeams = 0;
        do {
            System.out.print("How many teams' data are you planning to enter? ");
            while (!sc.hasNextInt()) {
                System.out.println("Please enter a positive integer.");
                System.out.print("How many teams' data are you planning to enter? ");
                sc.next(); // Clear the invalid input
            }
            numTeams = sc.nextInt();
        } while (numTeams <= 0);

        // Create an array to store team objects
        Team[] teams = new Team[numTeams];

        for (int i = 0; i < numTeams; i++) {
            System.out.println("Enter data for team " + (i + 1) + ":");

            String teamName = AskTeamName(sc);
            String teamCode = AskTeamCode(sc);
            int goalsScored = AskGoalsScored(sc);
            int goalsConceded = AskGoalsConceded(sc);
            char group = AskGroup(sc);

            // Create a new Team object and store it in the array
            teams[i] = new Team(teamName, teamCode, goalsScored, goalsConceded, group);

            // Display the entered data
            System.out.println("The current data looks like this:");
            System.out.println("Team Name,Team Code,Goals For,Goals Against,Group");
            System.out.println(teams[i].teamName + "," + teams[i].teamCode + "," + teams[i].goalsScored + ","
                    + teams[i].goalsConceded + "," + teams[i].group);
        }

        String fileStream = AskFileName(sc);

        if (saveToFile(teams, fileStream)) {
            System.out.println("Data saved to " + fileStream + ".csv");
        } else {
            System.out.println("Error while saving data to the file.");
        } // Data saved to file 

        System.out.println("Thank you and have a great day!");
    }

    //NAME: saveToFile
	//PURPOSE: Creates file to save data
	//IMPORTS: teams(Team), fileStream(String)
	//EXPORTS: 
	//ASSERTIONS:
		//PRE: Collects all data
		//POST: File is created and data stored 
	//REMARKS: Handles a IOException with a message     
    public static boolean saveToFile(Team[] teams, String fileStream) {
        try {
            PrintWriter pw = new PrintWriter(new File(fileStream + ".csv"));

            // Write file header
            pw.println("Team Name,Team Code,Goals For,Goals Against,Group");

            // Write each team's data
            for (Team team : teams) {
                if (team != null) {
                    pw.println(team.teamName + "," + team.teamCode + "," + team.goalsScored + "," + team.goalsConceded + ","
                            + team.group);
                }
            }

            pw.close();
            return true;
        } catch (IOException errorDetails) {
            errorDetails.printStackTrace();
            return false;
        }
    }

    //NAME: AskTeamName
	//PURPOSE: Asks user to enter valid team name 
	//IMPORTS: sc (Scanner)
	//EXPORTS: error message if teamName is incorrect 
	//ASSERTIONS:
		//PRE: 
		//POST: teamName is entered
	//REMARKS:    
    public static String AskTeamName(Scanner sc) {
        String teamName = "N/A";
    
        do {
            System.out.print("Team name: ");
            teamName = sc.nextLine().trim();
    
            if (teamName.isEmpty()) {
                System.out.println("Team name cannot be empty. Please re-enter.");
            } else if (!ValidTeamName(teamName)) {
                System.out.println("Team name should be in alphabetical letters only. Please re-enter.");
            }
        } while (teamName.isEmpty() || !ValidTeamName(teamName)); // Team name is validated
    
        return teamName;
    }    

    //NAME: ValidTeamName
	//PURPOSE: Checks if teamName is a String
	//IMPORTS: input(String)
	//EXPORTS:  
	//ASSERTIONS:
		//PRE: 
		//POST: teamName is valid
	//REMARKS:    
    public static boolean ValidTeamName(String input) {
        return input.matches("^[a-zA-Z]+$");
    }

    //NAME: AskTeamCode
	//PURPOSE: Asks user to enter teamCode 
	//IMPORTS: sc (Scanner)
	//EXPORTS: error message if teamCode is invalid 
	//ASSERTIONS:
		//PRE: 
		//POST: teamCode is entered correectly
	//REMARKS:    
    public static String AskTeamCode(Scanner sc) {
        String teamCode = "N/A";
        do {
            System.out.print("Team code: ");
            teamCode = sc.nextLine();
            
            if (!ValidTeamCode(teamCode)) {
                System.out.println("Team code should be in alphabetical letters. Please re-enter.");
            }
            else if (teamCode.trim().isEmpty()) {
                System.out.println("Team code cannot be empty. Please re-enter.");
            }

        } while (teamCode.trim().isEmpty() || !ValidTeamCode(teamCode));
        return teamCode;
    }

    //NAME: ValidTeamCode
	//PURPOSE: Checks if team code entered is an uppercase String 
	//IMPORTS: input (String)
	//EXPORTS:  
	//ASSERTIONS:
		//PRE: file name is given in string form
		//POST: teamCode will be valid
	//REMARKS:     
    public static boolean ValidTeamCode(String input) {
        return input.matches("^[A-Z]+$");
    }

    //NAME: AskGoalsScored
	//PURPOSE: Asks user to enter goals scored by team  
	//IMPORTS: sc (Scanner)
	//EXPORTS: error message if goals scored is an incorrect input 
	//ASSERTIONS:
		//PRE: 
		//POST: goalsScored is valid
	//REMARKS:     
    public static int AskGoalsScored(Scanner sc) {
        int goalsScored = 0;
        do {
            System.out.print("Goals scored: ");
            while (!sc.hasNextInt()) {
                System.out.println("Please enter a positive integer for goals scored.");
                System.out.print("Goals scored: ");
                sc.nextLine(); // Clear the invalid input
            }
            goalsScored = sc.nextInt();
            if (goalsScored < 0) {
                System.out.println("Goals scored cannot be negative. Please re-enter.");
            }  
        } while (goalsScored < 0); // goalsScored is validated
        return goalsScored;
    }

    //NAME: AskGoalsConceded
	//PURPOSE: Asks user to enter goals conceded by team 
	//IMPORTS: sc (Scanner)
	//EXPORTS: error message if goals conceded is an invalid input  
	//ASSERTIONS:
		//PRE: 
		//POST: Valid input for goalsConceded is accepted
	//REMARKS:     
    public static int AskGoalsConceded(Scanner sc) {
        int goalsConceded = 0;
        do {
            System.out.print("Goals Scored Against: ");
            while (!sc.hasNextInt()) {
                System.out.println("Please enter a positive integer for Goals Scored Against.");
                System.out.print("Goals Scored Against: ");
                sc.nextLine(); // Clear the invalid input
            }
            goalsConceded = sc.nextInt();
            if (goalsConceded < 0) {
                System.out.println("Goals Scored Against cannot be negative. Please re-enter.");
            }
        } while (goalsConceded < 0); // goalsConceded is validated 
        return goalsConceded;
    }

    //NAME: AskGroup
	//PURPOSE: Asks user to enter group letter of team 
	//IMPORTS: sc (Scanner)
	//EXPORTS: error message if group letter is invalid 
	//ASSERTIONS:
		//PRE: 
		//POST: group is valid and accepted
	//REMARKS:     
    public static char AskGroup(Scanner sc) {
        String validGroups = "ABCD";
        char group = 'A';
    
        while (true) {
            System.out.print("Enter group (A/B/C/D): ");
            String groupInput = sc.next();
    
            if (groupInput.length() == 1 && validGroups.contains(groupInput)) {
                group = groupInput.charAt(0);
                break; // Exit the loop if a valid group character is entered
            } else {
                System.out.println("Invalid group. Please select a group from A, B, C, or D.");
            }
        }
    
        return group;
    }

    //NAME: AskFileName
	//PURPOSE: Asks user to enter name for file 
	//IMPORTS: sc (Scanner)
	//EXPORTS: 
	//ASSERTIONS:
		//PRE: 
		//POST: valid file name is accepted
	//REMARKS:     
    public static String AskFileName(Scanner sc) {
        System.out.print("What would you like to name your csv file? ");
        return sc.next();
    }
}