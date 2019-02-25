import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BirthBeforeMarriageTest {
    String[][] individuals = {
            {"1", "Tim", "NA", "01-01-1990", "NA", "NA", "NA", "NA", "NA"},
            {"2", "Timantha", "NA", "01-01-1990", "NA", "NA", "NA", "NA", "NA"},
            {"3", "Joe", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA"},
            {"4", "Jane", "NA", "01-01-1920", "NA", "NA", "NA", "NA", "NA"},
            {"5", "Mike", "NA", "01-01-188-", "NA", "NA", "NA", "NA", "NA"},
            {"6", "Michelle", "NA", "01-01-1920", "NA", "NA", "01-01-1940", "NA", "NA"},
            {"7", "Fred", "NA", "01-01-1951", "NA", "NA", "07-04-1986", "NA", "NA"},
            {"8", "Freeda", "NA", "01-01-1951", "NA", "NA", "07-04-1976", "NA", "NA"},
            {"9", "Somya", "NA", "01-02-1900", "NA", "NA", "NA", "NA", "NA"},
            {"10", "Somyee", "NA", "02-01-1910", "NA", "NA", "NA", "NA", "NA"}
    };

    String[] family1 = {"1", "01-02-1900", "NA", "1", "NA", "2", "NA", "NA"};
    String[] family2 = {"2", "01-01-1910", "NA", "3", "NA", "4", "NA", "NA"};
    String[] family3 = {"3", "01-01-1900", "NA", "5", "NA", "6", "NA", "NA"};
    String[] family4 = {"4", "01-01-1950", "NA", "7", "NA", "8", "NA", "NA"};
    String[] family5 = {"5", "01-01-1900", "NA", "9", "NA", "10", "NA", "NA"};


    @Test
    public void testPass(){
        boolean expected = true;
        boolean result = BirthBeforeMarriage.CheckUS02(family1,individuals);
        assertEquals(expected,result);
    }
    @Test
    public void testHusbandBirth(){
        boolean expected = false;
        boolean result = BirthBeforeMarriage.CheckUS02(family2,individuals);
        assertEquals(expected,result);
    }
    @Test
    public void testWifeBirth(){
        boolean expected = false;
        boolean result = BirthBeforeMarriage.CheckUS02(family3,individuals);
        assertEquals(expected,result);
    }
    @Test
    public void testBothBirth(){
        boolean expected = false;
        boolean result = BirthBeforeMarriage.CheckUS02(family4,individuals);
        assertEquals(expected,result);
    }
    @Test
    public void testSameDay(){
        boolean expected = false;
        boolean result = BirthBeforeMarriage.CheckUS02(family5,individuals);
        assertEquals(expected,result);
    }
}
