// Dylan DiGeronimo
// I pledge my honor that I have abided by the Stevens Honor System

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UniqueIDsTest {
	
	@Test
	public void test1() {
		String[][] individuals = { { "1", "Tim", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA" },
				{ "2", "Timantha", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA" },
				{ "3", "Joe", "NA", "01-01-1900", "NA", "NA", "03-15-1976", "NA", "NA" },
				{ "4", "Jane", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA" }};
		boolean expected = true;
		boolean actual = UniqueIDs.checkUniqueIDs(individuals);
		assertEquals(expected, actual);
	}
	
	@Test
	public void test2() {
		String[][] individuals = {};
		boolean expected = true;
		boolean actual = UniqueIDs.checkUniqueIDs(individuals);
		assertEquals(expected, actual);
	}
	
	@Test
	public void test3() {
		String[][] individuals = { { "1", "Tim", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA" },
				{ "1", "Timantha", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA" },
				{ "1", "Joe", "NA", "01-01-1900", "NA", "NA", "03-15-1976", "NA", "NA" },
				{ "1", "Jane", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA" }};
		boolean expected = false;
		boolean actual = UniqueIDs.checkUniqueIDs(individuals);
		assertEquals(expected, actual);
	}
	
	@Test
	public void test4() {
		String[][] individuals = { { "1", "Tim", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA" },
				{ "2", "Timantha", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA" },
				{ "3", "Joe", "NA", "01-01-1900", "NA", "NA", "03-15-1976", "NA", "NA" },
				{ "1", "Jane", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA" }};
		boolean expected = false;
		boolean actual = UniqueIDs.checkUniqueIDs(individuals);
		assertEquals(expected, actual);
	}
	
	@Test
	public void test5() {
		String[][] individuals = { { "1", "Tim", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA" } };
		boolean expected = true;
		boolean actual = UniqueIDs.checkUniqueIDs(individuals);
		assertEquals(expected, actual);
	}
}
