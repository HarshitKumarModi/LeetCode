import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        int m = queries.length;
        int[] ans = new int[m];

        // Sort logs by time
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        // Store queries along with their original indices
        int[][] sortedQueries = new int[m][2];

        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }

        // Sort queries by time
        Arrays.sort(sortedQueries, (a, b) ->
            Integer.compare(a[0], b[0]));

        int[] count = new int[n + 1];

        int left = 0;
        int right = 0;
        int zeroServers = n;

        for (int[] q : sortedQueries) {

            int time = q[0];
            int index = q[1];

            // Add logs within the right boundary
            while (right < logs.length && logs[right][1] <= time) {

                int server = logs[right][0];

                if (count[server] == 0) {
                    zeroServers--;
                }

                count[server]++;
                right++;
            }

            // Remove logs outside the left boundary
            while (left < right && logs[left][1] < time - x) {

                int server = logs[left][0];

                count[server]--;

                if (count[server] == 0) {
                    zeroServers++;
                }

                left++;
            }

            ans[index] = zeroServers;
        }

        return ans;
    }
}