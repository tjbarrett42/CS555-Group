// Dylan DiGeronimo
// I pledge my honor that I have abided by the Stevens Honor System

public class MarriageBeforeDivorce {

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

	public static boolean checkMarriageBeforeDivorce(String[] family) {
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
			// Return whether the marriage happened before the divorce or not
			return isBefore(family[1], family[2]);
		}
	}
}