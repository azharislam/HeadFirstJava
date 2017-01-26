package Chapter4;

public class Clock {
	
	String time;
	
	void setTime(String t){ 
		time = t;
		
	}					//no return value at first, need to add a return value
	
	//void getTime(){ (same as above)
	String getTime(){
		return time; //won't compile because time wasn't declared within the variable
						//also no return value
	}
}
	//getter methods always have a return type by definition


