// Dylan DiGeronimo and Somya Gambhir
// I pledge my honor that I have abided by the Stevens Honor System

public class MarriageBeforeDeath {

	static String[][] individuals = { { "1", "Tim", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA" },
			{ "2", "Timantha", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA" },
			{ "3", "Joe", "NA", "01-01-1900", "NA", "NA", "03-15-1976", "NA", "NA" },
			{ "4", "Jane", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA" },
			{ "5", "Mike", "NA", "01-01-1900", "NA", "NA", "01-01-1920", "NA", "NA" },
			{ "6", "Michelle", "NA", "01-01-1915", "NA", "NA", "01-01-1940", "NA", "NA" },
			{ "7", "Fred", "NA", "01-01-1915", "NA", "NA", "07-04-1990", "NA", "NA" },
			{ "8", "Freeda", "NA", "01-01-1915", "NA", "NA", "07-04-1970", "NA", "NA" },
			{ "9", "Somya", "NA", "01-01-1900", "NA", "NA", "NA", "NA", "NA" },
			{ "10", "Somyee", "NA", "02-01-1910", "NA", "NA", "NA", "NA", "NA" } };

	// Returns false if the first date string is later than the second date string
	public static boolean isBefore(String dateString1, String dateString2) {
		// Split date strings into arrays
		String[] dateStringArray1 = dateString1.split("-");
		String[] dateStringArray2 = dateString2.split("-");
		// Convert string arrays to integer arrays
		int[] d1 = new int[dateStringArray1.length];
		for (int i = 0; i < dateStringArray1.length; i++) {
			d1[i] = Integer.parseInt(dateStringArray1[i]);
		}
		int[] d2 = new int[dateStringArray2.length];
		for (int i = 0; i < dateStringArray2.length; i++) {
			d2[i] = Integer.parseInt(dateStringArray2[i]);
		}
		if (d1[2] > d2[2]) {
			// d1 year after d2 year
			return false;
		} else if (d1[2] == d2[2]) {
			// Same year, have to check month
			if (d1[0] > d2[0]) {
				// d1 month after d2 month
				return false;
			} else if (d1[0] == d2[0]) {
				// Same month, have to check day

				if (d1[1] > d2[1]) {
					// d1 day after d2 day
					return false;
				} else {
					// Same day or d1 before d2
					return true;
				}
			} else {
				// d1 month < d2 month
				return true;
			}
		} else {
			// d1 year < d2 year
			return true;
		}
	}

	public static boolean checkMarriageBeforeDeath(String[] family) {
		int indexOfHusband = -1;
		int indexOfWife = -1;
		// Check marriage details
		if (family[1] == "NA") {
			// Not married, no need to check
			return true;
		} else {
			// Married, need to check death dates
			// First, we search for the husband (family[3]) and wife (family[5]) in
			// individuals
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
		} else if (indexOfHusband != -1) {
			// Husband is dead, check if it's before marriage
			if (!isBefore(family[1], individuals[indexOfHusband][6]))
				return false;
		}

		// Check wife
		if (indexOfWife != -1 && individuals[indexOfWife][6] == "NA") {
			// Not dead, that's ok
			return true;
		} else if (indexOfWife != -1) {
			// Wife is dead, check if it's before marriage
			return isBefore(family[1], individuals[indexOfWife][6]);
		}
		return true;
	}
}
