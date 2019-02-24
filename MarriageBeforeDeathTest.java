// Dylan DiGeronimo and Somya Gambhir
// I pledge my honor that I have abided by the Stevens Honor System

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MarriageBeforeDeathTest {
	
	

	String[] family1 = {"1", "01-01-1900", "NA", "1", "NA", "2", "NA", "NA"};
    String[] family2 = {"2", "01-01-1910", "NA", "3", "NA", "4", "NA", "NA"};
    String[] family3 = {"3", "01-01-1930", "NA", "5", "NA", "6", "NA", "NA"};
    String[] family4 = {"4", "01-01-1980", "NA", "7", "NA", "8", "NA", "NA"};
    String[] family5 = {"5", "NA", "NA", "9", "NA", "10", "NA", "NA"};
    
 
    @Test
    public void testNoOneDead() {
    	// Expect true when no one is dead
    	boolean expected = true;
    	boolean result = MarriageBeforeDeath.checkMarriageBeforeDeath(family1);
    	assertEquals(expected, result);
    }
    
    @Test
    public void testOneDeadAfterMarriage() {
    	// Expect true when one is dead after the marriage
    	boolean expected = true;
    	boolean result = MarriageBeforeDeath.checkMarriageBeforeDeath(family2);
    	assertEquals(expected, result);
    }
    
    @Test
    public void testHusbandDeadBeforeMarriage() {
    	// Expect false when one died before the marriage and one died after
    	boolean expected = false;
    	boolean result = MarriageBeforeDeath.checkMarriageBeforeDeath(family3);
    	assertEquals(expected, result);
    }
    
    @Test
    public void testWifeDeadBeforeMarriage() {
    	// Expect false when both died before the marriage
    	boolean expected = false;
    	boolean result = MarriageBeforeDeath.checkMarriageBeforeDeath(family4);
    	assertEquals(expected, result);
    }
    
    @Test
    public void testNeverMarried() {
    	// Expect true if they were never married
    	boolean expected = true;
    	boolean result = MarriageBeforeDeath.checkMarriageBeforeDeath(family5);
    	assertEquals(expected, result);
    }
}
