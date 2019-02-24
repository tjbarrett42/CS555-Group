// Dylan DiGeronimo
// I pledge my honor that I have abided by the Stevens Honor System

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MarriageBeforeDivorceTest {

    String[] family1 = {"1", "01-01-1900", "01-01-1910", "NA", "NA", "NA", "NA", "NA"};
    String[] family2 = {"2", "01-01-1910", "01-01-1900", "NA", "NA", "NA", "NA", "NA"};
    String[] family3 = {"3", "01-01-1900", "01-01-1900", "NA", "NA", "NA", "NA", "NA"};
    String[] family4 = {"4", "01-01-1900", "NA", "NA", "NA", "NA", "NA", "NA"};
    String[] family5 = {"5", "NA", "01-01-1910", "NA", "NA", "NA", "NA", "NA"};

    @Test
    public void testMarriageBeforeDivorce() {
        // Expect true when the marriage is before the divorce
        boolean expected = true;
        boolean result = MarriageBeforeDivorce.CheckMarriageBeforeDivorce(family1);
        assertEquals(expected, result);
    }

    @Test
    public void testMarriageAfterDivorce() {
        // Expect false when the marriage is after the divorce
        boolean expected = false;
        boolean result = MarriageBeforeDivorce.CheckMarriageBeforeDivorce(family2);
        assertEquals(expected, result);
    }
    
    @Test
    public void testMarriageSameAsDivorce() {
        // Expect true when the marriage is on the same day as the divorce
        boolean expected = true;
        boolean result = MarriageBeforeDivorce.CheckMarriageBeforeDivorce(family3);
        assertEquals(expected, result);
    }

    @Test
    public void testNonexistentDivorce() {
        // Expect true when there is a marriage but no divorce
        boolean expected = true;
        boolean result = MarriageBeforeDivorce.CheckMarriageBeforeDivorce(family4);
        assertEquals(expected, result);
    }

    @Test
    public void testNonexistentMarriage() {
        // Expect false when there is a divorce but no marriage
        boolean expected = false;
        boolean result = MarriageBeforeDivorce.CheckMarriageBeforeDivorce(family5);
        assertEquals(expected, result);
    }
}