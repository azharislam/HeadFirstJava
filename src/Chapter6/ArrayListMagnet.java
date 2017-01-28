package Chapter6;

import java.util.*;

public class ArrayListMagnet {
	
	public static void main (String[] args){
				
		ArrayList<String> a = new ArrayList<String>();
		
		a.add(0, "zero");
		a.add(1, "one");
		a.add(2, "two");
		a.add(3, "three");
		printAL(a);              //prints zero one two three
		
	
		if (a.contains("three")){
			a.add("four");
		}
		
		a.remove(2);
		printAL(a);				//prints zero one three four
		
		if(a.indexOf("four") != 4){
			a.add(4, "4.2");
		}
		
		printAL(a);				//prints zero one three four 4.2
		
		if(a.contains("two")){
			a.add("2.2");
		}

		printAL(a);				//still prints zero one three four 4.2
}
	
	public static void printAL(ArrayList<String> al){
		
		for(String element : al){
			
			
			System.out.print(element + " ");
		}
		
		System.out.println(" ");
	}

}
