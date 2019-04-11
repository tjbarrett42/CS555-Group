// Dylan DiGeronimo
// I pledge my honor that I have abided by the Stevens Honor System

public class SiblingMarriage {

	// Function that checks the husband and wife of each family to make sure they aren't siblings
	public static boolean checkSiblingMarriage(String[][] families, String[][] individuals) {
		String husbandID = "", wifeID = "", husbandFam = "", wifeFam = "";
		for (int i = 0; i < families.length; i++) {
			husbandID = families[i][3];
			wifeID = families[i][5];
			for (int j = 0; j < individuals.length; j++) {
				if (individuals[j][0] == husbandID) {
					husbandFam = individuals[j][7];
				} else if (individuals[j][0] == wifeID) {
					wifeFam = individuals[j][7];
				}
			}
			if (husbandFam == wifeFam && husbandFam != "") {
				System.out.println("ERROR: individuals " + husbandID + " and " + wifeID + " are siblings but also married!");
				return false;
			} 
		}
		return true;
	}
}