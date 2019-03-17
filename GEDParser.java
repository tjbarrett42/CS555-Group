// Current issues: \n characters are messing up equality statements
/*  TODOs:
    Add individuals and families to array in Create()
    Properly get last three words as date in Level2()
    Add functionality in Level1() to FAMC, FAMS, HUSB, WIFE, CHIL
    Test functions
     */

import java.io.*; 
import java.util.Arrays;
import java.lang.*;
// import java.util.ArrayList;

public class GEDParser
{   
    static String[] zero_tags = {
        "HEAD",
        "TRLR",
        "NOTE"
    };

    static String[] zero_special_cases = {
        "INDI",
        "FAM"
    };

    static String[] one_tags = {
        "NAME",
        "SEX",
        "BIRT",
        "DEAT",
        "FAMC",
        "FAMS",
        "MARR",
        "HUSB",
        "WIFE",
        "CHIL",
        "DIV",
    };

    static String[] two_tags = {
        "DATE"
    };

    /* Array of arrays
    Each array represents an individual:
    [ID, Name, Gender, Birthday, Age, Alive, Death Date, <Families as child>, <Family as Spouse>] */
    static String[][] individuals = new String[5000][9];

    /* Array of arrays
    Each array represents a family:
    [ID, Marriage Date, Divorce Date, Husband ID, Husband Name, Wife ID, Wife Name, <Ids of children> */
    static String[][] families = new String[1000][8];

    // Identifiers
    static String currentInd = "";
    static String currentFam = "";
    static String waitingEvent = ""; // This string holds either birth, death, marriage, or divorce. We use it to know what to do when we come across a date tag

    public static void main(String[] args)throws Exception { 
        // Check for proper command line args
        if (args.length != 1) {
            System.out.println("USAGE: java GEDParser yourFileHere.ged");
            System.exit(1);
        } 

        // Check file extension --> https://stackoverflow.com/questions/3571223/how-do-i-get-the-file-extension-of-a-file-in-java
        String extension = "";
        int lastIndex = args[0].lastIndexOf('.');
        if (lastIndex > 0) {
            extension = args[0].substring(lastIndex + 1);
        } else {
            System.out.println("ERROR: Must specify a GEDCOM file");
            System.exit(1);
        }

        if (!extension.equals("ged")) {
            System.out.println("ERROR: Must specify a GEDCOM file");
            System.exit(1);
        }

        // https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
        File file = new File(args[0]); 
        if (!file.exists()) {
            System.out.println("ERROR: GEDCOM file does not exist!");
            System.exit(1);
        } 
        BufferedReader br = new BufferedReader(new FileReader(file)); 
        String str; 

        while ((str = br.readLine()) != null) {
            //Split input str into array
            String[] splitStr = str.split(" ");

            // Trim whitespace and newlines from strings
            for(int i = 0; i < splitStr.length; i++) {
                splitStr[i] = splitStr[i].trim();
            }

            int currLevel = Integer.valueOf(splitStr[0]);

            //Check which level and use appropriate method
            if (currLevel == 0) {
                Level0(str);
            } else if(currLevel == 1) {
                Level1(str);
            } else if(currLevel == 2) {
                Level2(str);
            } else {
                InvalidLevel(str);
            }
        }
        br.close();
        
        checkIndividuals();
        checkFamilies();
        
        PrintTable();
    }

    public static void Level0(String str){
        String[] words = str.split(" ");

        // Check if this is one of the cases where it is just two words long
        if (words.length < 3) {
            boolean isZeroTag = Arrays.stream(zero_tags).anyMatch(words[1]::equals);
            if (isZeroTag) {
                // HEAD or TRLR -> We can ignore this as of Feb. 10
                return;
            }   
        } else {
            boolean isZeroTag = Arrays.stream(zero_tags).anyMatch(words[1]::equals);
            boolean isSpecialZeroTag = Arrays.stream(zero_special_cases).anyMatch(words[2]::equals);
            if (isZeroTag) {
                // NOTE -> We can ignore this as of Feb. 10
                return; 
            } else if(isSpecialZeroTag) {
                // INDI or FAM -> Create a new individual or family
                // System.out.println("Triggering create with tag " + words[2]);
                Create(words[2], words[1]);
            }
        }
    }

