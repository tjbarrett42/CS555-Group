import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BirthBeforeDeathTest {
    static String[] individual1 = {"1", "Somya", "NA", "01-01-1940", "NA", "NA", "NA", "NA", "NA"};
    static String[] individual2 = {"2", "Dylan", "NA", "01-01-1940", "NA", "NA", "01-01-1950", "NA", "NA"};
    static String[] individual3 = {"3", "Tim", "NA", "01-01-1940", "NA", "NA", "01-01-1940", "NA", "NA"};
    static String[] individual4 = {"4", "Sam", "NA", "01-01-1940", "NA", "NA", "01-02-1930", "NA", "NA"};
    static String[] individual5 = {"5", "Dan", "NA", "01-01-1940", "NA", "NA", "01-01-1930", "NA", "NA"};

    @Test
    public void testNoDeath(){
        boolean expected = true;
        boolean result = BirthBeforeDeath.CheckUS03(individual1);
        assertEquals(expected,result);
    }
    @Test
    public void testBirthBefore(){
        boolean expected = true;
        boolean result = BirthBeforeDeath.CheckUS03(individual2);
        assertEquals(expected,result);
    }
    @Test
    public void testSameDay(){
        boolean expected = true;
        boolean result = BirthBeforeDeath.CheckUS03(individual3);
        assertEquals(expected,result);
    }
    @Test
    public void testOneDayOff(){
        boolean expected = true;
        boolean result = BirthBeforeDeath.CheckUS03(individual4);
        assertEquals(expected,result);
    }
    @Test
    public void testPass(){
        boolean expected = true;
        boolean result = BirthBeforeDeath.CheckUS03(individual5);
        assertEquals(expected,result);
    }
}
