import org.junit.*;
import static org.junit.Assert.*;

public class SymbolTableTest {
	Symbol s;
	@Before
	public void initialize() {
		s = new Symbol();
	}
	@Test
	public void test_declared() {
		SymbolTable symbol_table = new SymbolTable();
		s.id = "symbol1";
		symbol_table.insert(s, false, 0);
		assertTrue("Symbol must be declared", symbol_table.declared("symbol1", 0));
	}

	@Test
	public void test_undeclared() {
		SymbolTable symbol_table = new SymbolTable();
		s.id = "symbol1";
		symbol_table.insert(s, false, 0);
		assertFalse("Symbol must not be declared", symbol_table.declared("symbol2", 0));
	}

	@Test
	public void test_undeclared_on_different_lvl() {
		SymbolTable symbol_table = new SymbolTable();
		s.id = "symbol1";
		symbol_table.insert(s, false, 0);
		assertFalse("Symbol must not be declared", symbol_table.declared("symbol1", 1));
	}

	@Test
	public void test_destroy_level() {
		SymbolTable symbol_table = new SymbolTable();

		s = new Symbol();
		s.id = "symbol1";
		symbol_table.insert(s, false, 0);

		s = new Symbol();
		s.id = "symbol2";
		symbol_table.insert(s, false, 1);

		s = new Symbol();
		s.id = "symbol3";
		symbol_table.insert(s, false, 1);

		s = new Symbol();
		s.id = "symbol4";
		symbol_table.insert(s, false, 1);

		symbol_table.print();

		symbol_table.destroy(1);
		assertTrue("symbol1 must be declared", symbol_table.declared("symbol1", 0));
		assertFalse("symbol2 must not exist", symbol_table.declared("symbol2", 1));
		assertFalse("symbol3 must not exist", symbol_table.declared("symbol3", 1));
		assertFalse("symbol4 must not exist", symbol_table.declared("symbol4", 1));
	}

	@Test
	public void test_search() {
		SymbolTable symbolTable = new SymbolTable();
		assertNotNull(symbolTable.search("integer", 0));
		assertEquals(Category.type, symbolTable.search("integer", 0).category);
	}


	@Test
	public void test_set_category_on_cached() {
		SymbolTable symbolTable = new SymbolTable();
		Symbol var_s1 = new Symbol(); var_s1.id = "id1";
		Symbol var_s2 = new Symbol(); var_s2.id = "id2";
		symbolTable.insert(var_s1, false, 0);
		symbolTable.insert(var_s2, false, 0);
		symbolTable.setCategoryOnCached(Category.variable);
		symbolTable.flushCached();
		assertEquals(Category.variable, var_s1.category);
		assertEquals(Category.variable, var_s2.category);
	}

	@Test
	public void test_set_type_on_cached() {
		SymbolTable symbolTable = new SymbolTable();
		Symbol var_s1 = new Symbol(); var_s1.id = "id1";
		Symbol var_s2 = new Symbol(); var_s2.id = "id2";
		symbolTable.insert(var_s1, false, 0);
		symbolTable.insert(var_s2, false, 0);
		symbolTable.setTypeOnCached(symbolTable.integerType);
		assertEquals(symbolTable.integerType, var_s1.type);
		assertEquals(symbolTable.integerType, var_s2.type);
	}
}
