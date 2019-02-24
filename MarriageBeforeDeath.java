// Dylan DiGeronimo and Somya Gambhir
// I pledge my honor that I have abided by the Stevens Honor System

public class MarriageBeforeDeath extends GEDParser {

	static String[][] individuals = {
	        {"1", "Tim", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA"},
	        {"2", "Timantha", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA"},
	        {"3", "Joe", "NA", "01-01-1900", "NA", "NA", "03-15-1976", "NA", "NA"},
	        {"4", "Jane", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA"},
	        {"5", "Mike", "NA", "01-01-1900", "NA", "NA", "01-01-1920", "NA", "NA"},
	        {"6", "Michelle", "NA", "01-01-1915", "NA", "NA", "01-01-1940", "NA", "NA"},
	        {"7", "Fred", "NA", "01-01-1915", "NA", "NA", "07-04-1986", "NA", "NA"},
	        {"8", "Freeda", "NA", "01-01-1915", "NA", "NA", "07-04-1976", "NA", "NA"},
	        {"9", "Somya", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA"},
	        {"10", "Somyee", "NA", "02-01-1910", "NA", "NA", "NA", "NA", "NA"}
	};
	
	public static boolean checkMarriageBeforeDeath(String[] family) {
		int indexOfHusband = -1;
		int indexOfWife = -1;
		String[] mar = family[1].split("-");
		// Check marriage details
		if (family[1] == "NA") {
			// Not married, no need to check
			return true;
		} else {
			// Married, need to check death dates
			// First, we search for the husband (family[3]) and wife (family[5] in individuals
			for (int i = 0; i < individuals.length; i++) {
				if (individuals[i][0] == family[3]) {
					// Husband found
					indexOfHusband = i;
				} else if (individuals[i][0] == family[5]) {
					// Wife found
					indexOfWife = i;
				}
			}
		}
		
		// Check husband
		if (indexOfHusband != -1 && individuals[indexOfHusband][6] == "NA") {
			// Not dead, that's ok
		} else if (indexOfHusband != -1){
			// Husband is dead, check if it's before marriage
			String[] husbandDeath = individuals[indexOfHusband][6].split("-");
			// Check year
			if (Integer.parseInt(husbandDeath[2]) < Integer.parseInt(mar[2])) {
				// Death before marriage
				return false;
			} else if (husbandDeath[2] == mar[2]) {
				// Same year, check month
				if (Integer.parseInt(husbandDeath[0]) < Integer.parseInt(mar[0])) {
					// Death before marriage
					return false;
				} else if (husbandDeath[0] == mar[0]) {
					// Same month, check day
					if (Integer.parseInt(husbandDeath[1]) < Integer.parseInt(mar[1])) {
						// Death before marriage
						return false;
					} else {
						// Same day or later day, continue to wife
					}
				} else {
					// Death after marriage, continue to wife
				}
			} else {
				// Death after marriage, continue to wife
			}
		}
		
		// Check wife
		if (indexOfWife != -1 && individuals[indexOfWife][6] == "NA") {
			// Not dead, that's ok
		} else if (indexOfWife != -1){
			// Wife is dead, check if it's before marriage
			String[] wifeDeath = individuals[indexOfWife][6].split("-");
			// Check year
			if (Integer.parseInt(wifeDeath[2]) < Integer.parseInt(mar[2])) {
				// Death before marriage
				return false;
			} else if (wifeDeath[2] == mar[2]) {
				// Same year, check month
				if (Integer.parseInt(wifeDeath[0]) < Integer.parseInt(mar[0])) {
					// Death before marriage
					return false;
				} else if (wifeDeath[0] == mar[0]) {
					// Same month, check day
					if (Integer.parseInt(wifeDeath[1]) < Integer.parseInt(mar[1])) {
						// Death before marriage
						return false;
					} else {
						// Same day or later day, that's ok
						return true;
					}
				} else {
					// Death after marriage, that's ok
					return true;
				}
			} else {
				// Death after marriage, that's ok
				return true;
			}
		}
		return true;
	}
}
