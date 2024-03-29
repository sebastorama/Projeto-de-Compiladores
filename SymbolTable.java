import java.util.*;

public class SymbolTable {
	Symbol integerType;
	Symbol booleanType;
	Symbol currentProcedure;
	ArrayList<Hashtable <String, Symbol>> tables;

	ArrayList <Symbol> cachedSymbols;


	private Symbol declareRead() {
		Symbol s = new Symbol();
		s.id = "read";
		s.level = 0;
		s.category = Category.procedure;
		return s;
	}

	private Symbol declareWrite() {
		Symbol s = new Symbol();
		s.id = "write";
		s.level = 0;
		s.category = Category.procedure;
		return s;
	}
	
	private Symbol declareTrue() {
		Symbol s = new Symbol();
		s.id = "true";
		s.level = 0;
		s.value = 1;
		s.category = Category.constant;
		s.type = booleanType;
		return s;
	}

	private Symbol declareFalse() {
		Symbol s = new Symbol();
		s.id = "false";
		s.level = 0;
		s.value = 0;
		s.category = Category.constant;
		s.type = booleanType;
		return s;
	}

	private Symbol declareInteger() {
		Symbol s = new Symbol();
		s.id = "integer";
		s.level = 0;
		s.category = Category.type;
		s.nBytes = 2;
		return s;
	}
	private Symbol declareBoolean() {
		Symbol s = new Symbol();
		s.id = "boolean";
		s.level = 0;
		s.category = Category.type;
		s.nBytes = 1;
		return s;
	}

	public SymbolTable() {
		this.tables = new ArrayList<Hashtable <String, Symbol>>();
		this.cachedSymbols = new ArrayList<Symbol>();
		this.integerType = declareInteger();
		this.booleanType = declareBoolean();
		Symbol trueConst = declareTrue();
		Symbol falseConst = declareFalse();
		Symbol readProcedure = declareRead();
		Symbol writeProcedure = declareWrite();

		insert(integerType, false, 0);
		insert(booleanType, false, 0);
		insert(trueConst, false, 0);
		insert(falseConst, false, 0);
		insert(writeProcedure, false, 0);
		insert(readProcedure, false, 0);

		flushCached();
	}

	public Hashtable<String, Symbol> findOrCreateTable(int level) {
		Hashtable<String, Symbol> table;

		if(tables.size() <= level) {
			int i;
			for(i=tables.size(); i<=level; i++) {
				table = new Hashtable<String, Symbol>();
				tables.add(i, table);
			}
		}

		return tables.get(level);
	}

	public boolean declared(String id, int level) {
		return findOrCreateTable(level).containsKey(id);
	}

	public Symbol search(String id, int level) {
		int i;
		for(i = level; i>=0; i--) { 
			if(declared(id, i)) {
				return findOrCreateTable(i).get(id);
			}
		}
		return null;
	}

	public void insert(Symbol symbol, boolean class_set, int level) {
		Hashtable<String, Symbol> table = findOrCreateTable(level);
		table.put(symbol.id, symbol);
		cachedSymbols.add(symbol);
	}

	public void destroy(int level) {
		findOrCreateTable(level).clear();
	}

	public void flushCached() {
		cachedSymbols.clear();
	}

	public void setCategoryOnCached(Category cat) {
		Iterator <Symbol> s = cachedSymbols.iterator();
		while(s.hasNext()) {
			s.next().category = cat;
		}
	}

	public void setTypeOnCached(Symbol type) {
		Iterator <Symbol> s = cachedSymbols.iterator();
		while(s.hasNext()) {
			s.next().type= type;
		}
	}
	
	public void addParametersOnCachedProcedure() {
		Iterator <Symbol> s = cachedSymbols.iterator();
		while(s.hasNext()) {
			currentProcedure.parameters.add(s.next());
		}
	}

	public void setOffsetAddressOnParameters(Symbol procedure) {
		Iterator <Symbol> s = procedure.parameters.iterator();
		while(s.hasNext()) {
			Symbol currentSymbol = s.next();
			currentSymbol.offsetAddress = currentSymbol.parameterIndex-(procedure.nPar+2);
		}
	}

	public void print() {
		Iterator <Hashtable <String, Symbol>> table = tables.iterator();
		int current_level = 0;

		while(table.hasNext()) {
			Hashtable<String, Symbol> currentTable;
			currentTable = table.next();
			System.out.println("\n\nlevel: " + current_level);

			Enumeration e = currentTable.keys();
			while(e.hasMoreElements()) {
				Symbol s = currentTable.get(e.nextElement());
				s.print();
			}
			current_level++;
		}
	}
}
