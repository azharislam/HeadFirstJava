package Chapter10;

public class StaticSuper {
	
	static {
		
		System.out.println("Super static block");
	}
	
	//StaticSuper{		wont compile
	
	StaticSuper(){
		
		System.out.println(
				"Super constructor");
	}

}
