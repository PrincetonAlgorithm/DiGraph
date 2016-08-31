package mjq.cwru;

import javax.swing.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

    //reverse DFS post-order of a DAG is a topological order
    public class DepthFirstOrder {
        private boolean[] marked;
        private Stack<Integer> reversePost;

        public DepthFirstOrder(DiGraph G) {
            marked = new boolean[G.V()];
            reversePost = new Stack<Integer>();
            for (int v = 0; v < G.V(); v++) {

            }
        }

        private void dfs(DiGraph G, int v) {
            marked[v] = true;
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    dfs(G, w);
                }
            }
            reversePost.push(v);
        }

        public Iterable<Integer> reversePost() {
            return reversePost;
        }

        public class StronglyConnectedComponent {
            private boolean[] marked;
            private int[] id;
            private int count = 0;

            public StronglyConnectedComponent(DiGraph G) {
                marked = new boolean[G.V()];
                id = new int[G.V()];

                DepthFirstOrder dfo = new DepthFirstOrder(G);
                for (int v : dfo.reversePost()) {
                    if (!marked[v]) {
                        dfs(G, v);
                        count++;
                    }
                }
            }

            private void dfs(DiGraph G, int v) {
                marked[v] = true;
                id[v] = count;
                for (int w : G.adj(v)) {
                    if (!marked[w]) {
                        dfs(G, w);
                    }
                }
            }

            public boolean stronglyConnected(int v, int w) {
                return id[v] == id[w];
            }

        }

    }
}
