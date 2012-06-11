import java.util.ArrayList;

public class Symbol {
	public String id;
	public int level;
	public boolean classSet = true; // value: true, ref: false
	public Symbol type = null;
	public Category category;
	public int nBytes = 0;
	public int value = 0;
	public int nPar = 0;
	public ArrayList parameters;

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

	public void print() {
		System.out.println(id +
				", classSet: " + classSetToS() +
				", type: " + typeToS() +
				", category: " + categoryToS()
				);
	}
}
