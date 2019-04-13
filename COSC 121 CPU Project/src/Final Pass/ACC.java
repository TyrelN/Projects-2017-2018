package P5;

public class ACC extends Register {

	
	public ACC(int value) {//constructor
		super(value);
		
	}
	
	
	public void add(int value2) { //adds to stored object value
		setValue(getValue()+ value2);
	}

	public void subtract(int value2) { //subtracts
		setValue(getValue() - value2);

	}

	public void multiply(int value2) { //multiplies
		setValue(getValue() * value2);
	}

	public void divide(int value2) { //divides
		setValue(getValue()/ value2);
	}

}
