//FILE: ProgramTwo.java
//AUTHOR: Mohamed Leevan Ahmed
//USERNAME: Leevan
//UNIT: COMP1007
//PURPOSE: Perform calculations on teams' data and siplay to user accordingly as requested 
//
//REFERENCE: none
//COMMENTS:
//
//REQUIRES: CSV file created in ProgramOne 
//
//LAST MOD: 15/10/2023

import java.io.*;
import java.util.*;

public class ProgramTwo {
    private static Team[] teams; // Array to store team objects

    //NAME: getData
	//PURPOSE: Open file and obtai  data 
	//IMPORTS: fileStream (String)
	//EXPORTS: 
	//ASSERTIONS:
		//PRE: 
		//POST: data is retrieved
	//REMARKS: Handles a IOException with a messasge    
    public static void getData(String fileStream) {
        try {
            BufferedReader read = new BufferedReader(new FileReader(fileStream + ".csv"));
            String line;
            int lines = 0;

            // Count the number of lines in the file to initialize the array
            while (read.readLine() != null) {
                lines++;
            }
            read.close();
            read = new BufferedReader(new FileReader(fileStream + ".csv"));

            teams = new Team[lines - 1]; // Subtract 1 for the header

            read.readLine(); // Skip the header

            int i = 0;

            while ((line = read.readLine()) != null) {
                String[] data = line.split(",");
                String teamName = data[0];
                String teamCode = data[1];
                int goalsScored = Integer.parseInt(data[2]);
                int goalsConceded = Integer.parseInt(data[3]);
                char group = data[4].charAt(0);

                teams[i] = new Team(teamName, teamCode, goalsScored, goalsConceded, group);
                i++;
            }
            read.close();
            System.out.println("Data retrieved successfully.");
        } catch (IOException errorDetails) {
            System.out.println("Error while retrieving data from the file.");
            errorDetails.printStackTrace();
        }
    }

