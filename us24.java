public class us24 {

    public static boolean us24(String[][] individuals, String[][] families){
        boolean tag = true;

        for(String[] fam: families){
            for(String[] fam2: families){
                if(fam[4] == fam2[4] && fam[6]==fam2[6]){
                    if(fam[1] == fam2[1]){
                        tag = false;
                    }
                }
            }
        }
        return tag;
    }

}
