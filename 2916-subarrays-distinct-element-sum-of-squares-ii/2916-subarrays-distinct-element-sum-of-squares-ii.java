class Solution {
    static final long MOD = 1_000_000_007L;

    long[] sum, sq, lazy;
    int n;

    public int sumCounts(int[] nums) {
        n = nums.length;
        sum = new long[4 * n];
        sq = new long[4 * n];
        lazy = new long[4 * n];

        java.util.HashMap<Integer, Integer> last = new java.util.HashMap<>();
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int prev = last.getOrDefault(nums[i], -1);

            update(1, 0, n - 1, prev + 1, i, 1);

            ans = (ans + sq[1]) % MOD;
            last.put(nums[i], i);
        }

        return (int) ans;
    }

    void update(int node, int start, int end, int l, int r, long val) {
        if (l > end || r < start) return;

        if (l <= start && end <= r) {
            apply(node, start, end, val);
            return;
        }

        push(node, start, end);

        int mid = (start + end) / 2;

        update(node * 2, start, mid, l, r, val);
        update(node * 2 + 1, mid + 1, end, l, r, val);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
    }

    void apply(int node, int start, int end, long val) {
        long len = end - start + 1L;

        sq[node] = (sq[node]
                + 2 * val % MOD * sum[node] % MOD
                + len % MOD * val % MOD * val % MOD) % MOD;

        sum[node] = (sum[node] + len * val) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }

    void push(int node, int start, int end) {
        if (lazy[node] == 0 || start == end) return;

        int mid = (start + end) / 2;

        apply(node * 2, start, mid, lazy[node]);
        apply(node * 2 + 1, mid + 1, end, lazy[node]);

        lazy[node] = 0;
    }
}