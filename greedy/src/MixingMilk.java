import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class MixingMilk {
    public static int solve(int M, int N, int[] units, int[] price) {

        // M - daily requirement
        // N - number of farmers
        // units - units of milk available from each farmer
        // price - price per unit from each farmer

        SortedMap<Integer, Integer> costToVolume = new TreeMap<>();
        int cost = 0;

        for (int i = 0; i < units.length; i++) {
            costToVolume.put(
                    price[i],
                    costToVolume.getOrDefault(price[i], 0) + units[i]
            );
        }

        for (Map.Entry<Integer, Integer> entry : costToVolume.entrySet()) {
            M -= entry.getValue();
            cost += entry.getValue() * entry.getKey();

            if (M <= 0) {
                cost += M * entry.getKey();
                break;
            }
        }

        return cost;
    }

    public static void main(String[] args) {
        SortedMap<Integer, String> map = new TreeMap<Integer, String>();

// Add Items to the TreeMap
        map.put(new Integer(1), "One");
        map.put(new Integer(3), "Three");
        map.put(new Integer(2), "Two");

// Iterate over them
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
    }
}
