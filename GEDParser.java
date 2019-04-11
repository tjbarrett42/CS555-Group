// Dylan DiGeronimo, Tim Barrett, Somya Gambhir

import com.jakewharton.fliptables.FlipTable;

import java.io.*;
import java.util.Arrays;
import java.lang.*;
// import java.util.ArrayList;

public class GEDParser {   

    // import other user stories as objects 
    private static MarriageBeforeDeath MarriageBeforeDeath; // DD
    private static MarriageBeforeDivorce MarriageBeforeDivorce; // DD
    private static LessThan150 LessThan150; // DD
    private static DivorceBeforeDeath DivorceBeforeDeath; // DD
    private static UniqueIDs UniqueIDs; // DD
    private static UniqueNameAndBirthDate UniqueNameAndBirthDate; // DD
    private static CorrectGender CorrectGender; // DD
    private static us24 us24;
    private static RejectIllDates RejectIllDates;
    private static BirthBeforeDeath us03;

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
    [ID, Marriage Date, Divorce Date, Husband ID, Husband Name, Wife ID, Wife Name, <Ids of children>] */
    static String[][] families = new String[1000][8];

    // Identifiers
    static String currentInd = "";
    static String currentFam = "";
    static String waitingEvent = ""; // This string holds either birth, death, marriage, or divorce. We use it to know what to do when we come across a date tag
    static int indcounter = 0;
    static int famcounter = 0;
    static String DATE = "2019-04-05";

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
            //System.out.println(str);
            int currLevel = Integer.valueOf(splitStr[0]);

