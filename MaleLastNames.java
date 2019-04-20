//Somya Gambhir

public class MaleLastNames extends GEDParser {
    public static boolean CheckMaleLastNames(String[][] individuals, String[][] families){
        boolean tag = true;
        for(String[] family:families){
            if(family[4].equalsIgnoreCase("NA")){
                return false;
            }
            if (family[1] != "NA"){
                String ln = family[4].split("\\W+")[1];
                for(String[] ind: individuals){
                    String curr = ind[0];
                    String indname = ind[1].split("\\W+")[1];
                    String[] children = family[7].split("\\W+");
                    for(String i: children){
                        if(i.equals(curr)){
                            if(ind[2].equals("M")){
                                if(!indname.equalsIgnoreCase(ln)){
                                    System.out.println("ERROR: FAMILY: US16: "+ family[0] + " has individual: " + ind[0] + " has a different last name than other males in the same family");
                                    tag = false;
                                }
                            }
                        }
                    }
                }
            }
        }

        return tag;
    }
//
//    public static void main(String[] args) {
//        System.out.println(CheckMaleLastNames(families,individuals));
//    }
}
