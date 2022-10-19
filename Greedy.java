import java.util.*;


public class Greedy {
    private static Long[][] memo;
    private static int[] coins;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // masukin koin yang available
        coins = new int[]{25, 21, 10, 1};

        int num = scanner.nextInt();
        memo = new Long[num][2]; // 0 = banyak coin; 1 = coin pertama
        System.out.println("Coins needed: " + g(num));

        // cetak coin apa aja
        System.out.println("What are those:");
        int temp = num;
        long current = -1;
        int countCoin = 0;
        while (temp != 0) {
            if (current == memo[temp-1][1]) {
                countCoin++;
            } else {
                if (current != -1) {
                    System.out.println(countCoin + "x coin " + current);
                }
                current = memo[temp-1][1];
                countCoin = 1;
            }

            temp -= memo[temp-1][1];
        }
        System.out.println(countCoin + "x coin " + current);
    }

    static long g(int ret) {
        if (ret == 0) {
            return 0;
        }

        if (memo[ret - 1][0] != null) {
            return memo[ret - 1][0];
        }

        long minCoin = 0;
        long minNeeds = Long.MAX_VALUE;
        long temp;

        // iterasi tiap opsi coin
        for (int coin : coins) {
            if (coin <= ret) {
                temp = 1 + g(ret - coin);
                if (temp < minNeeds) {
                    minNeeds = temp;
                    minCoin = coin;
                }
            }
        }

        memo[ret - 1][1] = minCoin;
        return memo[ret - 1][0] = minNeeds;
    }
}