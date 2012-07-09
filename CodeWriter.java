import java.util.Iterator;
import java.util.ArrayList;

public class CodeWriter { 
	public int amen_acc;
	public int stack_pointer;
	public int label_counter;
	ArrayList<String> output;

	public CodeWriter() {
		this.amen_acc = 0;
		this.label_counter = 1;
		this.stack_pointer = 0;
		this.output =  new ArrayList<String>();
	}

	public void putInstruction(String s) {
		output.add(s);
	}

	public int branchIfFalse() {
		this.putInstruction("DSVF L"+label_counter);
		return label_counter++;
	}

	public int branch() {
		this.putInstruction("DSVS L"+label_counter);
		return label_counter++;
	}

	public void markLabel() {
		this.putInstruction("L"+label_counter+" NADA");
	}

	public int increaseAmenAcc() {
		this.amen_acc++;
		return (stack_pointer + amen_acc - 1);
	}

	public void generateAmenInstruction() {
		String s = "AMEN " + amen_acc;
		this.stack_pointer += amen_acc;
		this.amen_acc = 0;
		this.output.add(s);
	}

	public void printCode() {
		Iterator it = output.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
