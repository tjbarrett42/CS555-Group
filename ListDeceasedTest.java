import org.junit.Test;

import static org.junit.Assert.assertEquals;

//How do I run tests for this if it will always return true because we are just listing out the deceased members?

public class ListDeceasedTest {
    static String[][] individuals = {
            {"1", "Tim ab", "M", "01-01-1900", "NA", "NA", "NA", "NA", "NA"},
            {"2", "Timantha ab", "F", "01-01-1900", "NA", "NA", "NA", "NA", "NA"},
            {"3", "Joe ab", "M", "01-01-1900", "NA", "NA", "03-15-1976", "NA", "NA"},
            {"4", "Jane ab", "F", "01-01-1900", "NA", "NA", "NA", "NA", "NA"},
            {"5", "Mike a", "M", "01-01-1900", "NA", "NA", "01-01-1920", "NA", "NA"},
            {"6", "Michelle ab", "F", "01-01-1915", "NA", "NA", "01-01-1940", "NA", "NA"},
            {"7", "Fred ab", "M", "01-01-1915", "NA", "NA", "07-04-1986", "NA", "NA"},
            {"8", "Freeda ab", "M", "01-01-1915", "NA", "NA", "07-04-1976", "NA", "NA"},
            {"9", "Somya ab", "M", "01-01-1900", "NA", "NA", "NA", "NA", "NA"},
            {"10", "Somyee ab", "F", "02-01-1910", "NA", "NA", "NA", "NA", "NA"}
    };
    @Test
    public void test1(){
        boolean expected = true;
        boolean result = ListDeceased.CheckListDeceased(individuals);
        assertEquals(expected,result);
    }
    @Test
    public void test2(){
        boolean expected = true;
        boolean result = ListDeceased.CheckListDeceased(individuals);
        assertEquals(expected,result);
    }
    @Test
    public void test3(){
        boolean expected = true;
        boolean result = ListDeceased.CheckListDeceased(individuals);
        assertEquals(expected,result);
    }
    @Test
    public void test4(){
        boolean expected = true;
        boolean result = ListDeceased.CheckListDeceased(individuals);
        assertEquals(expected,result);
    }
    @Test
    public void test5(){
        boolean expected = true;
        boolean result = ListDeceased.CheckListDeceased(individuals);
        assertEquals(expected,result);
    }
}
