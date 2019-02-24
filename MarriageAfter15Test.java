//Tim Barrett
//I pledge my honor that I have abided by the stevens honor system.

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MarriageAfter15Test {

    String[][] individuals = {
        {"1", "Tim", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA"},
        {"2", "Timantha", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA"},
        {"3", "Joe", "NA", "01-01-1915", "NA", "NA", "NA", "NA", "NA"},
        {"4", "Jane", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA"},
        {"5", "Mike", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA"},
        {"6", "Michelle", "NA", "01-01-1915", "NA", "NA", "NA", "NA", "NA"},
        {"7", "Dylan", "NA", "01-01-1915", "NA", "NA", "NA", "NA", "NA"},
        {"8", "Dylanie", "NA", "01-01-1915", "NA", "NA", "NA", "NA", "NA"},
        {"9", "Somya", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA"},
        {"10", "Somyee", "NA", "02-01-1910", "NA", "NA", "NA", "NA", "NA"}

    };

    String[] family1 = {"1", "01-01-1925", "NA", "1", "NA", "2", "NA", "NA"};
    String[] family2 = {"2", "01-01-1925", "NA", "3", "NA", "4", "NA", "NA"};
    String[] family3 = {"3", "01-01-1925", "NA", "5", "NA", "6", "NA", "NA"};
    String[] family4 = {"4", "01-01-1925", "NA", "7", "NA", "8", "NA", "NA"};
    String[] family5 = {"5", "01-01-1925", "NA", "9", "NA", "10", "NA", "NA"};

    @Test
    public void a() {
        // Expect true when the marriage is after both husband and wife are 15
        boolean expected = true;
        boolean result = MarriageAfter15.CheckMarriageAfter15(family1, individuals);
        assertEquals(expected, result);
    }

    @Test
    public void b() {
        // Expect false, joe was under 15
        boolean expected = false;
        boolean result = MarriageAfter15.CheckMarriageAfter15(family2, individuals);
        assertEquals(expected, result);
    }

    @Test
    public void c() {
        // Expect false, michelle was under 15
        boolean expected = false;
        boolean result = MarriageAfter15.CheckMarriageAfter15(family3, individuals);
        assertEquals(expected, result);
    }
    @Test
    public void d() {
        // Expect false, both under 15
        boolean expected = false;
        boolean result = MarriageAfter15.CheckMarriageAfter15(family4, individuals);
        assertEquals(expected, result);
    }

    @Test
    public void e() {
        // Expect false, somyee barely under 15 when
        boolean expected = false;
        boolean result = MarriageAfter15.CheckMarriageAfter15(family5, individuals);
        assertEquals(expected, result);
    }
}