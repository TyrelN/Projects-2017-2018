package P5;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Memory { // This class was changed to accommodate the new
						// loadMemory that functions as an overloaded helper
						// method.
	private int[] contents;
	private int size;

	public Memory(int size) { // Constructor
		this.size = size;
		contents = new int[size];
	}

	public int getValue(int address) { // Reads value at given address.
		if (address >= 0 | address <= size - 1)
			return contents[address];
		else
			return -1;
	}

	public void setValue(int address, int value) { // Sets the value for the
													// given address.
		if (address >= 0 && address <= size - 1)
			contents[address] = value;
		else
			System.out.println("Invalid address. Please try again.");

	}

	public void showContents() { // Prints out the contents of the memory unit.
		for (int c1 : contents) {
			System.out.println(c1);
		}

	}

	public void LoadMemory() { // Assigns default values to the array.
		contents[0] = 799;
		contents[1] = 789;
		contents[2] = 198;
		contents[3] = 499;
		contents[4] = 1008;
		contents[5] = 1108;
		contents[6] = 899;
		contents[7] = 909;
		contents[8] = 898;
		contents[9] = 0000;

	}

	public void LoadMemory(String file) throws NumberFormatException, IOException, EOFException {// assigns
																									// values
																									// to
																									// the
																									// contents
																									// array
																									// indexes
																									// until
																									// the
																									// value
																									// of
																									// zero
																									// is
																									// reached
																									// in
																									// the
																									// file
																									// contents.

		boolean end = false;
		int index = 0; // starts at address 0.
		try {
			DataInputStream newFile = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
			while (!end)
				try {
					contents[index++] = newFile.readInt();
				} catch (EOFException e) {
					end = true;
				} catch (IOException e) {
					System.out.println("IO Error");
				}
			newFile.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		} catch (SecurityException e) {
			System.out.println("Security Error");
		} catch (IOException e) {
			System.out.println("IO Error");
		}

	}
}
