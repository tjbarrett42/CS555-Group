// Dylan DiGeronimo
// I pledge my honor that I have abided by the Stevens Honor System

public class UniqueNameAndBirthDate {

	// Function that iterates over all individuals and checks if their name and birth date are unique
	public static boolean checkUniqueNameAndBirthDate(String[][] individuals) {
		// Loop through all individuals and check that their name is unique
		for (int i = 0; i < individuals.length-1; i++) {
			for (int j = i+1; j < individuals.length; j++) {
				if (individuals[i][1] == individuals[j][1]) {
					// Name is the same, now check birthday
					if (individuals[i][3] == individuals[j][3]) {
						// Birthday is the same
						return false;
					}
				}
			}
		}	
		// No errors found, return true
		return true;
	}
}