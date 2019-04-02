import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RejectIllDates {

    public static boolean RejectIllDates(String[][] indivduals){
        boolean tag = true;
        String bday;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        for(String[] ind: indivduals){
            bday = ind[3];

            try{
                format.parse(bday);
            }catch(ParseException e){
                tag = false;
            }
        }
        return tag;
    }

}
