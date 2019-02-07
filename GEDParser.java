import java.io.*; 
import java.util.Arrays;

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

    public static void main(String[] args)throws Exception { 
        //https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
        File file = new File("proj02test.ged"); 
        BufferedReader br = new BufferedReader(new FileReader(file)); 
        String str; 

        while ((str = br.readLine()) != null) {
            

            //Print input
            System.out.println("--> " + str);
            //Create changed input


            //Split input str into array
            String[] splitStr = str.split(" ");

            int currLevel = splitStr[0];

            //Check which level and use appropriate method
            //Switch to cases later to prettify? 
            if(currLevel == "0") Level0(currLevel);
            else if(currLevel == "1") Level0(currLevel);
            else if(currLevel == "2") Level0(currLevel);
            
        }
    }
    public static void Level0(String str){
        String[] words = str.split(" ");

        boolean isZeroTag = Arrays.stream(zero_tags).anyMatch(words[1]::equals);
        isZeroTag = Arrays.stream(zero_special_cases).anyMatch(words[1]::equals);

        if(isZeroTag){
            System.out.println("Level 0 Tag!");
        }   
    }

    public static void Level1(String str){
        String[] words = str.split(" ");

        boolean isOneTag = Arrays.stream(one_tags).anyMatch(words[1]::equals);

        if(isOneTag){
            System.out.println("Level 1 Tag!");
        }   
    }

    public static void Level2(String str){
        String[] words = str.split(" ");

        boolean isTwoTag = Arrays.stream(two_tags).anyMatch(words[1]::equals);

        if(isTwoTag){
            System.out.println("Level 2 Tag!");
        }   
    }

    public static void InvalidLevel(String str){
        // What to do here?
        System.out.println("Invalid level!");
    }
}