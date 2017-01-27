package Chapter5;
//user starts game
//game creates three dotcoms
//game places the three dot coms on a virtual grid
//game play begins
//prompt user for a guess
//check the user guess against all dot coms to look for a hit, miss or kill
//take appropriate action: if a hit, delete cell, if a kill delete dot com
//repeat last three prompts until there are no more dotcoms

public class SimpleDotCom {
	
	int[] locationCells;
	int numOfHits = 0;
	
	public void setLocationCells(int[] locs){
		locationCells = locs;
	}
	
	public String checkYourself(String stringGuess){
		
		int guess = Integer.parseInt(stringGuess); //convert int to string
		String result = "miss"; //variable to hold result, miss is default
		
		for (int cell : locationCells){ //repeat with each cell in the locationCells array
			 
			if (guess == cell){ 		//compare user guess to cell in the array
				
				result = "hit"; //user got a hit
				numOfHits++; 	//increment number of hits
				
				break;		//get out of loop, no need to test other cells
			}
		}
		
		if(numOfHits == locationCells.length){ //if number of hits is equal to the location of the dot com (3)
			
			result = "kill"; 					//kill
		}
		
		System.out.println(result);				//display result for user
		
		return result; //return result back to calling method
	}

}
