package Chapter5;

public class SimpleDotComGame {
	
	public static void main (String[] args){
		
		int numOfGuesses = 0; 								//make variable to hold user guesses
		
		GameHelper helper = new GameHelper();
		
		SimpleDotCom theDotCom = new SimpleDotCom();		//new object of a dot com
		
		int randomNum = (int) (Math.random() * 5);			//make random number for first cell, integer
		
		int[] locations = {randomNum, randomNum+1, randomNum+2}; //location for the 3 cell dotcom
		
		theDotCom.setLocationCells(locations); 					//set location cells
		
		boolean isAlive = true; 								//while game is still alive
		
		while(isAlive == true){
			
			String guess = helper.getUserInput("enter a number");		//get user input string
			String result = theDotCom.checkYourself(guess);				//ask dotcom to check guess, save returned result as string
			
			numOfGuesses++; 											//increment guess count
			
			if(result.equals("kill")){									//if result is kill
				
				isAlive = false;										//result is false, game is not alive
				
				System.out.println("You took " + numOfGuesses + " guesses");		//print user guesses2
			}
		}
	}

}
