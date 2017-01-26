package Chapter3;

public class BooksTestDrive {
	
	public static void main (String[] args){
		
		Books [] myBooks = new Books[3];
		
		myBooks[0] = new Books(); //before I didn't declare book objects, so I had a NullPointer exception
		myBooks[1] = new Books(); //after declaring the objects, program works
		myBooks[2] = new Books();
		
		myBooks[0].title = "The grapes of Java"; 
		myBooks[1].title = "The Java Gatsby";
		myBooks[2].title = "The Java cookbook";
		myBooks[0].author = "Bob";
		myBooks[1].author = "Sue";
		myBooks[2].author = "Ian";
		
		int x = 0;
		while (x < 3){
			
			System.out.print(myBooks[x].title);
			System.out.println(" by ");
			System.out.println(myBooks[x].author);
			
			x++;
		}
	}

}
