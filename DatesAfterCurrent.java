//Somya Gambhir
public class DatesAfterCurrent {

    public static boolean DatesAfterCurrent(String[][] individuals, String[][] families){
        String birth,death,marr,div;

        for(String[] ind:individuals){
            birth = ind[3];
            death = ind[6];

            if (Integer.parseInt(birth.split("-")[0]) > 2019) {
                System.out.println("ERROR: INDIVIDUAL: US01: " + ind[0] + " cannot be born after the current date!");
            } else if (Integer.parseInt(birth.split("-")[0]) == 2019) {
                // Same year, have to check month
                if (Integer.parseInt(birth.split("-")[1]) > 04) {
                    System.out.println("ERROR: INDIVIDUAL: US01: " + ind[0] + " cannot be born after the current date!");
                } else if (Integer.parseInt(birth.split("-")[1]) > 04) {
                    if (Integer.parseInt(birth.split("-")[2]) > 20) {
                        System.out.println("ERROR: INDIVIDUAL: US01: " + ind[0] + " cannot be born after the current date!");
                    } else { }
                } else { }
            } else { }

            if(!death.equals("NA")) {
                if (Integer.parseInt(death.split("-")[0]) > 2019) {
                    System.out.println("ERROR: INDIVIDUAL: US01: " + ind[0] + " cannot have died after the current date!");
                } else if (Integer.parseInt(death.split("-")[0]) == 2019) {
                    // Same year, have to check month
                    if (Integer.parseInt(death.split("-")[1]) > 04) {
                        System.out.println("ERROR: INDIVIDUAL: US01: " + ind[0] + " cannot have died after the current date!");
                    } else if (Integer.parseInt(death.split("-")[1]) == 04) {
                        if (Integer.parseInt(death.split("-")[2]) > 20) {
                            System.out.println("ERROR: INDIVIDUAL: US01: " + ind[0] + " cannot have died after the current date!");
                        } else { }
                    } else { }
                } else { }
            }
        }

        for (String[] fam:families) {
            marr = fam[1];
            div = fam[2];

            if (Integer.parseInt(marr.split("-")[0]) > 2019) {
                System.out.println("ERROR: FAMILY: US01: " + fam[0] + " cannot be married after the current date!");
            } else if (Integer.parseInt(marr.split("-")[0]) == 2019) {
                // Same year, have to check month
                if (Integer.parseInt(marr.split("-")[1]) > 04) {
                    System.out.println("ERROR: FAMILY: US01: " + fam[0] + " cannot be married after the current date!");
                } else if (Integer.parseInt(marr.split("-")[1]) == 04) {
                    if (Integer.parseInt(marr.split("-")[2]) > 20) {
                        System.out.println("ERROR: FAMILY: US01: " + fam[0] + " cannot be married after the current date!");
                    } else { }
                } else { }
            } else { }
            if(!div.equals("NA")) {
                if (Integer.parseInt(div.split("-")[0]) > 2019) {
                    System.out.println("ERROR: INDIVIDUAL: US01: " + fam[0] + " cannot be divorced after the current date!");
                } else if (Integer.parseInt(div.split("-")[0]) == 2019) {
                    // Same year, have to check month
                    if (Integer.parseInt(div.split("-")[1]) > 04) {
                        System.out.println("ERROR: INDIVIDUAL: US01: " + fam[0] + " cannot be divorced after the current date!");
                    } else if (Integer.parseInt(div.split("-")[1]) == 04) {
                        if (Integer.parseInt(div.split("-")[2]) > 20) {
                            System.out.println("ERROR: INDIVIDUAL: US01: " + fam[0] + " cannot be divorced after the current date!");
                        } else { }
                    } else { }
                } else {
                }
            }

        }
        return true;
    }
}
