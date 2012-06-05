import org.junit.*;
import static org.junit.Assert.*;

public class SymbolTableTest {
	@Test
	public void test_declared() {
		SymbolTable symbol_table = new SymbolTable();
		symbol_table.insert("symbol1", false, 0);
		assertTrue("Symbol must be declared", symbol_table.declared("symbol1", 0));
	}

	@Test
	public void test_undeclared() {
		SymbolTable symbol_table = new SymbolTable();
		symbol_table.insert("symbol1", false, 0);
		assertFalse("Symbol must not be declared", symbol_table.declared("symbol2", 0));
	}

	@Test
	public void test_declared_on_different_lvl() {
		SymbolTable symbol_table = new SymbolTable();
		symbol_table.insert("symbol1", false, 0);
		assertTrue("Symbol must be declared", symbol_table.declared("symbol1", 1));
	}

	@Test
	public void test_undeclared_on_different_lvl() {
		SymbolTable symbol_table = new SymbolTable();
		symbol_table.insert("symbol1", false, 1);
		assertFalse("Symbol must not be declared", symbol_table.declared("symbol1", 0));
	}

	@Test
	public void test_destroy_level() {
		SymbolTable symbol_table = new SymbolTable();
		symbol_table.insert("symbol1", false, 0);
		symbol_table.insert("symbol2", false, 1);
		symbol_table.insert("symbol3", false, 1);
		symbol_table.insert("symbol4", false, 1);

		symbol_table.destroy(1);
		assertTrue("Symbol must not be declared", symbol_table.declared("symbol1", 0));
		assertFalse("symbol2 must not exist", symbol_table.declared("symbol2", 1));
		assertFalse("symbol3 must not exist", symbol_table.declared("symbol3", 1));
		assertFalse("symbol4 must not exist", symbol_table.declared("symbol4", 1));
	}
}
