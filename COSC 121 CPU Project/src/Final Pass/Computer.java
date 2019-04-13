package P5;

import java.io.*;
import java.util.Scanner;

public class Computer { 
	

	private boolean STOP;
	private Memory ram;
	private Register ir;
	private Register pc;
	private Register acc;
	private Register dc;

	public Computer() {

		this.ram = new Memory(100);

		this.ir = new IR(0);
		this.pc = new PC(0);
		this.acc = new ACC(0);
		this.dc = new PC(99);
	}

	// Methods for part 5:
	public void compile(String filename) {
		SymbolList symbolTable;
		try {
			symbolTable = firstPass(filename);
			// System.out.println(symbolTable); - the test method.
			secondPass(symbolTable, filename);

		} catch (FileNotFoundException e) {
			System.out.println("No file found.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Output file error occured.");
			e.printStackTrace();
		}

	}

	public SymbolList firstPass(String filename) throws FileNotFoundException {
		pc.setValue(0);
		try (Scanner in = new Scanner(new File(filename));) {

			SymbolList list = new SymbolList();
			while (in.hasNextLine()) {
				String line = in.nextLine();

				String first = readFirstWord(line);

				if (isInstruction(first)) {
					incrementPC();

				} else if (first.equals("REM")) {

				}

				else if (isLabel(first)) {

					String second = readSecondWord(line);

					if (isInstruction(second)) {

						list.add(new Symbol(first, pc.getValue(), null));// The
																			// hint
																			// algorithm
																			// implies
																			// taking
																			// the
																			// second
																			// word
																			// as
																			// the
																			// name,
																			// but
																			// the
																			// project
																			// overview
																			// slides
																			// show
																			// the
																			// first
																			// word
																			// getting
																			// taken.
																			// I've
																			// decided
																			// to
																			// follow
																			// the
																			// project
																			// overview.
						incrementPC();
					} else {
						list.add(new Symbol(first, dc.getValue(), ram.getValue(dc.getValue())));
						decrementDC();
					}
				}
			}

			return list;
		}
	}

	public void secondPass(SymbolList table, String filename) throws IOException {

		int count = 0, dcounter = 0;// named differently for clarity.

		Scanner in = new Scanner(new File(filename));

		DataOutputStream out = new DataOutputStream(new FileOutputStream(new File(createOutputName(filename))));
		while (in.hasNextLine()) {
			String line = in.nextLine();
			String first = readFirstWord(line);
			String second = readSecondWord(line);
			if (isInstruction(first)) {
				out.writeInt(buildInstruction(first, table.getLocation(second)));
				count++;
			} else if (isLabel(first)) {
				count++;
				if (isInstruction(second)) {
					String third = readThirdWord(line);
					out.writeInt(buildInstruction(second, table.getLocation(third)));

				} else if (second.equals("DC")) {
					dcounter--;// since dcounter is already 0, i'm not sure if
								// it is supposed to be initialized to 0 at
								// first or not.
				}

			}
		}
		in.close();
		for (int i = 0; i < (100 - count); i++) {
			out.writeInt(0);

		}
		while (table != null) {
			for (int i = 100 + dcounter; i < 100; i++) {
				Integer value = table.getValue(i);
				out.writeInt(value);
			}

		}
		out.close();
	}

	public boolean isLabel(String word) {
		if (word.equals("ELSE") || word.equals("FINISH") || word.equals("FIRST") || word.equals("SECOND"))
			return true;
		else
			return false;
	}

	public boolean isInstruction(String word) {
		if (word.equals("IN") || word.equals("LD") || word.equals("SUB") || word.equals("BGTR") || word.equals("BZ")
				|| word.equals("OUT") || word.equals("B") || word.equals("ADD") || word.equals("DIV")
				|| word.equals("STOP") || word.equals("MPY") || word.equals("STO"))
			return true;
		else
			return false;
	}

	public String createOutputName(String filename) {
		String temp = filename.substring(0, filename.indexOf('.'));
		String replaced = temp + ".ex";// replaces the .asm with .ex to properly
										// establish output file name.
		return replaced;
	}

