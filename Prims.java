import java.io.*;
import java.util.*;

class Vertex {
    ArrayList<AdjVertex> neigh;

    public Vertex(ArrayList<AdjVertex> neigh) {
        this.neigh = neigh;
    }
}

class AdjVertex {
    int vertex;
    long weight;

    public AdjVertex(int vertex, long weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}

public class Prims {
    private static InputReader in;
    private static PrintWriter out;

    private static ArrayList<Vertex> graph;

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);

        int N = in.nextInt();

        graph = new ArrayList<>();
        graph.add(new Vertex(new ArrayList<>())); // vertex sampah di index 0
        for (int i = 1; i <= N; i++) {
            graph.add(new Vertex(new ArrayList<>()));
        }

        int E = in.nextInt();
        for (int i = 0; i < E; i++) {
            int A = in.nextInt(), B = in.nextInt(), W = in.nextInt();

            // undirected graph
            graph.get(A).neigh.add(new AdjVertex(B, W));
            graph.get(B).neigh.add(new AdjVertex(A, W));
        }

        prims(N, 1);

        out.close();
    }

    // Minimum Spanning Tree | Prim's Algorithm
    // source: my brain
    static void prims(int N, int S) {

        Long[][] minWeight = new Long[N + 1][3];
        minWeight[S][1] = 0L;

        PriorityQueue<AdjVertex> pq = new PriorityQueue<>(
                Comparator.comparingLong(v -> v.weight)
        );
        pq.add(new AdjVertex(S, 0));

        while (pq.size() > 0) {
            AdjVertex cur = pq.poll();

            if (minWeight[cur.vertex][0] != null) continue;

            minWeight[cur.vertex][0] = 1L;


            for (AdjVertex n : graph.get(cur.vertex).neigh) {

                if (minWeight[n.vertex][0] == null &&
                        (minWeight[n.vertex][1] == null || n.weight < minWeight[n.vertex][1])) {

                    minWeight[n.vertex][1] = n.weight;
                    minWeight[n.vertex][2] = (long) cur.vertex;

                    pq.add(new AdjVertex(n.vertex, n.weight));
                }

            }
        }

        for (int i = 1; i <= N; i++) {
            // vertex | known | edge's weight | pair
            out.println(i + " " + minWeight[i][0] + " " + minWeight[i][1] + " " + minWeight[i][2]);
        }
    }

    // taken from https://codeforces.com/submissions/Petr
    // together with PrintWriter, these input-output (IO) is much faster than the
    // usual Scanner(System.in) and System.out
    // please use these classes to avoid your fast algorithm gets Time Limit
    // Exceeded caused by slow input-output (IO)
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