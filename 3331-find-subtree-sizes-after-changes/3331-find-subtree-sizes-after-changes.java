
import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        // Build original tree
        ArrayList<Integer>[] children = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }

        // Store the new parent of every node
        int[] newParent = new int[n];
        newParent[0] = -1;

        // Track the nearest ancestor having each character
        int[] last = new int[26];
        Arrays.fill(last, -1);

        int[] previous = new int[n];

        // Iterative DFS with enter and exit states
        int[] stack = new int[2 * n + 1];
        int top = 0;

        stack[top++] = 0;

        while (top > 0) {
            int val = stack[--top];

            if (val >= 0) {
                // Enter node
                int node = val;
                int ch = s.charAt(node) - 'a';

                previous[node] = last[ch];

                if (node != 0) {
                    if (last[ch] != -1) {
                        newParent[node] = last[ch];
                    } else {
                        newParent[node] = parent[node];
                    }
                }

                last[ch] = node;

                // Exit event
                stack[top++] = ~node;

                // Visit original children
                for (int child : children[node]) {
                    stack[top++] = child;
                }

            } else {
                // Exit node and restore ancestor state
                int node = ~val;
                int ch = s.charAt(node) - 'a';

                last[ch] = previous[node];
            }
        }

        // Build modified tree
        ArrayList<Integer>[] updated = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            updated[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            updated[newParent[i]].add(i);
        }

        // Traverse modified tree
        int[] order = new int[n];
        top = 0;
        int count = 0;

        stack[top++] = 0;

        while (top > 0) {
            int node = stack[--top];

            order[count++] = node;

            for (int child : updated[node]) {
                stack[top++] = child;
            }
        }

        // Calculate subtree sizes bottom-up
        int[] answer = new int[n];
        Arrays.fill(answer, 1);

        for (int i = count - 1; i > 0; i--) {
            int node = order[i];

            answer[newParent[node]] += answer[node];
        }

        return answer;
    }
}
