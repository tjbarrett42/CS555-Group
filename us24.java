//Somya Gambhir

public class us24 {

    public static boolean us24(String[][] families){
        boolean tag = true;

        if(families.length==1){
            return true;
        }

        for(String[] fam: families){
            for(String[] fam2: families){
                if(!fam[0].equals(fam2[0])) {
                    if (fam[4] == fam2[4] && fam[6] == fam2[6]) {
                        if (fam[1].equals(fam2[1])) {
                            System.out.println("ERROR: FAMILY: US24: " + fam[0]  + " are not unique by spouses");
                            tag = false;
                        }
                    }
                }
            }
        }
        return tag;
    }

}
