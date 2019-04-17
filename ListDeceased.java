
//Somya Gambhir
public class ListDeceased extends GEDParser{
    public static boolean CheckListDeceased(String[][] individuals){
        boolean tag = false;
        System.out.println("Printing Deceased: ");
        for(String[] ind:individuals){
            if(!ind[6].equalsIgnoreCase("NA")){
                System.out.println(ind[0]);
                tag = true;
            }
        }

        return tag;
    }
}
