package P5;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestComputer {//This class was simply changed to test the new overloaded methods that read files.

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Computer comp = new Computer();
		comp.compile("src\\p5\\program.asm");
		comp.run("src\\P5\\program.ex");
		
		
	}

}
