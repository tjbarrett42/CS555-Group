// Dylan DiGeronimo and Somya Gambhir
// I pledge my honor that I have abided by the Stevens Honor System

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

public class LessThan150Test {
	
	@Test
	public void testDeath150thBirthday() {
		String guy[] = {"0", "Guy", "NA", "01-01-1800", "NA", "NA", "01-01-1950", "NA", "NA"};
		boolean expected = false;
		boolean actual = LessThan150.checkLessThan150(guy);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testBorn150YearsAgoTodayAndAlive() {
		String guy[] = {"0", "Guy", "NA", "03-06-1869", "NA", "NA", "NA", "NA", "NA"};
		boolean expected = false;
		boolean actual = LessThan150.checkLessThan150(guy);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testBorn150YearsAgoTodayAndDead() {
		String guy[] = {"0", "Guy", "NA", "03-06-1869", "NA", "NA", "05-24-1899", "NA", "NA"};
		boolean expected = true;
		boolean actual = LessThan150.checkLessThan150(guy);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testBornLessThan150YearsAgoAndAlive() {
		String guy[] = {"0", "Guy", "NA", "03-06-1969", "NA", "NA", "NA", "NA", "NA"};
		boolean expected = true;
		boolean actual = LessThan150.checkLessThan150(guy);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testBornToday() {
		String guy[] = {"0", "Guy", "NA", "03-06-2019", "NA", "NA", "NA", "NA", "NA"};
		boolean expected = true;
		boolean actual = LessThan150.checkLessThan150(guy);
		assertEquals(expected, actual);
	}
   
}
