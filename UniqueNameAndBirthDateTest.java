// Dylan DiGeronimo
// I pledge my honor that I have abided by the Stevens Honor System

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UniqueNameAndBirthDateTest {
	
	@Test
	public void testNormal() {
		String[][] individuals = { { "1", "Tim", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA" },
				{ "2", "Timantha", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA" },
				{ "3", "Joe", "NA", "01-01-1900", "NA", "NA", "03-15-1976", "NA", "NA" },
				{ "4", "Jane", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA" }};
		boolean expected = true;
		boolean actual = UniqueNameAndBirthDate.checkUniqueNameAndBirthDate(individuals);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testEmpty() {
		String[][] individuals = {};
		boolean expected = true;
		boolean actual = UniqueNameAndBirthDate.checkUniqueNameAndBirthDate(individuals);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSameNameDifferentBirthDate() {
		String[][] individuals = { { "1", "Tim", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA" },
				{ "2", "Tim", "NA", "01-01-1910", "NA", "NA", "NA", "NA", "NA" },
				{ "3", "Joe", "NA", "01-01-1900", "NA", "NA", "03-15-1976", "NA", "NA" },
				{ "4", "Jane", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA" }};
		boolean expected = true;
		boolean actual = UniqueNameAndBirthDate.checkUniqueNameAndBirthDate(individuals);
		assertEquals(expected, actual);
	}
	

	@Test
	public void testOneSame() {
		String[][] individuals = { { "1", "Tim", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA" },
				{ "2", "Tim", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA" },
				{ "3", "Joe", "NA", "01-01-1900", "NA", "NA", "03-15-1976", "NA", "NA" },
				{ "4", "Jane", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA" }};
		boolean expected = false;
		boolean actual = UniqueNameAndBirthDate.checkUniqueNameAndBirthDate(individuals);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testAllSame() {
		String[][] individuals = { { "1", "Tim", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA" },
				{ "2", "Tim", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA" },
				{ "3", "Tim", "NA", "01-01-1900", "NA", "NA", "03-15-1976", "NA", "NA" },
				{ "4", "Tim", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA" }};
		boolean expected = false;
		boolean actual = UniqueNameAndBirthDate.checkUniqueNameAndBirthDate(individuals);
		assertEquals(expected, actual);
	}
}
