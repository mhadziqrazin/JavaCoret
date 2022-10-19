import java.io.*;
import java.util.*;


public class JavaCoret {
    private static InputReader in;
    private static PrintWriter out;

    private static Integer[] memo;
    private static int num;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        num = scanner.nextInt();
        memo = new Integer[num];
        System.out.println(g(num));
        System.out.println(Arrays.toString(memo));

    }

    static int g(int ret) {
        if (ret == 0) {
            return 0;
        }
        if (ret < 0) {
            return num;
        }

        if (memo[ret - 1] != null) {
            return memo[ret - 1];
        }

        int dua5 = 1 + g(ret - 25);
        int dua1 = 1 + g(ret - 21);
        int sepuluh = 1 + g(ret - 10);
        int satu = 1 + g(ret - 1);
//        System.out.println(dua5 + " " + dua1 + " " + sepuluh + " " + satu);

        int temp1 = Math.min(dua5, dua1);
        int temp2 = Math.min(sepuluh, satu);
        int temp3 = Math.min(temp1, temp2);

        return memo[ret - 1] = temp3;
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}