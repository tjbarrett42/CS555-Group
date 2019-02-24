//Tim Barrett
//I pledge my honor that I have abided by the Stevens Honor System

public class FewerThan15Siblings extends GEDParser {

    //Check if individual has fewer than 15 siblings

    public static boolean CheckFewerThan15Siblings(String[][] families, String[] individual){
        String individualID = individual[0];

        for (int i = 0; i < families.length; i++){
            //Iterate through families
            String[] siblings = families[i][7].split(" ");
            //Create array of children element taken from families. Check if over/under 15 siblings
            boolean hasFewerThan15Siblings = true;
            if (siblings.length >= 16){
                //Less than or equal 16 instead of 15, because individual does not count as a sibling to himself
                hasFewerThan15Siblings = false;
            }
            for (int j = 0; j < siblings.length; i++){
                //If this is the id we are looking for, return the value of hasFewerThan15Siblings,
                // which will be false if the current siblings.length is equal or greater than 16
                if (siblings[j] == individualID){
                    return hasFewerThan15Siblings;
                }
            }
        }
        return true;
    }
}