import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;

class JumpLine {
	ArrayDeque<Integer> constants;
	int label;

	public JumpLine() {
		constants = new ArrayDeque<Integer>();
		label = 0;
	}

	public void putConstant(int c) {
		constants.addFirst(c);
	}

	public ArrayList<String> toInstructions(ArrayList<String> caseExpression) {
		ArrayList<String> instructions = new ArrayList<String>();
		Iterator constant = constants.descendingIterator();
		while(constant.hasNext()) {
			instructions.addAll(caseExpression);
			instructions.add("CRCT "+ constant.next());
			instructions.add("CMDG");
			instructions.add("DSVF L"+ label);
		}
		return instructions;
	}
}

class JumpTable {
	int jumpTableLabel = 0;
	int afterCaseLabel = 0;
	int elseLabel = 0;

	ArrayList<String> caseExpressionInstructions;
	ArrayDeque<JumpLine> jl;

	public JumpTable() {
		jl = new ArrayDeque<JumpLine>();
	}

	public void setCaseExpressionInstructions(ArrayList<String> instructions) {
		this.caseExpressionInstructions = instructions;
	}

	public void putConstant(int c) {
		jl.peekFirst().putConstant(c);
	}

	public void setLabel(int l) {
		jl.peekFirst().label = l;
	}

	public void addJumpLine() {
		jl.addFirst(new JumpLine());
	}

	public void requireElseLabel(int label) {
		elseLabel = label;
	}

	public ArrayList<String> toInstructions() {
		ArrayList<String> instructions = new ArrayList<String>();
		Iterator<JumpLine> jl_it = jl.descendingIterator();
		instructions.add("L"+jumpTableLabel+" NADA  { JumpTable code }");
		while(jl_it.hasNext()) {
			JumpLine current_jl = jl_it.next();
			instructions.addAll(current_jl.toInstructions(caseExpressionInstructions));
		}
		return instructions;
	}
}
