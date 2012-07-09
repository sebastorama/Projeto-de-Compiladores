import java.util.Iterator;
import java.util.ArrayList;
import java.util.ArrayDeque;

public class CodeWriter { 
	public int amen_acc;
	ArrayDeque<Integer> stack_pointer_offset;
	public int label_counter;
	ArrayList<String> output;

	public CodeWriter() {
		this.amen_acc = 0;
		this.label_counter = 1;
		this.stack_pointer_offset = new ArrayDeque<Integer>();
		this.stack_pointer_offset.addFirst(new Integer(0));
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

	public int increaseAmenAcc(int level) {
		this.amen_acc++;
		if(level > (stack_pointer_offset.size()-1) ) 
			stack_pointer_offset.push(new Integer(0));
		return (stack_pointer_offset.peekFirst() + amen_acc - 1);
	}

	public void generateAmenInstruction() {
		String s = "AMEN " + amen_acc;
		this.stack_pointer_offset.push(
				this.stack_pointer_offset.removeFirst()+amen_acc
				);
		this.amen_acc = 0;
		this.output.add(s);
	}

	public void generateLoadInstruction(Symbol s) {
		if(s.category == Category.variable) {
			this.putInstruction("CRVL "+s.level+" "+s.offsetAddress);
		} else if(s.category == Category.parameter) {
			if(s.classSet) // value
				this.putInstruction("CRVL "+s.level+" "+ s.offsetAddress);
			else // reference
				this.putInstruction("CRVI "+s.level+" "+ s.offsetAddress);
		}
	}

	public void printCode() {
		Iterator it = output.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
