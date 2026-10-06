class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();

        for(int i = 0; i<=n; i++){
            graph.add(new ArrayList<>());
        }

        for(int[] time : times){
            int u  = time[0];
            int v = time[1];
            int w = time[2];

            graph.get(u).add(new int[]{v, w});
        }

        int[] distance = new int[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[k] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        pq.add(new int[]{0, k});

        while(!pq.isEmpty()){
            int[] current = pq.poll();

            int dist = current[0];
            int node = current[1];

            if(dist > distance[node]){
                continue;
            }

            for(int[] edge : graph.get(node)){
                int neighbour = edge[0];
                int weight = edge[1];

                int newDist = dist + weight;

                if(newDist < distance[neighbour]){
                    distance[neighbour] = newDist;
                    pq.add(new int[]{newDist, neighbour});
                }
            }
        }

        int answer = 0;

        for(int i = 1; i<=n; i++){
            if(distance[i] == Integer.MAX_VALUE){
                return -1;
            }

            answer = Math.max(answer, distance[i]);
        }

        return answer;
    }
}