public class BirthBeforeMarriage {

    public static boolean CheckUS02(String[] family, String[][] individuals) {
        int indexOfHusband = -1;
        int indexOfWife = -1;
        String[] mar = family[1].split("-");
        // Check marriage
        if (family[1] == "NA") {
            // Not married
            return true;
        } else {
            // The family is married
            for (int i = 0; i < individuals.length; i++) {
                if (individuals[i][0] == family[3]) {
                    // Husband
                    indexOfHusband = i;
                } else if (individuals[i][0] == family[5]) {
                    // Wife
                    indexOfWife = i;
                }
            }
        }

        // Check husband for birth before marriage
        if (indexOfHusband != -1 && individuals[indexOfHusband][3] == "NA") {

        } else if (indexOfHusband != -1) {
            String[] husbandBirth = individuals[indexOfHusband][3].split("-");
            //Year comparison
            if (Integer.parseInt(husbandBirth[2]) < Integer.parseInt(mar[2])) {

            } else if (Integer.parseInt(husbandBirth[0]) < Integer.parseInt(mar[0])) {

            } else if (Integer.parseInt(husbandBirth[1]) < Integer.parseInt(mar[1])) {

            } else {
                return false;
            }
        }

        //Here we will see if wife married before birthday
        if (indexOfWife != -1 && individuals[indexOfWife][3] == "NA") {
        } else if (indexOfWife != -1) {
            String[] wifeBirth = individuals[indexOfWife][3].split("-");
            // Year comparison
            if (Integer.parseInt(wifeBirth[2]) < Integer.parseInt(mar[2])) {
                return true;
            } else if (Integer.parseInt(wifeBirth[0]) < Integer.parseInt(mar[0])) {
                return true;
            } else if (Integer.parseInt(wifeBirth[1]) < Integer.parseInt(mar[1])) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
        return true;

    }

}
