import java.util.Hashtable;
import java.util.ArrayList;

public class SymbolTable {
	ArrayList<Hashtable <String, Symbol>> tables;

	public SymbolTable() {
		tables = new ArrayList<Hashtable <String, Symbol>>();
	}

	public Hashtable findOrCreateTable(int level) {
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
		int i;
		for(i=0; i<=level; i++) {
			if(findOrCreateTable(i).containsKey(id)) {
				return true;
			}
		}
		return false;
	}

	public void insert(String id, boolean ref, int level) {
		Hashtable<String, Symbol> table = findOrCreateTable(level);
		Symbol symbol = new Symbol();
		table.put(id, symbol);
	}

	public void destroy(int level) {
		findOrCreateTable(level).clear();
	}

	public void setAttributes() {

	}
}
