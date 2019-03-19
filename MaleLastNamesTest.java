import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MaleLastNamesTest {
    static String[][] individuals = {
            {"1", "Tim ab", "M", "01-01-1900", "NA", "NA", "NA", "NA", "NA"},
            {"2", "Timantha ab", "F", "01-01-1900", "NA", "NA", "NA", "NA", "NA"},
            {"3", "Joe ab", "M", "01-01-1900", "NA", "NA", "03-15-1976", "NA", "NA"},
            {"4", "Jane a", "M", "01-01-1900", "NA", "NA", "NA", "NA", "NA"},
            {"5", "Mike ab", "M", "01-01-1900", "NA", "NA", "01-01-1920", "NA", "NA"},
            {"6", "Michelle ab", "F", "01-01-1915", "NA", "NA", "01-01-1940", "NA", "NA"},
            {"7", "Fred d", "F", "01-01-1915", "NA", "NA", "07-04-1986", "NA", "NA"},
            {"8", "Freeda d", "F", "01-01-1915", "NA", "NA", "07-04-1976", "NA", "NA"},
            {"9", "Somya ab", "M", "01-01-1900", "NA", "NA", "NA", "NA", "NA"},
            {"10", "Somyee ab", "F", "02-01-1910", "NA", "NA", "NA", "NA", "NA"}
    };
    static String[][] families1 = {{"1", "01-01-1900", "NA", "11", "John ab", "NA", "NA", "1 2"}};
    static String[][] families2 = {{"1", "01-01-1900", "NA", "11", "John ab", "NA", "NA", "3 4"}};
    static String[][] families3 = {{"1", "01-01-1900", "NA", "11", "John c", "NA", "NA", "5 6"}};
    static String[][] families4 = {{"1", "01-01-1900", "NA", "11", "John c", "NA", "NA", "7 8"}};
    static String[][] families5 = {{"1", "01-01-1900", "NA", "11", "NA", "NA", "NA", "7 8"}};

    @Test
    public void testOk(){
        boolean expected = true;
        boolean result = MaleLastNames.CheckMaleLastNames(families1,individuals);
        assertEquals(expected,result);
    }
    @Test
    public void testWrong(){
        boolean expected = false;
        boolean result = MaleLastNames.CheckMaleLastNames(families2,individuals);
        assertEquals(expected,result);
    }
    @Test
    public void testHusbandLastName(){
        boolean expected = false;
        boolean result = MaleLastNames.CheckMaleLastNames(families3,individuals);
        assertEquals(expected,result);
    }
    @Test
    public void testWrongButFemale(){
        boolean expected = true;
        boolean result = MaleLastNames.CheckMaleLastNames(families4,individuals);
        assertEquals(expected,result);
    }
    @Test
    public void testNoHusband(){
        boolean expected = false;
        boolean result = MaleLastNames.CheckMaleLastNames(families5,individuals);
        assertEquals(expected,result);
    }
}
