package P5;

public class PC extends Register{
	
	public PC(int value){
		super(value);
	}
	public void incrementValue(){ // increments the value by 1
		setValue(getValue()+ 1);
	}
	public void decrementValue(){ // decrements the value by 1
		setValue(getValue()- 1);
	}
}
