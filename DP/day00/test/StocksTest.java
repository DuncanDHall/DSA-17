
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class StocksTest {

    /**
     * Test method for {@link Stocks#maxProfit(int[])}.
     */
    @Test
    public void testMaxProfit() throws FileNotFoundException {
        for(int i = 1; i <= 7; i++){
            Scanner input = new Scanner(new File(
                    "testcases/maxprofit." + i + ".in"));
            Scanner output = new Scanner(new File(
                    "testcases/maxprofit." + i + ".out"));

            int n = input.nextInt();
            int[] prices = new int[n];
            for(int j = 0; j < n; j++){
                prices[j] = input.nextInt();
            }
            int expected = output.nextInt();
            int actual = new Stocks().maxProfit(prices);
            if (expected != actual) {
                System.out.println(Arrays.toString(prices));
                System.out.println("a:" + actual);
                System.out.println("e:" + expected);
            }
            assertEquals(expected, actual);

            input.close();
            output.close();
        }
    }

    /**
     * Test method for {@link Stocks#maxProfitWithK(int[], int)}}.
     */
    @Test
    public void testMaxProfitWithK() throws FileNotFoundException {
        for(int i = 1; i <= 9; i++){
            Scanner input = new Scanner(new File(
                    "testcases/maxprofitk." + i + ".in"));
            Scanner output = new Scanner(new File(
                    "testcases/maxprofitk." + i + ".out"));

            int n = input.nextInt();
            int k = input.nextInt();
            int[] prices = new int[n];
            for(int j = 0; j < n; j++){
                prices[j] = input.nextInt();
            }

            assertEquals(output.nextInt(),new Stocks().maxProfitWithK(prices,k));

            input.close();
            output.close();
        }
    }
}
