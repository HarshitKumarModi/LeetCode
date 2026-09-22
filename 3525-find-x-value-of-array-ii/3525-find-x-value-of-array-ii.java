class Solution {

    class Node {
        int prod;
        int[] cnt;

        Node(int k) {
            cnt = new int[k];
            prod = 1 % k;
        }
    }

    int k;
    Node[] tree;

    // Merge two segments: left + right
    Node merge(Node left, Node right) {

        Node res = new Node(k);

        // Product of complete segment
        res.prod = (left.prod * right.prod) % k;

        // Prefixes completely inside left
        for (int r = 0; r < k; r++) {
            res.cnt[r] = left.cnt[r];
        }

        // Prefixes that cross from left into right
        for (int r = 0; r < k; r++) {
            int newRem = (left.prod * r) % k;
            res.cnt[newRem] += right.cnt[r];
        }

        return res;
    }

    void build(int node, int l, int r, int[] nums) {

        if (l == r) {
            int val = nums[l] % k;

            tree[node].prod = val;
            tree[node].cnt[val] = 1;

            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    void update(int node, int l, int r, int index, int value) {

        if (l == r) {

            value %= k;

            tree[node].prod = value;

            // Reset counts
            for (int i = 0; i < k; i++) {
                tree[node].cnt[i] = 0;
            }

            tree[node].cnt[value] = 1;

            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, r, index, value);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    Node query(int node, int l, int r, int ql, int qr) {

        // Completely inside
        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = (l + r) / 2;

        // Completely in left
        if (qr <= mid) {
            return query(node * 2, l, mid, ql, qr);
        }

        // Completely in right
        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, r, ql, qr);
        }

        // Overlapping both sides
        Node left = query(node * 2, l, mid, ql, qr);
        Node right = query(node * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        this.k = k;

        int n = nums.length;

        tree = new Node[4 * n];

        for (int i = 0; i < tree.length; i++) {
            tree[i] = new Node(k);
        }

        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // Persistent update
            update(1, 0, n - 1, index, value);

            // Query [start ... n-1]
            Node result = query(
                1,
                0,
                n - 1,
                start,
                n - 1
            );

            ans[i] = result.cnt[x];
        }

        return ans;
    }
}