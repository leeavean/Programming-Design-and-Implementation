//FILE: Team.java
//AUTHOR: Mohamed Leevan Ahmed
//USERNAME: Leevan
//UNIT: COMP1007
//PURPOSE: Makes a Team object and manipulates it's information
//
//REFERENCE: none
//COMMENTS:none
//REQUIRES: none
//LAST MOD: 15/10/2023

public class Team {
    public String teamName;
    public String teamCode;
    public int goalsScored;
    public int goalsConceded;
    public char group;

	//NAME: Team
	//PURPOSE: A default constructor for the Team class sets variables to empty
	//IMPORTS: none
	//EXPORTS: none
	//ASSERTIONS:
		//PRE: none
		//POST: object is initialised
    public Team() {
        teamName = "";
        teamCode = "";
        goalsScored = 0;
        goalsConceded = 0;
        group = 'A'; 
    }
    
    //NAME: Team
	//PURPOSE: A constructor that initalises the class fields of the object with inputs passed in 
	//IMPORTS: aTeamName, aTeamCode team, aGoalsScored, aGoalsConceded, aGroup 
	//for each team
    //
	//EXPORTS: none
	//ASSERTIONS:
		//PRE:  All parameters represent valid inputs
		//POST: object is initialised 
    public Team(String aTeamName, String aTeamCode, int aGoalsScored, int aGoalsConceded, char aGroup) {
        teamName = aTeamName;
        teamCode = aTeamCode;
        goalsScored = aGoalsScored;
        goalsConceded = aGoalsConceded;
        group = aGroup;
    }
	
    //NAME: Team
	//PURPOSE: A copy constructor to copy the class fields of an object into the new object
	//IMPORTS: otherTeam, a object to copy to new object
	//EXPORTS: none
	//ASSERTIONS:
		//PRE: otherTeam has valid inputs
		//POST: object is initialised
    public Team(Team otherTeam) {
        teamName = otherTeam.teamName;
        teamCode = otherTeam.teamCode;
        goalsScored = otherTeam.goalsScored;
        goalsConceded = otherTeam.goalsConceded;
        group = otherTeam.group;
    }

    //NAME: getTeamName
	//PURPOSE: An accessor method to retrieve teamName
	//IMPORTS: none
	//EXPORTS: teamName (String)
	//ASSERTIONS:
		//PRE: 
		//POST: teamName is a valid string
    public String getTeamName() {
        return teamName;
    }

    //NAME: getTeamCode
	//PURPOSE: An accessor method to retrieve teamCode
	//IMPORTS: none
	//EXPORTS: teamCode (String)
	//ASSERTIONS:
		//PRE: 
		//POST: teamCode is a valid string   
    public String getTeamCode() {
        return teamCode;
    }

    //NAME: getGoalsScored
	//PURPOSE: An accessor method to retrieve goalsScored
	//IMPORTS: none
	//EXPORTS: goalsScored (int)
	//ASSERTIONS:
		//PRE: 
		//POST: goalsScored is a valid value 
    public int getGoalsScored() {
        return goalsScored;
    }

    //NAME: getGoalsConceded
	//PURPOSE: An accessor method to retrieve goalsConceded
	//IMPORTS: none
	//EXPORTS: goalsConceded (int)
	//ASSERTIONS:
		//PRE: 
		//POST: goalsConceded is a valid value   
    public int getGoalsConceded() {
        return goalsConceded;
    }

    //NAME: getGroup
	//PURPOSE: An accessor method to retrieve group
	//IMPORTS: none
	//EXPORTS: group (char)
	//ASSERTIONS:
		//PRE: 
		//POST: group is a valid value
    public char getGroup() {
        return group;
    }

    //NAME: setTeamName
	//PURPOSE: A mutator method to modify teamName
	//IMPORTS: aTeamName (String)
	//EXPORTS:  none
	//ASSERTIONS:
		//PRE: aTeamName is a valid value
		//POST: 
    public void setTeamName(String aTeamName) {
        teamName = aTeamName;
    }

    //NAME: setTeamCode
	//PURPOSE: A mutator method to modify teamCode
	//IMPORTS: aTeamCode (String)
	//EXPORTS:  none
	//ASSERTIONS:
		//PRE: aTeamCode is a valid value
		//POST: 
    public void setTeamCode(String aTeamCode) {
        teamCode = aTeamCode;
    }

    //NAME: setGoalScored
	//PURPOSE: A mutator method to modify goalScored
	//IMPORTS: aGoalScored (int)
	//EXPORTS:  none
	//ASSERTIONS:
		//PRE: aGoalScored is a valid value
		//POST: 
    public void setGoalsScored(int aGoalsScored) {
        goalsScored = aGoalsScored;
    }

    //NAME: setGoalsConceded
	//PURPOSE: A mutator method to modify goalsConceded
	//IMPORTS: aGoalsConceded (int)
	//EXPORTS:  none
	//ASSERTIONS:
		//PRE: aGoalsConceded is a valid value
		//POST: 
    public void setGoalsConceded(int aGoalsConceded) {
        goalsConceded = aGoalsConceded;
    }

    //NAME: setGroup
	//PURPOSE: A mutator method to modify group
	//IMPORTS: aGroup (char)
	//EXPORTS:  none
	//ASSERTIONS:
		//PRE: aGroup is a valid value
		//POST: 
    public void setGroup(char aGroup) {
        group = aGroup;
    }
}
