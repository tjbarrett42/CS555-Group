import java.util.ArrayList;
import java.util.List;

public class ListDeceased extends GEDParser{
    public static boolean CheckListDeceased(String[][] individuals){
        boolean tag = true;
        for(String[] ind:individuals){
            if(!ind[6].equalsIgnoreCase("NA")){
                System.out.println(ind[0]);
            }
        }
        return tag;
    }
}
