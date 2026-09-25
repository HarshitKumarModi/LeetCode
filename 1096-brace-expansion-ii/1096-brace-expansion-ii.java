class Solution {

    public List<String> braceExpansionII(String expression) {
        Set<String> result = solve(expression, 0, expression.length() - 1);

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    private Set<String> solve(String s, int left, int right) {

        Set<String> result = new HashSet<>();

        // Find the outermost comma
        int level = 0;
        int start = left;

        for (int i = left; i <= right; i++) {

            if (s.charAt(i) == '{') {
                level++;
            } 
            else if (s.charAt(i) == '}') {
                level--;
            } 
            else if (s.charAt(i) == ',' && level == 0) {

                result.addAll(solve(s, start, i - 1));
                start = i + 1;
            }
        }

        // If we found a top-level comma
        if (start != left) {
            result.addAll(solve(s, start, right));
            return result;
        }

        // No top-level comma -> concatenation
        Set<String> current = new HashSet<>();
        current.add("");

        int i = left;

        while (i <= right) {

            Set<String> part;

            if (s.charAt(i) == '{') {

                int level2 = 1;
                int j = i + 1;

                while (level2 > 0) {
                    if (s.charAt(j) == '{') {
                        level2++;
                    } 
                    else if (s.charAt(j) == '}') {
                        level2--;
                    }
                    j++;
                }

                // Solve inside {}
                part = solve(s, i + 1, j - 2);

                i = j;

            } else {

                part = new HashSet<>();
                part.add(String.valueOf(s.charAt(i)));

                i++;
            }

            // Concatenate current with part
            Set<String> next = new HashSet<>();

            for (String a : current) {
                for (String b : part) {
                    next.add(a + b);
                }
            }

            current = next;
        }

        return current;
    }
}