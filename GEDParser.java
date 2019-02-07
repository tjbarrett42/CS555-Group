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

            //------error: incompatible types: string[] cannot be conv to string
            if(splitStr[0] == "0"){
                Level0(splitStr);
            }

        }
    }
    public static void Level0(String str){
        String[] words = str.split(" ");

        boolean isZeroTag = Arrays.stream(zero_tags).anyMatch(words[1]::equals);

        if(isZeroTag){
            System.out.println("Correct");
        }

        
    }
}