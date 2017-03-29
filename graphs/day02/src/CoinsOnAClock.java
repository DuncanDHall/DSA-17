import java.util.ArrayList;
import java.util.List;

public class CoinsOnAClock {

    public static List<char[]> coinsOnAClock(int pennies, int nickels, int dimes, int hoursInDay) {

        char[] clock = new char[hoursInDay];
        ArrayList<char[]> solutions = new ArrayList<>();

        coinsOnAClockHelper(pennies, nickels, dimes, clock, hoursInDay, 0, solutions);

        return solutions;
    }

    private static void coinsOnAClockHelper(
            int pennies, int nickels, int dimes, char[] clock, int hoursInDay, int next, ArrayList<char[]> solutions
    ) {
        // base cases
        if (clock[next] != '\0') {
            // if there is a blank spot, then dead end
            for (char c: clock) if (c == '\0') return;

            // if there is no blank spot, this is a solution;
            solutions.add(clock.clone());
            return;
        }

        // try placing a penny
        if (pennies != 0) {
            clock[next] = 'p';
            coinsOnAClockHelper(pennies - 1, nickels, dimes, clock, hoursInDay, (next + 1) % hoursInDay, solutions);
            clock[next] = '\0';
        }

        // try placing a nickel
        if (nickels != 0) {
            clock[next] = 'n';
            coinsOnAClockHelper(pennies, nickels - 1, dimes, clock, hoursInDay, (next + 5) % hoursInDay, solutions);
            clock[next] = '\0';

        }

        // try placing a dime
        if (dimes != 0) {
            clock[next] = 'd';
            coinsOnAClockHelper(pennies, nickels, dimes - 1, clock, hoursInDay, (next + 10) % hoursInDay, solutions);
            clock[next] = '\0';

        }
    }

    public static void main(String[] args) {
        coinsOnAClock(0, 0, 0, 12);
    }

}
