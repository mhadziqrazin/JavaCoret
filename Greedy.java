import java.util.*;


public class Greedy {
    private static Long[][] memo;
    private static int num;
    private static int[] coins;
    private static long firstPick; // coin pertama yang dipilih


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // masukin koin yang available
        coins = new int[]{25, 21, 10, 1};

        num = scanner.nextInt();
        memo = new Long[num][2]; // 0 = banyak coin; 1 = coin pertama
        System.out.println("Coins needed = " + g(num));

        // cetak coin apa aja
        System.out.print("What are those = ");
        int temp = num;
        while (temp != 0) {
            System.out.print(memo[temp-1][1] + " ");
            temp -= memo[temp-1][1];
        }
    }

    static long g(int ret) {
        if (ret == 0) {
            return 0;
        }
        if (ret < 0) {
            return num; // biar gede dan ga kepilih di bawah
        }

        if (memo[ret - 1][0] != null) {
            return memo[ret - 1][0];
        }

        long minCoin = Long.MAX_VALUE;
        long temp;
        long[] options = new long[coins.length]; // opsi cabang coin

        // iterasi tiap opsi coin
        for (int i = 0; i < coins.length; i++) {
            temp = 1 + g(ret - coins[i]);
            minCoin = Math.min(minCoin, temp);
            options[i] = temp;
        }

        temp = findMin(options);
        memo[ret - 1][1] = firstPick;
        return memo[ret - 1][0] = temp;
    }

    public static long findMin(long[] arr) {
        long min = Long.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
                firstPick = coins[i];
            }
        }
        return min;
    }
}