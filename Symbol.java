import java.util.*;

public class Symbol {
	public String id;
	public int level;
	public boolean classSet = true; // value: true, ref: false
	public Symbol type = null;
	public Category category;
	public int nBytes = 0;
	public int value = 0;
	public int nPar = 0;
	public int offsetAddress = 0;
	public int parameterIndex = 0;
	public int label = 0;
	public ArrayList<Symbol> parameters;

	public Symbol() {
		parameters = new ArrayList<Symbol>();
	}

	public String classSetToS() {
		if(classSet)
			return "value";
		else
			return "reference";

	}

	public String typeToS() {
		if(type!=null)
			return type.id;
		return "";
	}

	public String categoryToS() {
		if(category == Category.procedure)
			return "procedure";
		else if(category == Category.variable)
			return "variable";
		else if(category == Category.parameter)
			return "parameter";

		return "";
	}

	public String parametersToS() {
		Iterator <Symbol> parameter = parameters.iterator();
		String result = "";
		while(parameter.hasNext()) {
			result += parameter.next().id + " ";
		}
		return result;
	}

	public void print() {
		String result = "";
		result += id +
				", classSet: " + classSetToS() +
				", type: " + typeToS() +
				", category: " + categoryToS();
		if(category == Category.procedure) {
			result += "\n\tparameters: " + parametersToS();
		}

		System.out.println(result);
	}
}
