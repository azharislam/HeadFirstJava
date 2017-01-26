package Chapter3;

public class Hobbits {
	
	String name;
	
	public static void main (String[] args){
		
		Hobbits [] h = new Hobbits[3];
		//int z = 0;
		int z = -1;       //arrays start from 0, there are 3 objects in the array, so it should start at -1
		//while(z < 4){
		while(z < 2){	//goes from -1 to 2
			z = z + 1;
			
			h[z] = new Hobbits();
			h[z].name = "Bilbo";
			if (z == 1){
				
				h[z].name = "Frodo";
			}
			
			if (z == 2){
				h[z].name = "Sam";
			}
			
			System.out.print(h[z].name + " is a ");
			System.out.println("good Hobbit name");
		}
	}

}
