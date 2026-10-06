class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for(int i = 0; i<numCourses; i++){
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[numCourses];

        for(int[] prerequisite : prerequisites){
            int course = prerequisite[0];
            int pre = prerequisite[1];

            graph.get(pre).add(course);

            indegree[course]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0; i<numCourses; i++){
            if(indegree[i] == 0){
                queue.add(i);
            }
        }

        int index = 0;
        int[] ans = new int[numCourses];

        while(!queue.isEmpty()){
            int node = queue.poll();
            ans[index] = node;
            index++;

            for(int neighbour : graph.get(node)){
                indegree[neighbour]--;
                if(indegree[neighbour] == 0){
                    queue.add(neighbour);
                }
            }
        }

        if(index != numCourses){
            return new int[0];
        }

        return ans;
    }
}