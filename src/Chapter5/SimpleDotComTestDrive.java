package Chapter5;

public class SimpleDotComTestDrive {
	
	public static void main (String[] args){
		
		SimpleDotCom dot = new SimpleDotCom(); //instantiate a SimpleDotCom object
		
		int[] locations = {2, 3, 4}; //make an int array for the location of the dot com (3 consecutive ints/7)
		
		dot.setLocationCells(locations); //invoke the setter method on the dot com
		
		String userGuess = "2"; //make a fake user guess
		
		String result = dot.checkYourself(userGuess); //invoke method on the dot com object and pass it as a fake guess
		
		String testResult = "failed";
		
		if (result.equals("hit")){
			
			testResult = "passed"; //if fake guess gives back a hit, its working
		}
		
		System.out.println(testResult);
	}

}
