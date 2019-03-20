//Tim Barrett
//I pledge my honor that I have abided by the Stevens Honor System

import java.util.*;

public class UniqueFirstNamesInFamilies {

    //Return list of unique first names in families.

    public static List CheckUniqueFirstNamesInFamilies(String[] family, String[][] individuals){
        //
        List<String> uniqueFirstNames = new ArrayList<String>();
        //Husband is first, automatically has unique name in family.
        uniqueFirstNames.add(family[4]);
        //Check wife's name
        if (family[6] != family[4]){
            //if wife name is different, add to list
            uniqueFirstNames.add(family[6]);
        }

        //Build string of all names, split, then add unique names
        
        String[] siblings = family[7].split(" ");

        for (int i = 0; i < siblings.length; i++){

            String siblingID = siblings[i];
            
            //Unfortunately there is no builtin getBirthdate() yet so it has to be done manually.
            for (int j = 0; j <= individuals.length; j++){
                if (siblingID == individuals[j][0]){
                    String siblingName = individuals[j][1];
                    String[] siblingNameArr = individuals[j][1].split(" ");
                    String siblingFirstName = siblingNameArr[0];
                    if (!uniqueFirstNames.contains(siblingFirstName)){
                        uniqueFirstNames.add(siblingFirstName);
                    }
                }
            }
        }

        return uniqueFirstNames;
        
    }
}