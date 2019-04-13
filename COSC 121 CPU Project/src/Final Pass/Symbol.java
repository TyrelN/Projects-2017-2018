package P5;

public class Symbol {
	private String name;
	private Integer value;
	private int location;

	public Symbol(String name, int location, Integer value) {
		this.name = name;
		this.value = value;
		this.location = location;
	}

	public String getName() {
		return this.name;
	}

	public Integer getValue() {
		return this.value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public int getLocation() {
		return this.location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "(" + getName() + ", " + getLocation() + ", " + getValue() + ")";
	}

}
