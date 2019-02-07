import java.io.*; 
import java.util.Arrays;

public class GEDParser
{ 
    public static void main(String[] args)throws Exception { 
        //https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
        File file = new File("proj02test.ged"); 
        BufferedReader br = new BufferedReader(new FileReader(file)); 
        String str; 

        while ((str = br.readLine()) != null) {
            //Print input
            System.out.println("--> " + str);
            //Create changed input
            String newStr = "";

            int length = 0;

            int level;
            String id = "";
            String tag;
            String valid = "Y";
            String[] arguments = {"","","","","","","","","","","","","","","",""};

            String[] newStrArr = str.split(" ");
            level = Integer.parseInt(newStrArr[0]);
            tag = newStrArr[1];

            if (newStrArr.length < 2 ){
                valid = "N";
            }

            if (newStrArr[1].length() > 4){
                valid = "N";
            }

            boolean idBefore = false;

            if (newStrArr.length > 3 && (newStrArr[2] == "INDI" || newStrArr[2] == "FAM")){
                id = newStrArr[1];
                tag = newStrArr[2];
                valid = "Y";   
                idBefore = true; 

                if (newStrArr.length > 3){
                    for (int i = 3; i < newStrArr.length; i++){
                        arguments[i] = newStrArr[i];
                    }
                }
            }

            if (newStrArr.length >= 3){
                id = newStrArr[2];
            }


            ///...
            if (idBefore){
                System.out.println("<-- " + level + "|" + tag + "|" + valid + "|" + id + "|" );
            }else{
                System.out.println("<-- " + level + "|" + tag + "|" + valid + "|" );
            }
            

        }
    } 
} 