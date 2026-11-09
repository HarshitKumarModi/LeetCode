import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        TreeSet<Integer> available = new TreeSet<>();

        // Initially, all servers are available
        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        // Stores {finishTime, serverId}
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        int[] count = new int[k];

        for (int i = 0; i < arrival.length; i++) {

            // Release servers whose requests have completed
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                long[] server = busy.poll();
                available.add((int) server[1]);
            }

            // If no server is available, drop the request
            if (available.isEmpty()) {
                continue;
            }

            // Preferred server
            int preferred = i % k;

            // Find the first available server >= preferred
            Integer server = available.ceiling(preferred);

            // Wrap around if no such server exists
            if (server == null) {
                server = available.first();
            }

            // Assign request to the server
            available.remove(server);

            count[server]++;

            // Mark server as busy until request completion
            busy.offer(new long[]{
                (long) arrival[i] + load[i],
                server
            });
        }

        // Find maximum number of handled requests
        int max = 0;

        for (int c : count) {
            max = Math.max(max, c);
        }

        // Collect all busiest servers
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == max) {
                result.add(i);
            }
        }

        return result;
    }
}