// Dylan DiGeronimo
// I pledge my honor that I have abided by the Stevens Honor System

public class MarriageBeforeDivorce {

    // I'm pulling the basic structure for this from my group's project
    // I'm not sure where it will fit in to the full structure, so I will have it run on the already stored families rather than on the GEDCOM file

    public static boolean CheckMarriageBeforeDivorce(String[] family) {
    	if ((family[1] == "NA" && family[2] == "NA") || (family[1] != "NA" && family[2] == "NA")) {
    		// Valid to be neither married nor divorced or married but not divorced
    		return true;
        } else if (family[1] == "NA" && family[2] != "NA") {
        	// Invalid to be not married but divorced
        	return false;
        } else if (family[1] == family[2]) {
            // Married and divorced on the same day
            return true;
        } else {
            // Get both dates as ["MM", "DD"m "YYYY"]
            String[] mar = family[1].split("-");
            String[] div = family[2].split("-");
            if (Integer.parseInt(mar[2]) > Integer.parseInt(div[2])) {
                // Marriage year > divorce year
                return false;
            } else if (mar[2] == div[2]) {
                // Same year, have to check month

                if (Integer.parseInt(mar[0]) > Integer.parseInt(div[0])) {
                    // Marriage month > divorce month
                    return false;
                } else if (mar[0] == div[0]) {
                    // Same month, have to check day
                    
                    if (Integer.parseInt(mar[1]) > Integer.parseInt(div[1])) {
                        // Marriage day > divorce day
                        return false; 
                    } else {
                        // Same day or marriage < divorce
                        return true;
                    }

                } else {
                    // Marriage month < divorce month
                    return true;
                }

            } else {
                // Marriage year < divorce year
                return true;
            }
        }
    }
}