    public static void Level1(String str){
        String[] words = str.split(" ");

        String tag = words[1];
        boolean isOneTag = Arrays.stream(one_tags).anyMatch(tag::equals);
        
        if (isOneTag) {
            if (tag == "NAME") {
                // System.out.println("Calling name for individual " + currentInd + " with name " + words[2]);
                // Place the individual's name in the records
                NameIndividual(words[2]);
            } else if (tag == "SEX") {
                // Place the individual's sex in the records
                AssignSexToIndividual(words[2]);
            } else if (tag == "BIRT") {
                // Set the next event for a date to birth
                waitingEvent = "birth";
                // WaitForBirthdate(currentInd);
            } else if (tag == "DEAT") {
                // Set the next event for a date to death
                waitingEvent = "death";
            } else if (tag == "FAMC") {

            } else if (tag == "FAMS") {

            } else if (tag == "MARR") {
                // Set the next event for a date to marriage
                waitingEvent = "marriage";
            } else if (tag == "HUSB") {
                
            } else if (tag == "WIFE") {
                
            } else if (tag == "CHIL") {
                
            } else if (tag == "DIV") {
                // Set the next event for a date to divorce          
                waitingEvent = "divorce";  
            } else {
                return;
            }
        }   
    }

    public static void Level2(String str){
        String[] words = str.split(" ");

        String tag = words[1];
        String date = "TEMPORARY PLACEHOLDER"; // TODO: Make date be the rest of words as one string
        boolean isTwoTag = Arrays.stream(two_tags).anyMatch(tag::equals);

        if(isTwoTag){
            // The tag can only be date 
            AddDate(date);
        }   
    }

    public static void InvalidLevel(String str){
        // Skip the current line, it's invalid for sure
        return;
    }

    // Create a new individual or family
    public static void Create(String tag, String id) {
        // System.out.println(tag);
        if (tag == "INDI") {
            // Create new indiviual
            String[] indi = {id, "NA", "NA", "NA", "NA", "NA", "NA", "NA", "NA"};
            System.out.println("Individual " + id + " created");
            // TODO: Add to individuals
            currentInd = id;
        } else if (tag == "FAM") {
            // Create new family
            String[] fam = {id, "NA", "NA", "NA", "NA", "NA", "NA", "NA", "NA"};
            System.out.println("Family " + id + " created");
            // TODO: Add to families
            currentFam = id;
        }
    }

    // BELOW: Functions to fill in information about individuals given in level 1 tags

    // Fill in the name of an individual
    public static void NameIndividual(String name) {
        // Iterate through individuals looking for the current one
        for (int i = 0; i < 5000; i++) {
            if (individuals[i][0] == currentInd) {
                individuals[i][1] = name;
                // System.out.println("Individual " + id + " is named " + name);
                return;
            }
        }
    }

    public static void AssignSexToIndividual(String sex) {
        // Iterate through individuals looking for the current one
        for (int i = 0; i < 5000; i++) {
            if (individuals[i][0] == currentInd) {
                individuals[i][2] = sex;
                // System.out.println("Individual " + id + " is of sex " + sex);
                return;
            }
        }
    }

    // Add a date to the proper field
    public static void AddDate(String date) {
        // Iterate through individuals and families looking for the current one
        int placeOfInd = 0;
        for (int i = 0; i < 5000; i++) {
            if (individuals[i][0] == currentInd) {
                placeOfInd = i;
            }
        }
        int placeOfFam = 0;
        for (int j = 0; j < 1000; j++) {
            if (families[j][0] == currentFam) {
                placeOfFam = j;
            }
        }
        if (waitingEvent == "birth") {
            individuals[placeOfInd][3] = date;
        } else if (waitingEvent == "death") {
            individuals[placeOfInd][6] = date;
        } else if (waitingEvent == "marriage") {
            families[placeOfFam][1] = date;
        } else if (waitingEvent == "divorce") {
            families[placeOfFam][2] = date;
        } else {
            return;
        }
    }

    // Go through all individuals and apply all individual-level user stories to them
    public static void checkIndividuals() {

    }

    
    // Go through all families and apply all family-level user stories to them
    public static void checkFamilies() {

    }

    public static void PrintTable(){
        //Simple implementation
        System.out.println(" Individuals\n ID Name Gender Birthday Age Alive Death Children Spouse");
        //Iterate through individuals
        for (int i = 0; i < 10; i++){
            //Iterate through each individual 

            String indPrint = "";

            for (int j = 0; j < 9; j++){
                indPrint = indPrint + " " + individuals[i][j];
            }

            System.out.println(indPrint);
            
        }

        System.out.println(" Families\n ID Married Divorced HusbandID HusbandName WifeID WifeName Children");
        //Iterate through individuals
        for (int i = 0; i < 10; i++){
            //Iterate through each individual 
            String famPrint = "";

            for (int j = 0; j < 8; j++){
                famPrint = famPrint + " " + families[i][j];
            }

            System.out.println(famPrint);
            
        }
    }
}