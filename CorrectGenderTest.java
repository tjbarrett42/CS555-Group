// Dylan DiGeronimo
// I pledge my honor that I have abided by the Stevens Honor System

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CorrectGenderTest {
	
	String[] family1 = {"1", "01-01-1900", "NA", "1", "NA", "2", "NA", "NA"};
    String[] family2 = {"2", "01-01-1910", "NA", "3", "NA", "4", "NA", "NA"};
    String[] family3 = {"3", "01-01-1930", "NA", "5", "NA", "6", "NA", "NA"};
    String[] family4 = {"4", "01-01-1980", "NA", "7", "NA", "8", "NA", "NA"};
    String[] family5 = {"5", "NA", "NA", "9", "NA", "10", "NA", "NA"};
	
	@Test
	public void test1() {
		String[][] families = { {"1", "01-01-1900", "NA", "1", "NA", "2", "NA", "NA"} };
		String[][] individuals = { 
				{ "1", "Tim", "Male", "01-01-1900", "NA", "NA", "NA", "NA", "NA" },
				{ "2", "Timantha", "Female", "01-01-1900", "NA", "NA", "NA", "NA", "NA" },
				{ "3", "Joe", "NA", "01-01-1900", "NA", "NA", "03-15-1976", "NA", "NA" },
				{ "4", "Jane", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA" }
		};
		boolean expected = true;
		boolean actual = CorrectGender.checkCorrectGender(families, individuals);
		assertEquals(expected, actual);
	}
	
	@Test
	public void test2() {
		String[][] families = {};
		String[][] individuals = {};
		boolean expected = true;
		boolean actual = CorrectGender.checkCorrectGender(families, individuals);
		assertEquals(expected, actual);
	}
	
	@Test
	public void test3() {
		String[][] families = {
				{"1", "01-01-1900", "NA", "1", "NA", "2", "NA", "NA"},
				{"2", "01-01-1910", "NA", "3", "NA", "4", "NA", "NA"},
		};
		String[][] individuals = { 
				{ "1", "Tim", "Male", "01-01-1900", "NA", "NA", "NA", "NA", "NA" },
				{ "2", "Timantha", "Female", "01-01-1900", "NA", "NA", "NA", "NA", "NA" },
				{ "3", "Joe", "Male", "01-01-1900", "NA", "NA", "03-15-1976", "NA", "NA" },
				{ "4", "Jane", "Male", "01-01-1900", "NA", "NA", "NA", "NA", "NA" }
		};
		boolean expected = false;
		boolean actual = CorrectGender.checkCorrectGender(families, individuals);
		assertEquals(expected, actual);	
	}
	
	@Test
	public void test4() {
		String[][] families = {
				{"1", "01-01-1900", "NA", "1", "NA", "2", "NA", "NA"},
				{"2", "01-01-1910", "NA", "3", "NA", "4", "NA", "NA"},
		};
		String[][] individuals = { 
				{ "1", "Tim", "Male", "01-01-1900", "NA", "NA", "NA", "NA", "NA" },
				{ "2", "Timantha", "Female", "01-01-1900", "NA", "NA", "NA", "NA", "NA" },
				{ "3", "Joe", "Female", "01-01-1900", "NA", "NA", "03-15-1976", "NA", "NA" },
				{ "4", "Jane", "Male", "01-01-1900", "NA", "NA", "NA", "NA", "NA" }
		};
		boolean expected = false;
		boolean actual = CorrectGender.checkCorrectGender(families, individuals);
		assertEquals(expected, actual);	
	}
	
	@Test
	public void test5() {
		String[][] families = {
				{"1", "01-01-1900", "NA", "1", "NA", "2", "NA", "NA"},
				{"2", "01-01-1910", "NA", "3", "NA", "4", "NA", "NA"},
		};
		String[][] individuals = { 
				{ "1", "Tim", "Female", "01-01-1900", "NA", "NA", "NA", "NA", "NA" },
				{ "2", "Timantha", "Female", "01-01-1900", "NA", "NA", "NA", "NA", "NA" },
				{ "3", "Joe", "Female", "01-01-1900", "NA", "NA", "03-15-1976", "NA", "NA" },
				{ "4", "Jane", "Male", "01-01-1900", "NA", "NA", "NA", "NA", "NA" }
		};
		boolean expected = false;
		boolean actual = CorrectGender.checkCorrectGender(families, individuals);
		assertEquals(expected, actual);	
	}
}