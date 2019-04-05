//Somya Gambhir
//I pledge my honor that I have abided by the Stevens Honor System
import com.sun.tools.javac.comp.Check;

public class BirthBeforeDeath extends GEDParser{

    public static boolean CheckUS03(String[] individuals){
        if(individuals[6] == "NA"){
            return true;
        }
        else{
            String[] birth = individuals[3].split("-");
            String[] death = individuals[6].split("-");
            if(Integer.parseInt(birth[0]) > Integer.parseInt(death[0])){
                return false;
            }
            else if(Integer.parseInt(birth[1]) > Integer.parseInt(death[1])){
                return false;
            }
            else if(Integer.parseInt(birth[2]) >= Integer.parseInt(death[2])) {
                return false;
            }
            else{
                return true;
            }
        }
    }
}
