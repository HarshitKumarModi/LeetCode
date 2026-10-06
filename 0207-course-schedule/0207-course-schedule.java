import java.util.*;

public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for(int i = 0; i<numCourses; i++){
            graph.add(new ArrayList<>());
        }

        for(int[] prerequisite : prerequisites){
            int course = prerequisite[0];
            int pre = prerequisite[1];

            graph.get(pre).add(course);
        }

        boolean[] visited = new boolean[numCourses];
        boolean[] pathVisited = new boolean[numCourses];

        for(int i = 0; i<numCourses; i++){
            if(!visited[i]){
                if(dfs(i, graph, visited, pathVisited)){
                    return false;
                }
            }
        }

        return true;
    }

    private boolean dfs(int node, ArrayList<ArrayList<Integer>> graph, boolean[] visited, boolean[] pathVisited){
        visited[node] = true;
        pathVisited[node] = true;

        for(int neighbour : graph.get(node)){
            if(!visited[neighbour]){
                if(dfs(neighbour, graph, visited, pathVisited)){
                    return true;
                }
            } else if (pathVisited[neighbour]){
                return true;
            }
        }

        pathVisited[node] = false;

        return false;
    }
}
