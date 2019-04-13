package P5;

public class Test {

	public static void main(String[] args) {//To test the SymbolList list
		SymbolList list = new SymbolList();
		list.add(new Symbol("ELSE", 8, null));
		list.add(new Symbol("FINISH", 9, null));
		list.add(new Symbol("FIRST", 99, null));
		list.add(new Symbol("FIRST", 98, 0));
		
		
	System.out.println(list.getLocation("FIRST"));
		
	}

}
