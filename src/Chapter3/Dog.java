package Chapter3;

public class Dog {
	
	String name;
	
	public static void main (String[] args){
		
		Dog dog1 = new Dog(); //make new dog object and access it
		dog1.bark(); //has value of null when not referencing an object
		dog1.name = "Bart"; //gave it name
		
		Dog[] myDogs = new Dog[3]; //make dog array
									//now add dogs
		myDogs[0] = new Dog();
		myDogs[1] = new Dog();
		myDogs[2] = dog1;
		
									//now access dogs using the array
									//references
		myDogs[0].name = "Fred";
		myDogs[1].name = "Pablo";
		
									//myDogs[2] name?
		
		System.out.print("the last dog's name is ");
		System.out.println(myDogs[2].name);
		
									//now loop through array and tell all dogs to bark
		int x = 0;
		while(x < myDogs.length){
			myDogs[x].bark();
			x = x + 1;
		}
	}
	
	public void bark(){
		System.out.println(name + " says Ruff!");
	}
	
	public void eat(){}
	public void chaseCat(){}

}
