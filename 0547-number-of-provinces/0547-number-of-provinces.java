class Solution {

    public void bfs(int start, int[][] isConnected, boolean[] visited){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()){
            int node = queue.poll();
            for(int neighbour = 0; neighbour<isConnected.length; neighbour++){
                if (isConnected[node][neighbour] == 1 && !visited[neighbour]){
                    visited[neighbour] = true;
                    queue.add(neighbour);
                }
            }
        }
    }
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int provinces = 0;
        boolean[] visited = new boolean[n];

        for(int i = 0; i<n; i++){
            if(!visited[i]){
                provinces++;
                bfs(i, isConnected, visited);
            }
        }

        return provinces;
    }
}