	public int buildInstruction(String first, int second) {// modified from two
															// strings to better
															// appease the code
															// structure.

		String operand = second + "";
		String combined = "";
		switch (first) {
		case "STOP":
			combined = "00";
			break;
		case "LD":
			combined = "01" + operand;
			break;
		case "STO":
			combined = "02" + operand;
			break;
		case "ADD":
			combined = "03" + operand;
			break;
		case "SUB":
			combined = "04" + operand;
			break;
		case "MPY":
			combined = "05" + operand;
			break;
		case "DIV":
			combined = "06" + operand;
			break;
		case "IN":
			combined = "07" + operand;
			break;
		case "OUT":
			combined = "08" + operand;
			break;
		case "B":
			combined = "09" + operand;
			break;
		case "BGTR":
			combined = "10" + operand;
			break;
		case "BZ":
			combined = "11" + operand;
			break;
		default:
			combined = "00";
			break;
		}
		int complete = Integer.parseInt(combined);
		return complete;

	}

	public String readFirstWord(String line) {// These read methods look for the
												// space between the words to
												// divide and extract the
												// desired word depending on the
												// method, with the following
												// methods cutting out the
												// previous word to extract the
												// next.
		int i = line.indexOf(' ');
		String first = line.substring(0, i);
		if (first.contains(":")) {
			String proper = first.substring(0, first.indexOf(':'));
			return proper;
		} else {
			return first;
		}
	}

	public String readSecondWord(String line) {
		int i = line.indexOf(' ');
		String temp = line.substring(i + 1);

		if (temp.contains(" ")) {
			int j = temp.indexOf(' ');

			String second = temp.substring(0, j);

			return second;
		} else
			return temp;
	}

	public String readThirdWord(String line) {
		int i = line.indexOf(' ');
		String temp = line.substring(i + 1);
		if (temp.contains(" ")) {
			int j = temp.indexOf(' ');
			String second = temp.substring(j + 1);
			if (second.contains(" ")) {
				int k = second.indexOf(' ');
				String third = second.substring(0, k);
				return third;
			} else
				return second;
		} else
			return temp;
	}

	// Methods from past parts
	public void run() {// No parameters. Original Method.
		STOP = false;

		ram.LoadMemory();

		pc.setValue(0);

		while (!STOP) { // The computation continues until the operation
						// code reaches it's 0 value;

			this.fetch();

			this.incrementPC();

			this.execute();

		}

	}

	public void run(String file) throws FileNotFoundException, IOException {// new
																			// method
																			// that
																			// initializes
																			// content
																			// values
																			// with
																			// the
																			// contents
																			// of
																			// a
																			// file.

		STOP = false;

		ram.LoadMemory(file);

		pc.setValue(0);

		while (!STOP) {

			this.fetch();

			this.incrementPC();

			this.execute();
		}
	}

	private void fetch() {// Sets the IR value to it's designated
							// value in the memory.

		ir.setValue(ram.getValue(pc.getValue()));

	}

	private void execute() { // Checks the instruction code to determine proper
								// process to execute.
		// System.out.println("execute start"); -> used to test that every
		// execution was properly running.
		switch (((IR) ir).getOpCode()) {
		case 0:
			STOP = true;
			break;
		case 1:

			acc.setValue(ram.getValue(((IR) ir).getOperand()));

			// ram.getValue(((IR) ir).getOperand())
			break;
		case 2:

			ram.setValue(acc.getValue(), ((IR) ir).getOperand());

			break;
		case 3:

			((ACC) acc).add(ram.getValue(((IR) ir).getOperand()));

			break;
		case 4:

			((ACC) acc).subtract(ram.getValue(((IR) ir).getOperand()));

			break;
		case 5:

			((ACC) acc).multiply(ram.getValue(((IR) ir).getOperand()));

			break;
		case 6:

			((ACC) acc).divide(ram.getValue(((IR) ir).getOperand()));

			break;
		case 7:

			ram.setValue(Terminal.input(), ((IR) ir).getOperand());

			break;
		case 8:

			Terminal.output(ram.getValue(((IR) ir).getOperand()));

			break;
		case 9:

			pc.setValue(((IR) ir).getOperand());

			break;
		case 10:

			if (acc.getValue() > 0) {
				pc.setValue(((IR) ir).getOperand());
			}

			break;
		case 11:

			if (acc.getValue() == 0) {
				pc.setValue(((IR) ir).getOperand());
			}

			break;
		default:
			Terminal.output("Error. Not an instruction\n");
			STOP = true;
			;
		}
	}

	private void incrementPC() { // uses the original pc increment.
		((PC) pc).incrementValue();
	}

	private void decrementDC() {
		((PC) dc).decrementValue();
	}

}
