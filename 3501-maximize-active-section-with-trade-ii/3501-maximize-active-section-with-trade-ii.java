import java.util.*;

class Group {
    int start, length;

    Group(int start, int length) {
        this.start = start;
        this.length = length;
    }
}

class SparseTable {
    private int[][] st;

    SparseTable(int[] nums) {
        int n = nums.length;
        int k = bitLength(n);
        st = new int[k + 1][n + 1];

        for (int i = 0; i < n; i++)
            st[0][i] = nums[i];

        for (int i = 1; i <= k; i++) {
            for (int j = 0; j + (1 << i) <= n; j++) {
                st[i][j] = Math.max(
                        st[i - 1][j],
                        st[i - 1][j + (1 << (i - 1))]
                );
            }
        }
    }

    int query(int l, int r) {
        int k = bitLength(r - l + 1) - 1;
        return Math.max(st[k][l], st[k][r - (1 << k) + 1]);
    }

    private int bitLength(int x) {
        return x == 0 ? 0 : Integer.SIZE - Integer.numberOfLeadingZeros(x);
    }
}

class Solution {

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int ones = 0;
        for (char c : s.toCharArray())
            if (c == '1')
                ones++;

        List<Group> zeroGroups = new ArrayList<>();
        int[] zeroGroupIndex = new int[s.length()];
        getZeroGroups(s, zeroGroups, zeroGroupIndex);

        List<Integer> ans = new ArrayList<>();

        if (zeroGroups.isEmpty()) {
            for (int i = 0; i < queries.length; i++)
                ans.add(ones);
            return ans;
        }

        SparseTable st = new SparseTable(getZeroMergeLengths(zeroGroups));

        for (int[] q : queries) {
            int l = q[0], r = q[1];

            int left = zeroGroupIndex[l] == -1 ? -1
                    : zeroGroups.get(zeroGroupIndex[l]).length
                    - (l - zeroGroups.get(zeroGroupIndex[l]).start);

            int right = zeroGroupIndex[r] == -1 ? -1
                    : r - zeroGroups.get(zeroGroupIndex[r]).start + 1;

            int startAdj = zeroGroupIndex[l] + 1;
            int endAdj = (s.charAt(r) == '1'
                    ? zeroGroupIndex[r]
                    : zeroGroupIndex[r] - 1) - 1;

            int res = ones;

            if (s.charAt(l) == '0' && s.charAt(r) == '0'
                    && zeroGroupIndex[l] + 1 == zeroGroupIndex[r]) {
                res = Math.max(res, ones + left + right);
            } else if (startAdj <= endAdj) {
                res = Math.max(res, ones + st.query(startAdj, endAdj));
            }

            if (s.charAt(l) == '0'
                    && zeroGroupIndex[l] + 1 <=
                    (s.charAt(r) == '1'
                            ? zeroGroupIndex[r]
                            : zeroGroupIndex[r] - 1)) {
                res = Math.max(res,
                        ones + left + zeroGroups.get(zeroGroupIndex[l] + 1).length);
            }

            if (s.charAt(r) == '0'
                    && zeroGroupIndex[l] < zeroGroupIndex[r] - 1) {
                res = Math.max(res,
                        ones + right + zeroGroups.get(zeroGroupIndex[r] - 1).length);
            }

            ans.add(res);
        }

        return ans;
    }

    private void getZeroGroups(String s, List<Group> groups, int[] idx) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                if (i > 0 && s.charAt(i - 1) == '0') {
                    groups.get(groups.size() - 1).length++;
                } else {
                    groups.add(new Group(i, 1));
                }
            }
            idx[i] = groups.size() - 1;
        }
    }

    private int[] getZeroMergeLengths(List<Group> groups) {
        int[] arr = new int[Math.max(0, groups.size() - 1)];
        for (int i = 0; i + 1 < groups.size(); i++) {
            arr[i] = groups.get(i).length + groups.get(i + 1).length;
        }
        return arr;
    }
}