class Solution {

    public long findKthSmallest(int[] coins, int k) {

        // Remove redundant coins
        java.util.Arrays.sort(coins);
        java.util.ArrayList<Integer> list = new java.util.ArrayList<>();

        for (int c : coins) {
            boolean keep = true;
            for (int x : list) {
                if (c % x == 0) {
                    keep = false;
                    break;
                }
            }
            if (keep) list.add(c);
        }

        int n = list.size();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = list.get(i);

        long low = 1;
        long high = (long) arr[0] * k;

        while (low < high) {

            long mid = low + (high - low) / 2;

            if (count(mid, arr) >= k)
                high = mid;
            else
                low = mid + 1;
        }

        return low;
    }

    private long count(long x, int[] coins) {

        int n = coins.length;
        long ans = 0;

        for (int mask = 1; mask < (1 << n); mask++) {

            long lcm = 1;
            int bits = 0;
            boolean overflow = false;

            for (int i = 0; i < n; i++) {

                if ((mask & (1 << i)) != 0) {

                    bits++;

                    lcm = lcm(lcm, coins[i]);

                    if (lcm > x) {
                        overflow = true;
                        break;
                    }
                }
            }

            if (overflow) continue;

            if ((bits & 1) == 1)
                ans += x / lcm;
            else
                ans -= x / lcm;
        }

        return ans;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }
}