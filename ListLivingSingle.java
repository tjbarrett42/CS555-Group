//Somya Gambhir

public class ListLivingSingle {

    public static boolean ListLivingSingle(String[][] individuals){
        boolean tag = true;

        System.out.println("Printing Living: ");
        for(String[] ind:individuals){
            if(Integer.parseInt(ind[4])>30 && ind[8].equals("NA")){
                System.out.println(ind[0]);
                tag = true;
            }
        }

        return tag;
    }

}
