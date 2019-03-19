public class MaleLastNames extends GEDParser {

//    static String[][] individuals = {
//            {"1", "Tim ab", "M", "01-01-1900", "NA", "NA", "NA", "NA", "NA"},
//            {"2", "Timantha ab", "F", "01-01-1900", "NA", "NA", "NA", "NA", "NA"},
//            {"3", "Joe ab", "M", "01-01-1900", "NA", "NA", "03-15-1976", "NA", "NA"},
//            {"4", "Jane ab", "F", "01-01-1900", "NA", "NA", "NA", "NA", "NA"},
//            {"5", "Mike a", "M", "01-01-1900", "NA", "NA", "01-01-1920", "NA", "NA"},
//            {"6", "Michelle ab", "F", "01-01-1915", "NA", "NA", "01-01-1940", "NA", "NA"},
//            {"7", "Fred ab", "M", "01-01-1915", "NA", "NA", "07-04-1986", "NA", "NA"},
//            {"8", "Freeda ab", "M", "01-01-1915", "NA", "NA", "07-04-1976", "NA", "NA"},
//            {"9", "Somya ab", "M", "01-01-1900", "NA", "NA", "NA", "NA", "NA"},
//            {"10", "Somyee ab", "F", "02-01-1910", "NA", "NA", "NA", "NA", "NA"}
//    };
//    static String[][] families = {
//            {"1", "01-01-1900", "NA", "11", "John ab", "NA", "NA", "1 2 3 4 5 6 7 8 9 10"}
//
//    };
    public static boolean CheckMaleLastNames(String[][] families, String[][] individuals){
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
                            if(ind[2] == "M"){
                                if(!indname.equalsIgnoreCase(ln)){
                                    System.out.println("Error in family: "+ family[0] + " with individual: " + ind[0]);
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
