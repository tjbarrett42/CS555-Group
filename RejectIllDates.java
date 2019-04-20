//Somya Gambhir

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RejectIllDates {

    public static boolean RejectIllDates(String[][] indivduals,String[][] families){
        boolean tag = true;
        String bday,death,marr,div;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        for(String[] ind: indivduals){
            bday = ind[3];
            death = ind[6];

            try{
                format.parse(bday);
                if(!death.equals("NA")){
                    format.parse(death);
                }
            }catch(ParseException e){
                System.out.println("ERROR: INDIVIDUAL: US42: " + ind[0] + " has incorrect date format!");
                tag = false;
            }
        }

        for(String[] fam: families){
            marr = fam[1];
            div = fam[2];
            try{
                format.parse(marr);
                if(!div.equals("NA")){
                    format.parse(div);
                }
            }catch(ParseException e){
                System.out.println("ERROR: FAMILY: US42: " + fam[0] + " has incorrect date format!");
                tag = false;
            }
        }

        return tag;
    }

}
