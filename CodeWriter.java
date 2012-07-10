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

	public int markLabel() {
		this.putInstruction("L"+label_counter+" NADA");
		return this.label_counter++;
	}

	public void markLabel(int label) {
		this.putInstruction("L"+label+" NADA");
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

	public void generateLoadInstruction(Symbol s, boolean classSet) {
		if(classSet) {
			if(s.category == Category.variable) {
				this.putInstruction("CRVL "+s.level+","+s.offsetAddress);
			} else if(s.category == Category.parameter) {
				if(s.classSet) // value
					this.putInstruction("CRVL "+s.level+","+ s.offsetAddress);
				else // reference
					this.putInstruction("CRVI "+s.level+","+ s.offsetAddress);
			}
		} else {
			if(s.classSet)
				this.putInstruction("CREN "+s.level+","+s.offsetAddress);
			else
				this.putInstruction("CRVL "+s.level+","+ s.offsetAddress);
		}
	}

	public void generateLoadConstant(int i) {
		this.putInstruction("CRCT " + i );
	}

	public void generateStoreInstruction(Symbol s) {
		if(s.category == Category.variable) {
			this.putInstruction("ARMZ "+s.level+","+s.offsetAddress);
		} else if(s.category == Category.parameter) {
			if(s.classSet) // value
				this.putInstruction("ARMZ "+s.level+","+ s.offsetAddress);
			else // reference
				this.putInstruction("ARMI "+s.level+","+ s.offsetAddress);
		}
	}


	public int generateProcedureEntryInstruction(int level) {
		this.putInstruction("L"+ this.label_counter + " ENPR " + level);
		return this.label_counter++;
	}

	public void generateRelationInstruction(Relation r) {
		switch(r) {
			case equal: this.putInstruction("CMIG");
								 break;
			case different: this.putInstruction("CMDG");
								 break;
			case less: this.putInstruction("CMME");
								 break;
			case lessEqual: this.putInstruction("CMEG");
								 break;
			case greaterEqual: this.putInstruction("CMAG");
								 break;
			case greater: this.putInstruction("CMMA");
								 break;
		}
	}

	public void generateProcedureCallInstruction(Symbol s) {
		this.putInstruction("CHPR L"+s.label);
	}

	public void generateDeallocInstruction() {
		Integer sp = this.stack_pointer_offset.peekFirst();
		if(sp != null && sp > 0) {
			this.putInstruction("DMEN " + this.stack_pointer_offset.pop());
		}
	}

	public void generateProcedureReturnInstruction(Symbol s) {
		this.putInstruction("RTPR "+(s.level+1)+","+s.parameters.size());
	}

	public void setReadInstruction() {
		String s = this.output.get(this.output.size()-1);
		this.output.set(this.output.size()-1, s.replace("CRVL", "ARMZ"));
	}

	public void printCode() {
		Iterator it = output.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
