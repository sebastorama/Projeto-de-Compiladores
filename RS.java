import java.util.*;
import java.util.ArrayList;

public class RS {
	public String id;
	public ArrayList<String> errors;
	public int currentLevel;
	public Symbol c_type;
	public Symbol cachedType;
	public int value;
	public SymbolTable symbolTable;

	public RS() {
		symbolTable = new SymbolTable();
		errors = new ArrayList<String>();
		currentLevel = 0;
	}

	public boolean success() {
		if(errors.size() == 0)
			return true;
		else
			return false;
	}

	public void printErrors() {
		Iterator<String> error = errors.iterator();
		while( error.hasNext() ) {
			System.out.println(error.next());
		}
	}

	public void process(String n, Token t) {
		if( n.equals("0") ) {

		} else if( n.equals("0'") ) {

		} else if( n.equals("0''") ) {

			Symbol s = symbolTable.search(t.toString(), 0);
			if(s == null || s.category != Category.constant) {
				errors.add("Constante \""+t.toString()+"\" não definida"+
						", linha: "+t.beginLine+", coluna: "+ t.beginColumn);
			} else {
				c_type = s.type;
				value = s.value;
			}

		} else if( n.equals("0'''") ) {

			c_type = symbolTable.integerType;
			value = Integer.parseInt(t.toString());

		} else if( n.equals("3") ) {
			if(symbolTable.declared(t.toString(), currentLevel)) {
				errors.add("Identificador \""+t.toString()+"\" já declarado"+
						", linha: "+t.beginLine+", coluna: "+ t.beginColumn);

			} else {
				Symbol s = new Symbol();
				s.id = t.toString(); 
				s.level = currentLevel;
				symbolTable.insert(s, false, currentLevel); 
			}
		} else if( n.equals("4") ) {

			symbolTable.setCategoryOnCached(Category.variable);
			symbolTable.setTypeOnCached(cachedType);
			symbolTable.flushCached();

		} else if( n.equals("9") ) {

			Symbol s = symbolTable.search(t.toString(), 0); 
			if(s == null || s.category != Category.type) {
				errors.add("Tipo \""+t.toString()+"\" não definido"+
						", linha: "+t.beginLine+", coluna: "+ t.beginColumn);
				cachedType = null;
			} else {
				cachedType = s;
			}

		}
	}
}
