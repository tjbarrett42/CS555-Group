// Dylan DiGeronimo
// I pledge my honor that I have abided by the Stevens Honor System

public class UniqueIDs {

	// Function that iterates over all individuals and checks if their ID is unique
	public static boolean checkUniqueIDs(String[][] individuals) {
		// Loop through all individuals and check that their ID isn't the same as any that come after
		for (int i = 0; i < individuals.length-1; i++) {
			for (int j = i+1; j < individuals.length; j++) {
				if (individuals[i][0] == individuals[j][0]) {
					System.out.println("ERROR: ID " + individuals[i][0] + " was used for multiple people!");
					return false;
				}
			}
		}	
		// No errors found, return true
		return true;
	}
}
