//Tim Barrett
//I pledge my honor that I have abided by the stevens honor system.

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FewerThan15SiblingsTest {

    String[][] families = {
        {"1", "NA", "NA", "NA", "NA", "NA", "NA", "NA", "1"},
        {"2", "NA", "NA", "NA", "NA", "NA", "NA", "NA", "2 3 4"},
        {"3", "NA", "NA", "NA", "NA", "NA", "NA", "NA", "5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20"},
        {"4", "NA", "NA", "NA", "NA", "NA", "NA", "NA", "NA"},

    };

    String[] individual1 = {"1", "Tim", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA"};
    String[] individual2 = {"2", "Timantha", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA"};
    String[] individual3 = {"3", "Joe", "NA", "01-01-1915", "NA", "NA", "NA", "NA", "NA"};
    String[] individual4 = {"4", "Jane", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA"};
    String[] indiviudal5 = {"5", "Mike", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA"};

    @Test
    public void a() {
        // Expect true only child as individual 1
        boolean expected = true;
        boolean result = FewerThan15Siblings.CheckFewerThan15Siblings(families, individual1);
        assertEquals(expected, result);
    }

    @Test
    public void b() {
        // Expect true when having two siblings as indiviudal 2
        boolean expected = true;
        boolean result = FewerThan15Siblings.CheckFewerThan15Siblings(families, individual2);
        assertEquals(expected, result);
    }

    @Test
    public void c() {
        // Expect true when having two siblings as indiviudal 3
        boolean expected = true;
        boolean result = FewerThan15Siblings.CheckFewerThan15Siblings(families, individual3);
        assertEquals(expected, result);
    }

    @Test
    public void d() {
        // Expect true when having two siblings as indiviudal 4
        boolean expected = true;
        boolean result = FewerThan15Siblings.CheckFewerThan15Siblings(families, individual5);
        assertEquals(expected, result);
    }

    @Test
    public void d() {
        // Expect false when having 15 siblings as indiviudal 5
        boolean expected = false;
        boolean result = FewerThan15Siblings.CheckFewerThan15Siblings(families, individual5);
        assertEquals(expected, result);
    }
}