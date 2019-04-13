package P5;

public class IR extends Register {
	public IR(int value) {
		super(value);
		
	}

	public int getOpCode() { // returns first two digits of the instruction
		//I updated the code to account for the lack of 0 at the beginning of the instruction, so that it will always check the right values.
		return getValue() /100;

	}
	

	public int getOperand() { // returns the last two digits of the instruction
		return getValue() %100;
	}

}
