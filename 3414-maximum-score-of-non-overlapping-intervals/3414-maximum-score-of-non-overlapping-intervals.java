import java.util.*;

class Solution {

    static class Interval {
        int start;
        int end;
        int weight;
        int index;

        Interval(int start, int end, int weight, int index) {
            this.start = start;
            this.end = end;
            this.weight = weight;
            this.index = index;
        }
    }

    static class State {
        long score;
        List<Integer> indices;

        State(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        // Store intervals with their original index
        Interval[] arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Interval(
                intervals.get(i).get(0),
                intervals.get(i).get(1),
                intervals.get(i).get(2),
                i
            );
        }

        // Sort by ending time
        Arrays.sort(arr, (a, b) -> a.end - b.end);

        // prev[i] = number of intervals before i
        // that are completely non-overlapping with i
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            prev[i] = findPrevious(arr, i);
        }

        // dp[i][k]:
        // best answer using first i intervals
        // and choosing at most k intervals
        State[][] dp = new State[n + 1][5];

        // With 0 intervals, score is 0
        for (int k = 0; k <= 4; k++) {
            dp[0][k] = new State(0, new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) {

            Interval current = arr[i - 1];

            for (int k = 0; k <= 4; k++) {

                // Option 1: Don't take current interval
                State notTake = dp[i - 1][k];

                State best = notTake;

                // Option 2: Take current interval
                if (k > 0) {

                    State previous = dp[prev[i - 1]][k - 1];

                    List<Integer> newIndices =
                        new ArrayList<>(previous.indices);

                    newIndices.add(current.index);

                    // Sort because answer must be compared
                    // lexicographically by original indices
                    Collections.sort(newIndices);

                    State take = new State(
                        previous.score + current.weight,
                        newIndices
                    );

                    best = better(take, notTake);
                }

                dp[i][k] = best;
            }
        }

        // dp[n][4] = best answer using at most 4 intervals
        List<Integer> answer = dp[n][4].indices;

        int[] result = new int[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }

    // Find how many intervals before i have end < current start
    private int findPrevious(Interval[] arr, int i) {

        int left = 0;
        int right = i - 1;
        int answer = 0;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (arr[mid].end < arr[i].start) {
                answer = mid + 1;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    // Return the better of two states
    private State better(State a, State b) {

        if (a.score > b.score) {
            return a;
        }

        if (b.score > a.score) {
            return b;
        }

        // Same score -> lexicographically smaller indices
        if (isLexicographicallySmaller(a.indices, b.indices)) {
            return a;
        }

        return b;
    }

    private boolean isLexicographicallySmaller(
        List<Integer> a,
        List<Integer> b
    ) {

        int size = Math.min(a.size(), b.size());

        for (int i = 0; i < size; i++) {

            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }

        // If one is a prefix of the other,
        // the shorter one is lexicographically smaller
        return a.size() < b.size();
    }
}