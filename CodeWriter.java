import java.util.Iterator;
import java.util.ArrayList;

public class CodeWriter { 
	public int amen_acc;
	public int stack_pointer;
	public int label_counter;
	ArrayList<String> output;

	public CodeWriter() {
		this.amen_acc = 0;
		this.label_counter = 0;
		this.stack_pointer = 0;
		this.output =  new ArrayList<String>();
	}

	public void putInstruction(String s) {
		output.add(s);
	}

	public int increaseAmenAcc() {
		this.amen_acc++;
		return (stack_pointer + amen_acc - 1);
	}

	public void printCode() {
		Iterator it = output.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
