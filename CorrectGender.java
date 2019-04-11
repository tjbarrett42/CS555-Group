// Dylan DiGeronimo
// I pledge my honor that I have abided by the Stevens Honor System

public class CorrectGender {

	// Function that checks the husband and wife of each family to see if they are the right gender
	public static boolean checkCorrectGender(String[][] families, String[][] individuals) {
		String husbandID, wifeID;
		for (int i = 0; i < families.length; i++) {
			husbandID = families[i][3];
			wifeID = families[i][5];
			for (int j = 0; j < individuals.length; j++) {
				if (individuals[j][0] == husbandID) {
					if (individuals[j][2] != "Male") {
						System.out.println("ERROR: Individual " + husbandID + " is a husband but is not male!");
						return false;
					}
				} else if (individuals[j][0] == wifeID) {
					if (individuals[j][2] != "Female") {
						System.out.println("ERROR: Individual " + wifeID + " is a wife but is not female!");
						return false;
					}
				}
			}
		}
		return true;
	}
}
