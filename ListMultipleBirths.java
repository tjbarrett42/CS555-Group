//Tim Barrett
//I pledge my honor that I have abided by the Stevens Honor System

import java.util.*;

public class ListMultipleBirths {

    //Check if there were multiple births (twins, triplets..) in a family

    public static List CheckListMultipleBirths(String[] family, String[][] individuals){
        //Iterate through children, comparing nth bday to rest to check if there are multiple births
        String[] siblings = family[7].split(" ");

        int siblingsCount = siblings.length;

        List<String> multipleBirths = new ArrayList<>();
        List<String> birthDates = new ArrayList<>();

        for (int i = 0; i < siblingsCount; i++){
            
            
            //Refer to sibling[i]'s birth date

            //Find the matching ID first
            String siblingID = siblings[i];
            String siblingBirthdayI;
            for (int j = 0; j < individuals.length; j++){
                if (siblingID == individuals[j][0]){
                    siblingBirthdayI = individuals[j][2];
                    if (!birthDates.contains(siblingBirthdayI)){
                        //If new birthdate, add to list
                        birthDates.add(individuals[j][2]);
                    } else {
                        //If same birthdate already found, add id to multBirth arraylist
                        multipleBirths.add(individuals[j][1]);
                    }
                }
            }
        }

        return multipleBirths;
        
    }

}