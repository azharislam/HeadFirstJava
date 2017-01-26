package Chapter4;

public class PoorDog {

	private int size;		//no value assigned to instance variables
	private String name;	//difference between instance variables and local variables is that
							//local variables are declared within the method and need to be assigned a value
	public int getSize(){	//instance variables are declared inside a class and has a default value assigned
		return size; //return zero
	}
	
	public String getName(){
		return name; //return null
	}
}
//this is because they have default values if no value is assigned 