    //NAME: dataAnalysis
	//PURPOSE: Asks user to choose type of analysis or to exit program 
	//IMPORTS: 
	//EXPORTS: message displaying options 
	//ASSERTIONS:
		//PRE: 
		//POST: 
	//REMARKS:    
    public static void dataAnalysis() {
        if (teams == null) {
            System.out.println("No data to analyze. Please retrieve data first.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        int option;

        while (true) {
            System.out.println("Choose one of the options below:");
            System.out.println("1. Overall Analysis");
            System.out.println("2. Group Analysis");
            System.out.println("3. Exit");

            if (sc.hasNextInt()) {
                option = sc.nextInt();
                sc.nextLine(); // Consume the newline character 
            } else {
                System.out.println("Invalid option. Please select an integer value for option from 1 to 3.");
                sc.nextLine(); // Consume the invalid input
                continue;
            }

            switch (option) {
                case 1:
                    AllTeamsAnalysis();
                    break;
                case 2:
                    GroupAnalysis(sc);
                    break;
                case 3:
                    System.out.println("Thank you and have a great day!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option. Please select an option from 1 to 3.");
            }
        }
    }

    //NAME: AllTeamsAnalysis
	//PURPOSE: Calculates goal difference and dsiaplays all team in descending order of each 
	//IMPORTS: 
	//EXPORTS: Teams in descending order
	//ASSERTIONS:
		//PRE: 
		//POST: All teams sorted in descending order 
	//REMARKS:     
    public static void AllTeamsAnalysis() {
        Arrays.sort(teams, (a, b) -> b.goalsScored - a.goalsScored); // Sort by goals scored (descending)
        System.out.println("Teams sorted by descending order of total goals scored :");
        for (Team team : teams) {
            System.out.println(team.teamName + ": " + team.goalsScored + " goals");
        }

        Arrays.sort(teams, (a, b) -> b.goalsConceded - a.goalsConceded); // Sort by goals conceded (descending)
        System.out.println("Teams sorted by descending order of total goals conceded :");
        for (Team team : teams) {
            System.out.println(team.teamName + ": " + team.goalsConceded + " goals conceded");
        }

        Arrays.sort(teams, (a, b) -> (b.goalsScored - b.goalsConceded) - (a.goalsScored - a.goalsConceded)); // Sort by goal difference
        System.out.println("Teams sorted by descending order of goal difference:");
        for (Team team : teams) {
            System.out.println(team.teamName + ": " + (team.goalsScored - team.goalsConceded) + " goal difference");
        }

        System.out.println("The best performing team is " + teams[0].teamName);
    }

    //NAME: GroupAnalysis
	//PURPOSE: Sorts teams based of a specific group in descending order of goals scored, conceded and goal difference
	//IMPORTS: sc (Scanner)
	//EXPORTS:  
	//ASSERTIONS:
		//PRE: 
		//POST: Teams in group sorted in descending order 
	//REMARKS:
    public static void GroupAnalysis(Scanner sc) {
        String validGroups = "A/B/C/D";
        char selectedGroup = getValidGroup(sc, validGroups);

        // Check if the selected group is valid
        if (selectedGroup != '0') {
            Team[] groupTeams = filterTeamsByGroup(selectedGroup);

            // Check if there are teams in the selected group
            if (groupTeams.length == 0) {
                System.out.println("No data available for group " + selectedGroup + ". Please select a different group.");
                return; // Return to the main menu
            }

            // Perform analysis and display results
            analyzeAndDisplayResults(groupTeams, selectedGroup);
        }
    }

    //NAME: getValidGroup
	//PURPOSE: Check to see if entered group is valid
	//IMPORTS: Scanner sc, String validGroups
	//EXPORTS:  
	//ASSERTIONS:
		//PRE: 
		//POST: Group letter is valid
	//REMARKS: 
    private static char getValidGroup(Scanner sc, String validGroups) {
        while (true) {
            System.out.print("Enter the group from A, B, C, or D for group analysis: ");
            String input = sc.next();

            if (input.length() == 1) {
                char selectedGroup = input.toUpperCase().charAt(0);

                if (validGroups.indexOf(selectedGroup) != -1) {
                    return selectedGroup; // Valid group selected
                }
            }

            System.out.println("Invalid group. Please select a group from A, B, C, or D.");
            sc.nextLine(); // Consume the invalid input
        }
    }

    //NAME: filterTeamsByGroup
	//PURPOSE: Separate the teams for a specific group
	//IMPORTS: char selectedGroup
	//EXPORTS:  
	//ASSERTIONS:
		//PRE: 
		//POST: Temas are filtered by group
	//REMARKS: 
    private static Team[] filterTeamsByGroup(char selectedGroup) {
        return Arrays.stream(teams)
                .filter(team -> team.group == selectedGroup)
                .toArray(Team[]::new);
    }

    //NAME: analyzeAndDisplayResults
	//PURPOSE: Find out which team is the best performing in a specific group
	//IMPORTS: Team[] groupTeams, char selectedGroup
	//EXPORTS:  
	//ASSERTIONS:
		//PRE: 
		//POST: Details of chosen group's teams' and the best team is displayed
	//REMARKS: 
    private static void analyzeAndDisplayResults(Team[] groupTeams, char selectedGroup) {
        Arrays.sort(groupTeams, (a, b) -> b.goalsScored - a.goalsScored);
        System.out.println("Teams in group " + selectedGroup + " sorted by descending order of total goals scored:");
        for (Team team : groupTeams) {
            System.out.println(team.teamName + ": " + team.goalsScored + " goals");
        }

        Arrays.sort(groupTeams, (a, b) -> b.goalsConceded - a.goalsConceded);
        System.out.println("Teams in group " + selectedGroup + " sorted by descending order of total goals conceded:");
        for (Team team : groupTeams) {
            System.out.println(team.teamName + ": " + team.goalsConceded + " goals conceded");
        }

        Arrays.sort(groupTeams, (a, b) -> (b.goalsScored - b.goalsConceded) - (a.goalsScored - a.goalsConceded));
        System.out.println("Teams in group " + selectedGroup + " sorted by descending order of goal difference:");
        for (Team team : groupTeams) {
            System.out.println(team.teamName + ": " + (team.goalsScored - team.goalsConceded) + " goal difference");
        }

        System.out.println("The best performing team in group " + selectedGroup + " is " + groupTeams[0].teamName);
    }

}



