import java.util.*;

class Solution {
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        int[] parent = new int[n];
        int[] depth = new int[n];
        int[] size = new int[n];
        int[] heavy = new int[n];
        int[] order = new int[n];

        Arrays.fill(parent, -1);
        Arrays.fill(heavy, -1);

        int count = 0;
        int[] stack = new int[n];
        int top = 0;
        stack[top++] = 0;
        parent[0] = 0;

        while (top > 0) {
            int u = stack[--top];
            order[count++] = u;

            for (int v : adj[u]) {
                if (parent[v] == -1) {
                    parent[v] = u;
                    depth[v] = depth[u] + 1;
                    stack[top++] = v;
                }
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];
            size[u] = 1;
            int maxSize = 0;

            for (int v : adj[u]) {
                if (parent[v] == u) {
                    size[u] += size[v];

                    if (size[v] > maxSize) {
                        maxSize = size[v];
                        heavy[u] = v;
                    }
                }
            }
        }

        int[] head = new int[n];
        int[] pos = new int[n];
        int timer = 0;

        int[] nodeStack = new int[n];
        int[] headStack = new int[n];
        top = 0;

        nodeStack[top] = 0;
        headStack[top++] = 0;

        while (top > 0) {
            int u = nodeStack[--top];
            int h = headStack[top];

            while (u != -1) {
                head[u] = h;
                pos[u] = timer++;

                for (int v : adj[u]) {
                    if (parent[v] == u && v != heavy[u]) {
                        nodeStack[top] = v;
                        headStack[top++] = v;
                    }
                }

                u = heavy[u];
            }
        }

        char[] chars = s.toCharArray();
        int[] values = new int[n];

        for (int i = 0; i < n; i++) {
            values[pos[i]] = 1 << (chars[i] - 'a');
        }

        SegmentTree seg = new SegmentTree(values);
        List<Boolean> answer = new ArrayList<>();

        for (String query : queries) {
            String[] parts = query.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                chars[u] = c;
                seg.update(pos[u], 1 << (c - 'a'));
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int mask = 0;

                while (head[u] != head[v]) {
                    if (depth[head[u]] < depth[head[v]]) {
                        int temp = u;
                        u = v;
                        v = temp;
                    }

                    mask ^= seg.query(pos[head[u]], pos[u]);
                    u = parent[head[u]];
                }

                if (depth[u] > depth[v]) {
                    int temp = u;
                    u = v;
                    v = temp;
                }

                mask ^= seg.query(pos[u], pos[v]);

                answer.add((mask & (mask - 1)) == 0);
            }
        }

        return answer;
    }

    static class SegmentTree {
        int[] tree;
        int n;

        SegmentTree(int[] arr) {
            n = arr.length;
            tree = new int[4 * n];
            build(1, 0, n - 1, arr);
        }

        void build(int node, int l, int r, int[] arr) {
            if (l == r) {
                tree[node] = arr[l];
                return;
            }

            int mid = (l + r) / 2;
            build(node * 2, l, mid, arr);
            build(node * 2 + 1, mid + 1, r, arr);

            tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
        }

        void update(int idx, int value) {
            update(1, 0, n - 1, idx, value);
        }

        void update(int node, int l, int r, int idx, int value) {
            if (l == r) {
                tree[node] = value;
                return;
            }

            int mid = (l + r) / 2;

            if (idx <= mid) {
                update(node * 2, l, mid, idx, value);
            } else {
                update(node * 2 + 1, mid + 1, r, idx, value);
            }

            tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
        }

        int query(int ql, int qr) {
            return query(1, 0, n - 1, ql, qr);
        }

        int query(int node, int l, int r, int ql, int qr) {
            if (ql <= l && r <= qr) {
                return tree[node];
            }

            if (r < ql || l > qr) {
                return 0;
            }

            int mid = (l + r) / 2;

            return query(node * 2, l, mid, ql, qr)
                    ^ query(node * 2 + 1, mid + 1, r, ql, qr);
        }
    }
}