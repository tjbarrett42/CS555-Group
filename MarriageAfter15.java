//Tim Barrett
//I pledge my honor that I have abided by the Stevens Honor System

public class MarriageAfter15 extends GEDParser {

    //Check if marriage in family had an individual under 15

    public static boolean CheckMarriageAfter15(String[] family, String[][] individuals){
        //return true if not married
        if (family[1] == "NA") {
            return true;
        }
        
        String marriageDate = family[1];
        String husbandBirthDate;
        String wifeBirthDate;
        String husbandID = family[3];
        String wifeID = family[5];

        //Iterate through individuals list, find matching ind from family
        for (int i = 0; individuals[i] != null; i++){
            if (husbandID == individuals[i][0]){
                husbandBirthDate = individuals[i][3];
            }else if (wifeID == individuals[i][0]){
                wifeBirthDate = individuals[i][3];
            }
        }

        //Parsing to array to compare
        String[] sMarriageD = marriageDate.split("-");
        String[] sHusbandBD = husbandBirthDate.split("-");
        String[] sWifeBD = wifeBirthDate.split("-");
        
        //test if husband was older than 15 when married

        if ((Integer.parseInt(sMarriageD[2]) > Integer.parseInt(sHusbandBD[2]) + 15)) {
            // Older than 15 when married
            
        } else if ((Integer.parseInt(sMarriageD[2]) == Integer.parseInt(sHusbandBD[2]) + 15)){
            // Same year as 15th bday, check month
            if ((Integer.parseInt(sMarriageD[0]) > Integer.parseInt(sHusbandBD[0]))){
                // Older than 15 when married by month
                
            } else if ((Integer.parseInt(sMarriageD[0]) == Integer.parseInt(sHusbandBD[0]))){
                // Same month as 15th birtday, check day
                if ((Integer.parseInt(sMarriageD[1]) > Integer.parseInt(sHusbandBD[1]))){
                    // Older than 15 when married by month
                    
                } else {

                    return false;
                }

            } else {
                //Marriage month < birthday month
                return false;
            }
        } else {
            // Marriage year < birthday year
            return false;
        }

        //Check if wife was 15 at marriage 
        if ((Integer.parseInt(sMarriageD[2]) > Integer.parseInt(sWifeBD[2]) + 15)) {
            // Older than 15 when married
            
        } else if ((Integer.parseInt(sMarriageD[2]) == Integer.parseInt(sWifeBD[2]) + 15)){
            // Same year as 15th bday, check month
            if ((Integer.parseInt(sMarriageD[0]) > Integer.parseInt(sWifeBD[0]))){
                // Older than 15 when married by month
                
            } else if ((Integer.parseInt(sMarriageD[0]) == Integer.parseInt(sWifeBD[0]))){
                // Same month as 15th birtday, check day
                if ((Integer.parseInt(sMarriageD[1]) > Integer.parseInt(sWifeBD[1]))){
                    // Older than 15 when married by month
                    
                } else {

                    return false;
                }

            } else {
                //Marriage month < birthday month
                return false;
            }
        } else {
            // Marriage year < birthday year
            return false;
        }

        //If everything passes, they were older than 15 during marriage.

        return true;

    }
}