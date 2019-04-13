package P5;
import java.util.Scanner; 
public class Terminal {
	static Scanner in = new Scanner(System.in);
	public static int input(){ // Reads user input of an integer value and returns it.
		System.out.println("?");
		int value = in.nextInt();
		return value;
	}
	public static void output(String msg){ //Displays the output of a string parameter.
		System.out.println("OUTPUT: " + msg);
	}
	
	public static void output(int msg){ //Displays output of an integer.
		System.out.println("OUTPUT: " + msg);
	}
}
