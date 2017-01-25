package Chapter2;

public class GuessGame {
		
		Player p1; //3 instance variables for the 3 player objects
		Player p2;
		Player p3;
	
		public void startGame(){ 
			
			p1 = new Player(); //create 3 player objects
			p2 = new Player();
			p3 = new Player();
			
			int guessp1 = 0; //declare 3 variables to hold 3 guesses the players make
			int guessp2 = 0;
			int guessp3 = 0;
			
			boolean p1isRight = false; //3 variables to hold true or false based on players answer
			boolean p2isRight = false;
			boolean p3isRight = false;
			
			int targetNumber = (int) (Math.random() * 10); //wont ever be more than 9 (math.random generates random number between 0 and 1)
			System.out.println("I am thinking of a number between 0 and 9...");
			
			while(true){
				System.out.println("Number to guess is " + targetNumber);
				
				p1.guess();
				p2.guess();
				p3.guess();
				
				guessp1 = p1.number; //the number that this guy guessed
				System.out.println("Player one guessed " + guessp1);
				
				guessp2 = p2.number;
				System.out.println("Player two guessed " + guessp2);
				
				guessp3 = p3.number;
				System.out.println("Player three guessed " + guessp3);
				
				if (guessp1 == targetNumber){
					p1isRight = true;
				}
				if (guessp2 == targetNumber){ //check each players guess to see if it matches target number
					p2isRight = true;
				}
				if (guessp3 == targetNumber){
					p3isRight = true;
				}
				
				if(p1isRight || p2isRight || p3isRight){ //if all three of them are correct
					
					System.out.println("We have a winner!");
					System.out.println("Player one got it right? " + p1isRight);
					System.out.println("Player two got it right?" + p2isRight );
					System.out.println("Player three got it right?" + p3isRight );
					System.out.println("Game is over.");
					break; //game is over so break out of loop
					
				} else {
					
					System.out.println("Players will have to try again, soz guys hehe");
				}
			}
	}
}
