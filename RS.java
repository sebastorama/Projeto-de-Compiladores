import java.util.*;
import java.util.ArrayList;

public class RS {
	public String id;
	public ArrayList<String> errors;
	public int currentLevel = 0;
	public Symbol c_type;
	public Symbol cachedType;
	public int value;
	public boolean cachedClassSet; // value: true, ref: false
	public SymbolTable symbolTable;
	public Token cachedToken;
	public Symbol cachedProcedure;
	public int cachedParameterCount;

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

		} else if( n.equals("5") ) {
			if(symbolTable.declared(t.toString(), currentLevel)) {
				errors.add("Identificador \""+t.toString()+"\" já declarado"+
						", linha: "+t.beginLine+", coluna: "+t.beginColumn);
			} else {
				Symbol s = new Symbol();
				s.id = t.toString(); 
				s.level = currentLevel;
				s.nPar = 0;
				symbolTable.insert(s, false, currentLevel);
			  symbolTable.setCategoryOnCached(Category.procedure);
				symbolTable.currentProcedure = s;
				symbolTable.flushCached();
				currentLevel++;
				cachedProcedure = s;
			}
		} else if( n.equals("8") ) {
			symbolTable.destroy(currentLevel);
			currentLevel--;
		} else if( n.equals("9") ) {

			Symbol s = symbolTable.search(t.toString(), 0); 
			if(s == null || s.category != Category.type) {
				errors.add("Tipo \""+t.toString()+"\" não definido"+
						", linha: "+t.beginLine+", coluna: "+ t.beginColumn);
				cachedType = null;
			} else {
				cachedType = s;
			}

		} else if( n.equals("14") ) {
			this.cachedClassSet = true;
		} else if( n.equals("15") ) {
			this.cachedClassSet = false;
		} else if( n.equals("18") ) {	
			if(symbolTable.declared(t.toString(), currentLevel)) {
				errors.add("Identificador \""+t.toString()+"\" já declarado"+
						", linha: "+t.beginLine+", coluna: "+ t.beginColumn);
			} else {
				Symbol s = new Symbol();
				s.id = t.toString();
				s.level = currentLevel;
				s.category = Category.parameter;
				s.classSet = cachedClassSet;
				symbolTable.insert(s, false, currentLevel);
			}
			cachedProcedure.nPar++;
		} else if ( n.equals("19") ) {
			this.process("9", t);
			symbolTable.setTypeOnCached(cachedType);
			symbolTable.flushCached();
		} else if (n.equals("21") ) {
			Symbol s = symbolTable.search(t.toString(), currentLevel);
			if(s == null) {
				errors.add("Identificador \""+t.toString()+"\" não declarado"+
						", linha: "+t.beginLine+", coluna: "+ t.beginColumn);
			}
		} else if (n.equals("23") ) {
			cachedParameterCount++;
		} else if (n.equals("24") ) {
			Symbol s = symbolTable.search(t.toString(), currentLevel);
			if(s != null && s.category == Category.procedure) {
				if(cachedParameterCount != s.nPar) {
					errors.add("Número de parâmetros incompatível." +
							"\nesperado: " + s.nPar +
							"\n obtido: " + cachedParameterCount +
							"\n linha: "+t.beginLine);
				}
			}
		} else if (n.equals("25") ) {
			Symbol s = symbolTable.search(t.toString(), currentLevel);
			if(s != null && s.category != Category.procedure) {
				errors.add("Identificador \""+t.toString()+"\" não é procedimento"+
						", linha: "+t.beginLine+", coluna: "+ t.beginColumn);
			} else {
				cachedParameterCount = 0;
			}
		} else if (n.equals("25'") ) {
			Symbol s = symbolTable.search(t.toString(), currentLevel);
			if(s == null ||
					(s.category != Category.variable && 
					s.category != Category.procedure &&
					s.category != Category.parameter)
					) {
				errors.add("Função, procedimento ou variável \""+
						t.toString() +
						"\" não definido" +
						", linha: "+t.beginLine+", coluna: "+ t.beginColumn);
			}
		} else if (n.equals("26")) {
			int i = Integer.parseInt(t.toString());
			if(i>32000 || i<-32000) {
				errors.add("Valor fora dos limites permitidos \""+
						t.toString() +
						"\"" +
						", linha: "+t.beginLine+", coluna: "+ t.beginColumn);
			}
		}
	}

	public void printSymbolTable() {
		symbolTable.print();
	}
}
