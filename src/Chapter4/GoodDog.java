package Chapter4;

public class GoodDog {
	
	private int size; //making instance variable private
	
	public int getSize(){
		return size;
	}
	
	public void setSize(int s){
		size = s;
	}

	void bark(){
		
		if (size > 60){
			System.out.println("Woof woof!");
		}
		else if (size > 14){
			System.out.println("Ruff! Ruff!");
		} else {
			System.out.println("Yip yip!");
		}
	}
}
