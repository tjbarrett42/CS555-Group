// Dylan DiGeronimo and Somya Gambhir
// I pledge my honor that I have abided by the Stevens Honor System

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class LessThan150 {
	
	// Set date format for getting today's date
	public static final String DATEFORMAT = "MM-dd-yyyy";

	// Returns a string of today's date 
	public static String today() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat s = new SimpleDateFormat(DATEFORMAT);
		return s.format(c.getTime());
	}
	
	// Returns whether two dates are within 150 years or not
	public static boolean lessThan150YearsApart(String dateString1, String dateString2) {
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
		if(d2[2] - d1[2] > 150) {
			// Years are at least 150 years apart
			return false;
		} else if (d2[2] - d1[2] == 150) {
			// In this case, the dates may still be less than 150 years apart
			// We need to check to see if the 150th anniversary of d1 has happened yet
			if (d2[0] > d1[0]) {
				// It has happened, as d2 is a later month
				return false;
			} else if (d2[0] == d1[0]) {
				// Same month, check day
				if (d2[1] >= d1[1]) {
					// Either a later day or the 150th anniversary
					return false;
				} else {
					// Earlier day
					return true;
				}
			} else {
				// Earlier month
				return true;
			}
		} else {
			// Must be less than 150 years old
			return true;
		}
	}

	// Determine if an individual is less than 150 years old
	public static boolean checkLessThan150(String[] individual) {
		String born =  individual[3];
		// First, determine if they are dead
		if (individual[6] == "NA") {
			// Not dead, compare birthday and current day
			String today = today();
			return lessThan150YearsApart(born, today);
		} else {
			// Dead, compare birthday and death day
			String dead = individual[6];
			return lessThan150YearsApart(born, dead);
		}
	}
}
