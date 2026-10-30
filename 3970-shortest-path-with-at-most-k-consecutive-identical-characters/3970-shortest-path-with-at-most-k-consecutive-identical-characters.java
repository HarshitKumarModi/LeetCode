
import java.util.*;

class Solution {

    static class State {
        int node;
        int count;
        long cost;

        State(int node, int count, long cost) {
            this.node = node;
            this.count = count;
            this.cost = cost;
        }
    }

    public int shortestPath(int n, int[][] edges, String labels, int k) {

        // Build adjacency list
        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
        }

        // Store only visited states:
        // dist[node] = {consecutiveCount -> minimumCost}
        Map<Integer, Long>[] dist = new HashMap[n];

        for (int i = 0; i < n; i++) {
            dist[i] = new HashMap<>();
        }

        PriorityQueue<State> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(a.cost, b.cost)
        );

        // Starting node
        dist[0].put(1, 0L);
        pq.offer(new State(0, 1, 0L));

        while (!pq.isEmpty()) {

            State curr = pq.poll();

            int node = curr.node;
            int count = curr.count;
            long cost = curr.cost;

            // Skip outdated states
            if (dist[node].get(count) != cost) {
                continue;
            }

            // Destination reached
            if (node == n - 1) {
                return (int) cost;
            }

            // Explore neighbours
            for (int[] edge : graph[node]) {

                int next = edge[0];
                int weight = edge[1];

                int nextCount;

                if (labels.charAt(node) == labels.charAt(next)) {
                    nextCount = count + 1;
                } else {
                    nextCount = 1;
                }

                // Check consecutive character constraint
                if (nextCount > k) {
                    continue;
                }

                long newCost = cost + weight;

                Long oldCost = dist[next].get(nextCount);

                if (oldCost == null || newCost < oldCost) {

                    dist[next].put(nextCount, newCost);

                    pq.offer(
                        new State(next, nextCount, newCost)
                    );
                }
            }
        }

        return -1;
    }
}
