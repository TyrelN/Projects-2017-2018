package P5;

import java.util.Iterator;

public class SymbolList {// Acting as both the linked list and the symbol
							// table
	private int size = 0;
	private SymbolNode<Symbol> head = null, tail = null;

	public String toString() {
		StringBuilder viewList = new StringBuilder();
		SymbolNode<Symbol> current = head;
		for (int i = 0; i < size; i++) {
			viewList.append(current.symbol + "\n");
			current = current.next;

		}
		return viewList.toString();
	}

	public void addLast(Symbol e) {
		SymbolNode<Symbol> node = new SymbolNode<>(e);
		if (size == 0) {
			head = tail = node;
		} else {
			tail.next = node;
			tail = node;
		}
		size++;
	}

	public void add(Symbol e) {
		addLast(e);

	}


	public Integer getValue(int location) {
		if (location < 0 || location >= size) {
			return null;
		} else {
			SymbolNode<Symbol> current = head;
			for (int i = 0; i < location; i++) {
				current = current.next;
			}
			return (current!=null)?current.symbol.getValue():null;
		}
	}
	public int getLocation(String name) {
		
			SymbolNode<Symbol> current = head;
			while(current.symbol.getName()!= name) {
				current = current.next;
			}
			return (current!=null)?current.symbol.getLocation():null;
		}
	
	public int getValue(String name) {
		
		SymbolNode<Symbol> current = head;
		while(current.symbol.getName()!= name) {
			current = current.next;
		}
		return (current!=null)?current.symbol.getValue():null;
	}

	

	public SymbolIterator iterator() {
		return new SymbolIterator();
	}

	class SymbolIterator implements Iterator<Symbol> {
		SymbolNode<Symbol> current = head;

		public Symbol next() {
			Symbol e = current.symbol;
			current = current.next;
			return e;
		}

		public boolean hasNext() {

			return (current != null);
		}
	}
	private class SymbolNode<Symbol> {//Note this hiding parameter type may imply that the type casting was unnecessary
		
		Symbol symbol;
		SymbolNode<Symbol> next;

		private SymbolNode(Symbol e) {
			symbol = e;
		}
	}
	
}