            //Check which level and use appropriate method
            if (currLevel == 0) {
                level0(str);
            } else if(currLevel == 1) {
                level1(str);
            } else if(currLevel == 2) {
                level2(str);
            } else {
                invalidLevel(str);
            }
        }
        br.close();

        for(String[] fam:families){
            fam[7] += "}";
        }

        for(String[] ind:individuals){
            if(ind[8]!="NA")
                ind[8] += "}";
            if(ind[7]!="NA")
                ind[7] += "}";
        }

        calcAge();

        printTable();

        checkIndividuals();
        checkFamilies();
    }

    public static void level0(String str){
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
                // INDI or FAM -> create a new individual or family
                create(words[2], words[1]);
            }
        }
    }

    public static void level1(String str){
        String[] words = str.split(" ",3);

        String tag = words[1];
        boolean isOneTag = Arrays.stream(one_tags).anyMatch(tag::equals);
        
        if (isOneTag) {
            if (tag.equals("NAME")) {
                // Place the individual's name in the records
                nameIndividual(words[2]);
            } else if (tag.equals("SEX")) {
                // Place the individual's sex in the records
                assignSexToIndividual(words[2]);
            } else if (tag.equals("BIRT")) {
                // Set the next event for a date to birth
                waitingEvent = "birth";
                // WaitForBirthdate(currentInd);
            } else if (tag.equals("DEAT")) {
                // Set the next event for a date to death
                killIndividual();
                waitingEvent = "death";
            } else if (tag.equals("FAMC")) {
                // Update the current individual's "child in family" field
                updateChild(words[2]);
            } else if (tag.equals("FAMS")) {
                // Update the current individual's "spouse in family" field
                updateSpouse(words[2]);
            } else if (tag.equals("MARR")) {
                // Set the next event for a date to marriage
                waitingEvent = "marriage";
            } else if (tag.equals("HUSB")) {
                // Add the specified individual to the current family as the husband
                addHusband(words[2]);
            } else if (tag.equals("WIFE")) {
                // Add the specified individual to the current family as the wife
                addWife(words[2]);
            } else if (tag.equals("CHIL")) {
                // Add the specified individual to the current family as a child
                addChild(words[2]);
            } else if (tag.equals("DIV")) {
                // Set the next event for a date to divorce          
                waitingEvent = "divorce";  
            } else {
                return;
            }
        }   
    }

    public static void level2(String str){
        String[] words = str.split(" ");
        String tag = words[1];
        boolean isTwoTag = Arrays.stream(two_tags).anyMatch(tag::equals);

        if(isTwoTag){
            // The tag can only be date
            // Convert month to number 
            String monthWord = words[3];
            String day;
            String month = "0";
            if (monthWord.equals("JAN")) {
                month = "01";
            } else if (monthWord.equals("FEB")) {
                month = "02";
            } else if (monthWord.equals("MAR")) {
                month = "03";
            } else if (monthWord.equals("APR")) {
                month = "04";
            } else if (monthWord.equals("MAY")) {
                month = "05";
            } else if (monthWord.equals("JUN")) {
                month = "06";
            } else if (monthWord.equals("JUL")) {
                month = "07";
            } else if (monthWord.equals("AUG")) {
                month = "08";
            } else if (monthWord.equals("SEP")) {
                month = "09";
            } else if (monthWord.equals("OCT")) {
                month = "10";
            } else if (monthWord.equals("NOV")) {
                month = "11";
            } else if (monthWord.equals("DEC")) {
                month = "12";
            }
            //Make sure day formmating is correct for numbers below 10
            if(words[2].length() ==1){
                day = "0"+words[2];}
            else{
                day = words[2];}
            String year = words[4];
            String date = year + "-" + month + "-" + day;
            addDate(date);

        }   
    }

    public static void invalidLevel(String str){
        // Skip the current line, it's invalid for sure
        return;
    }
    //TODO implement Age
    public static void calcAge(){
        for (int i = 0; i < indcounter; i++) {

            String by = individuals[i][3].split("-")[0];
            String bm = individuals[i][3].split("-")[1];
            String bd = individuals[i][3].split("-")[2];
            String death = DATE;
            String dy,dm,dd;
            int age;
            if(individuals[i][6] == "NA"){
                dy = death.split("-")[0];
                dm = death.split("-")[1];
                dd = death.split("-")[2];
            }
            else {
                dy = individuals[i][6].split("-")[0];
                dm = individuals[i][6].split("-")[1];
                dd = individuals[i][6].split("-")[2];
            }
            age = Integer.parseInt(dy) - Integer.parseInt(by);
            if(Integer.parseInt(by) < Integer.parseInt(dy)){
                if(Integer.parseInt(bm) < Integer.parseInt(dm)){
                    age++;
                }else if(Integer.parseInt(bm) == Integer.parseInt(dm)){
                    if(Integer.parseInt(bd) < Integer.parseInt(dd)){
                        age++;
                    }
                }
            }
            else{
                age = 0;
            }

            individuals[i][4] = Integer.toString(age);
        }
    }

    // create a new individual or family
    public static void create(String tag, String id) {
        // System.out.println(tag);
        if (tag.equals("INDI")) {
            // create new indiviual
            String[] indi = {id, "NA", "NA", "NA", "NA", "Alive", "NA", "NA", "NA"};
            // System.out.println("Individual " + id + " created");
            addIndividual(indi);
            currentInd = id;
        } else if (tag.equals("FAM")) {
            // create new family
            String[] fam = {id, "NA", "NA", "NA", "NA", "NA", "NA", "NA"};
            // System.out.println("Family " + id + " created");
            addFamily(fam);
            currentFam = id;
        }
    }

    // BELOW: Functions to fill in information about individuals given in level 1 tags

    // Fill in the name of an individual
    public static void nameIndividual(String name) {
        // Iterate through individuals looking for the current one
        for (int i = 0; i < 5000; i++) {
            if (individuals[i][0] == currentInd) {
                individuals[i][1] = name;
                return;
            }
        }
    }

    public static void assignSexToIndividual(String sex) {
        // Iterate through individuals looking for the current one
        for (int i = 0; i < 5000; i++) {
            if (individuals[i][0] == currentInd) {
                individuals[i][2] = sex;
                return;
            }
        }
    }

    // Add a date to the proper field
    public static void addDate(String date) {
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

    // Add individual to individuals
    public static void addIndividual(String[] individual) {
        // Iterate over all individuals to find first empty individual 
        for (int i = 0; i < individuals.length; i++) {

            if (individuals[i][0] == null) {
                individuals[i] = individual;
                indcounter++;
                break;
            }
        }
    }

    // Add family to families
    public static void addFamily(String[] family) {
        // Iterate over all families to find first empty family
        for (int i = 0; i < families.length; i++) {
            if (families[i][0] == null) {
                families[i] = family;
                famcounter++;
                break;
            }
        }
    }

    // Add a child to a family
    public static void updateChild(String famID) {
        // Find current individual and update child to add the family id
        for (int i = 0; i < individuals.length; i++) {
            if (individuals[i][0].equals(currentInd)) {
                if(individuals[i][7].equals("NA"))
                    individuals[i][7] =  "{'" +famID+ "'";
                else
                    individuals[i][7] +=  "'" +famID+ "'";
                break;
            }
        }
    }

    // Update an individual's spouse in family field
    public static void updateSpouse(String famID) {
        // Find current individual
        for (int i = 0; i < individuals.length; i++) {
            if (individuals[i][0].equals(currentInd)) {
                if(individuals[i][8].equals("NA"))
                    individuals[i][8] =  "{'" +famID+ "'";
                else
                    individuals[i][8] +=  ", '" +famID+ "'";
                break;
            }
        }
    }

    // Add the id of a child to the current family
    public static void addChild(String childId) {
         // Find current family
         for (int i = 0; i < families.length; i++) {
            if (families[i][0].equals(currentFam)) {
                // Family found, see if children are empty (first child has different formatting)
                if (families[i][7].equals("NA")) {
                    families[i][7] = "{'"+currentInd+"'";
                } else {
                    families[i][7] += (", " + "'"+currentInd+"'");
                }
            }
            break;
        }
    }

    // Add the id of the husband to the current family
    public static void addHusband(String husbandID) {
        // Find current family
        for (int i = 0; i < families.length; i++) {
            if (families[i][0].equals(currentFam)) {
                // Family found, add husband's id
                families[i][3] = husbandID;
                // Now, we find the husbad's name and add that
                for (int j = 0; j < individuals.length; j++) {
                    if (individuals[j][0].equals(husbandID)) {
                        families[i][4] = individuals[j][1];
                        break;

                    }
                }
                break;
            }
        }
    }

    // Add the id of the wife to the current family
    public static void addWife(String wifeID) {
        // Find current family
        for (int i = 0; i < families.length; i++) {
            if (families[i][0].equals(currentFam)) {
                // Family found, add wife's id
                families[i][5] = wifeID;
                // Now, we find the husbad's name and add that
                for (int j = 0; j < individuals.length; j++) {
                    if (individuals[j][0].equals(wifeID)) {
                        families[i][6] = individuals[j][1];
                        break;
                    }
                } 
            }
            break;
        }
    }

    // Change an indiviual's alive status to be dead
    public static void killIndividual() {
        // Find current individual
        for (int i = 0; i < individuals.length; i++) {
            if (individuals[i][0].equals(currentInd)) {
                individuals[i][5] = "Dead";
                break;
            }
        }
    }

    // Go through all individuals and apply all individual-level user stories to them
    public static void checkIndividuals() {
        for (int i = 0; i < indcounter; i++) {
            if (!LessThan150.checkLessThan150(individuals[i])) {
                System.out.println("ERROR: Individual " + individuals[i][0] + " is more than 150 years old!");
            }
            if(!us03.CheckUS03(individuals[i])){
                System.out.println("ERROR: Individual: US03: " + individuals[i][0] + " cannot be born after death!");
            }
        }
    }

    // Go through all families and apply all family-level user stories to them
    public static void checkFamilies() {
        for (int i = 0; i < families.length; i++) {
           if (!MarriageBeforeDeath.checkMarriageBeforeDeath(families[i])) {
               System.out.println("ERROR: Family " + families[i][0] + " were married after the death of a spouse!");
            }
            if (!MarriageBeforeDivorce.checkMarriageBeforeDivorce(families[i])) {
                System.out.println("ERROR: Family " + families[i][0] + " were divorced before they were married!");
            }
            if (!DivorceBeforeDeath.checkDivorceBeforeDeath(families[i])) {
                System.out.println("ERROR: Family " + families[i][0] + " were divorced after the death of a spouse!");
            }
        }
    }

    // Apply all user stories that do not require a loop
    public static void checkNoLoop() {
        // Print statements for these functions are handled within them
        UniqueIDs.checkUniqueIDs(individuals);
        UniqueNameAndBirthDate.checkUniqueNameAndBirthDate(individuals);
        CorrectGender.checkCorrectGender(families, individuals);
    }

    public static void printTable(){
        //Use Fliptables lib to print out tables similar to prettytables module in Python
        String[] indheaders = {"ID","Name","Gender","Birthday","Age","Alive","Death","Child","Spouse"};
        String[] famheaders = {"ID","Married","Divorced","Husband ID","Husband Name","Wife ID","Wife Name","Children"};

        //Create new arrays with no null values
        String[][] prntind = new String[indcounter][9];
        String[][] prntfam = new String[famcounter][9];

        for(int i=0;i<indcounter;i++){
            prntind[i] = individuals[i];
        }
        for(int i=0;i<famcounter;i++){
            prntfam[i] = families[i];
        }

        System.out.println("Individuals:");
        System.out.println(FlipTable.of(indheaders, prntind));
        System.out.println("Families:");
        System.out.println(FlipTable.of(famheaders,prntfam));

    }
}