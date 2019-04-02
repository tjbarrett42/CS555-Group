import java.util.*;

public class ListLivingMarried {

    // List all living married individuals (id)
    public static List CheckListLivingMarried(String[][] families, String[][] individuals) {

        List<String> livingMarried = new ArrayList<>();

        // Iterate through families,
        for (int i = 0; i < families.length; i++) {
            // Match husband ID to individual
            String husbandID = families[i][3];
            for (int j = 0; j < individuals.length; i++) {
                // If husbandID[3] (death date) is "", list.add(husbandID)
                if (individuals[j][0] == husbandID) {
                    if (individuals[j][3] != "") {
                        livingMarried.add(husbandID);
                    }
                }
            }

            // Match wife ID to individual
            String wifeID = families[i][5];
            for (int j = 0; j < individuals.length; i++) {
                // If wifeID[3] (death date) is "", list.add(wifeID)
                if (individuals[j][0] == wifeID) {
                    if (individuals[j][3] != "") {
                        livingMarried.add(wifeID);
                    }
                }
            }
        }

        return livingMarried;
    }
}