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
        "DATA"
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

    public static void main(String[] args)throws Exception { 
        //https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
        File file = new File("proj02test.ged"); 
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
            //Switch to cases later to prettify? 
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
    }

    public static void Level0(String str){
        String[] words = str.split(" ");

        // Check if this is one of the cases where it is just two words long
        if (words.length < 3) {
            boolean isZeroTag = Arrays.stream(zero_tags).anyMatch(words[1]::equals);
            if (isZeroTag) {
                // HEAD or TRLR -> We can ignore this for now
                return;
            }   
        } else {
            boolean isZeroTag = Arrays.stream(zero_tags).anyMatch(words[1]::equals);
            boolean isSpecialZeroTag = Arrays.stream(zero_special_cases).anyMatch(words[2]::equals);
            if (isZeroTag) {
                // NOTE -> We can ignore this for now
                return; 
            } else if(isSpecialZeroTag) {
                // Create a new individual or family
                System.out.println("Triggering create with tag " + words[2]);
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
                System.out.println("Level 1 tag is " + tag);
                System.out.println("Calling name for individual " + currentInd + " with name " + words[2]);
                NameIndividual(words[2], currentInd);
            }
        }   
    }

    public static void Level2(String str){
        String[] words = str.split(" ");

        String tag = words[1];
        boolean isTwoTag = Arrays.stream(two_tags).anyMatch(tag::equals);

        if(isTwoTag){
            System.out.println("Level 2 Tag: " + tag);
        }   
    }

    public static void InvalidLevel(String str){
        // Skip this line
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

    public static void NameIndividual(String name, String id) {
        for (int i = 0; i < 5000; i++) {
            if (individuals[i][0] == id) {
                individuals[i][1] = name;
                System.out.println("Individual " + id + " is named " + name);
                return;
            }
        }
    }

    public static void PrintTable(){
        //Convert id and fam arrays into readable strings

        String const indPrintTemplate = "     |                      |      |          |   |     |          |             |             |\n";
        String const famPrintTemplate = "     |          |          |          |                        |       |                    |                     |\n";

        System.out.println("Individuals")
        System.out.println("ID   |Name                  |Gender|Birthday  |Age|Alive|Death     |Children     |Spouse       |\n")
        System.out.println("-----+----------------------+------+----------+---+-----+----------+-------------+-------------+\n")
        // Example info:    I01  |Timothy /Barrett/     |M     |02-26-1998|20 |True |NA        |NA           |NA           |
        // Index:           0    5                      29     36         46  50    56         67            81            94    

        //Print ID arrays here
        //Keep iterating until an element is found without an ID, ie the end of the array
        for (int i = 0; individuals[i][0] != ""; i++){
            String indPrint = indPrintTemplate;
            //use replace() to add data in specific index. Also prevents overflow with end index
            indPrint.replace(0,5,individuals[i][0]); //ID
            indPrint.replace(6,29,individuals[i][1]); //Name
            indPrint.replace(30,36,individuals[i][2]); //Gender
            indPrint.replace(37,46,individuals[i][3]); //Birthday
            indPrint.replace(47,50,individuals[i][4]); //Age
            indPrint.replace(51,56,individuals[i][5]); //Alive
            indPrint.replace(57,67,individuals[i][6]); //Death
            indPrint.replace(68,81,individuals[i][7]); //Children
            indPrint.replace(82,94,individuals[i][8]); //False
            
            //Once every section is populated, print
            System.out.println(indPrint + "\n");
            //Move to next individual
        }

        System.out.println("Individuals")
        System.out.println("ID   |Married   |Divorced  |Husband ID|Husband Name            |Wife ID|Wife Name           |Children             |\n}")
        System.out.println("-----+----------+----------+----------+------------------------+-------+--------------------+---------------------+\n")
        // Example info:    F1   |00-00-0000|NA        |I02       |Joe /Schmno             |True   |Jane Schmo          |NA                   |
        // Spacing          0    5          16         27         38                       63      71                   92                    113

        //Print F arrays here:
        for (int i = 0; families[i][0] != ""; i++){
            String famPrint = famPrintTemplate;
            //use replace() to add data in specific index. Also prevents overflow with end index
            famPrint.replace(0,5,families[i][0]); //ID
            famPrint.replace(6,16,families[i][1]); //Married
            famPrint.replace(17,27,families[i][2]); //Divorced
            famPrint.replace(28,38,families[i][3]); //Husband ID
            famPrint.replace(39,63,families[i][4]); //Husband Name
            famPrint.replace(64,71,families[i][5]); //Wife ID
            famPrint.replace(71,92,families[i][6]); //Wife Name
            famPrint.replace(93,113,families[i][7]); //Children
            
            //Once every section is populated, print
            
            System.out.println(famPrint + "\n");
            //Move to next individual
        }
    }
}