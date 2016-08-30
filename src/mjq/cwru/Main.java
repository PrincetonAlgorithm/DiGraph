package mjq.cwru;

import javax.swing.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        // write your code here
    }

    public class Bag<Item> implements Iterable<Item> {
        int N;

        public Bag() {
        }

        public void add(Item x) {
        }

        public int size() {
            return N;
        }

        @Override
        public Iterator<Item> iterator() {
            return null;
        }
    }

    public class DiGraph {
        private int V;
        private Bag<Integer>[] adj;

        public DiGraph(int V) {
            this.V = V;
//            it's wrong
//            adj = new Bag<Integer>[V];
//            it's right
            adj = (Bag<Integer>[]) new Bag[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new Bag<Integer>();
            }

        }

        public void addEdge(int v, int w) {
            adj[v].add(w);
        }

        public Iterable<Integer> adj(int v) {
            return adj[v];
        }

        //number of vertices
        public int V() {
            return this.V;
        }

        //number of edges
        public int E() {
            return 0;
        }

        public DiGraph reverse() {
            return null;
        }

        //
        public String toString() {
            return null;
        }
    }

    public class DirectedDFS {
        private boolean[] marked;

        public DirectedDFS(DiGraph G, int s) {
            marked = new boolean[G.V()];
            dfs(G, s);
        }

        private void dfs(DiGraph G, int v) {
            marked[v] = true;
            for (int w : G.adj(v)) {
                if (!marked[w])
                    dfs(G, w);
            }
        }

        //client can ask whether any vertex is reachable from s
        public boolean visited(int v) {
            return marked[v];
        }
    }

    public class DirectedBFS {
        private boolean[] marked;

        public DirectedBFS(DiGraph G, int s) {
            marked = new boolean[G.V()];
            bfs(G, s);
        }

        private void bfs(DiGraph G, int s) {
            Queue<Integer> queue = new LinkedList<Integer>();
            queue.offer(s);
            marked[s] = true;
            while (!queue.isEmpty()) {
                int v = queue.poll();
                for (int w : G.adj(v)) {
                    if (!marked[w]) {
                        queue.offer(w);
                        marked[w] = true;
                    }
                }
            }
        }
    }
}
