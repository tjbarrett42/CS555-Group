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
    static String[] individuals = new String[5000];

    /* Array of arrays
    Each array represents a family:
    [ID, Marriage Date, Divorce Date, Husband ID, Husband Name, Wife ID, Wife Name, <Ids of children> */
    static String[] families = new String[1000];

    public static void main(String[] args)throws Exception { 
        //https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
        File file = new File("proj02test.ged"); 
        BufferedReader br = new BufferedReader(new FileReader(file)); 
        String str; 

        while ((str = br.readLine()) != null) {
            
            //Print input
            System.out.println("--> " + str);

            //Split input str into array
            String[] splitStr = str.split(" ");

            int currLevel = Integer.valueOf(splitStr[0]);

            //Check which level and use appropriate method
            //Switch to cases later to prettify? 
            System.out.println("Current level: " + currLevel);
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

        // Check if this is ine of the cases where it is just two words long
        if (words.length < 3) {
            boolean isZeroTag = Arrays.stream(zero_tags).anyMatch(tag::equals);
            if (isZeroTag) {
                // Validated it's a true tag
                System.out.println("Level 0 Tag: " + words[1]);
            }   
        } else {
            boolean isZeroTag = Arrays.stream(zero_tags).anyMatch(words[1]::equals);
            boolean isSpecialZeroTag = Arrays.stream(zero_special_cases).anyMatch(words[2]::equals);
            if (isZeroTag) {
                // Validated it's a true tag
                System.out.println("Level 0 Tag: " + words[1]);
            } else if(isSpecialZeroTag) {
                // Validated it's a true tag
                System.out.println("Level 0 Tag: " + words[2]); 
                // Create a new individual or family
                Create(words[2], words[1]);
            }
        }
    }

    public static void Level1(String str){
        String[] words = str.split(" ");

        String tag = words[1];
        boolean isOneTag = Arrays.stream(one_tags).anyMatch(tag::equals);
        
        if (isOneTag) {
            System.out.println("Level 1 Tag: " + tag);
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
        // What to do here?
        System.out.println("Invalid level!");
    }

    // Create a new individual or family
    public static void Create(String tag, String id) {
        if (tag == "INDI") {
            // Create new indiviual
            String[] indi = {id, "NA", "NA", "NA", "NA", "NA", "NA", "NA", "NA"};

        } else if (tag == "FAM") {
            // Create new family
            String[] fam = {id, "NA", "NA", "NA", "NA", "NA", "NA", "NA", "NA"};
            
        } else {
            System.out.println("Warning! This function should not have been called!");
        }
    }
}