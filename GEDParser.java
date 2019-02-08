import java.io.*; 
import java.util.Arrays;
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
}