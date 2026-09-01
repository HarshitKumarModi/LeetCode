import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        // litterId[i][j] tells which bit belongs to litter at (i,j)
        int[][] litterId = new int[m][n];

        int startRow = 0;
        int startCol = 0;
        int litterCount = 0;

        // Find S and assign IDs to L cells
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startRow = i;
                    startCol = j;
                }

                if (ch == 'L') {
                    litterId[i][j] = litterCount;
                    litterCount++;
                }
            }
        }

        // No litter
        if (litterCount == 0) {
            return 0;
        }

        int totalMasks = 1 << litterCount;

        /*
            visited[row][col][energy][mask]

            true = this state has already been visited
        */
        boolean[][][][] visited =
            new boolean[m][n][energy + 1][totalMasks];

        // Initially all litter is remaining
        int initialMask = (1 << litterCount) - 1;

        Queue<int[]> queue = new LinkedList<>();

        // row, col, current energy, mask
        queue.add(new int[] {
            startRow,
            startCol,
            energy,
            initialMask
        });

        visited[startRow][startCol][energy][initialMask] = true;

        int moves = 0;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            int size = queue.size();

            // Process one BFS level
            for (int k = 0; k < size; k++) {

                int[] state = queue.poll();

                int row = state[0];
                int col = state[1];
                int currEnergy = state[2];
                int mask = state[3];

                // All litter collected
                if (mask == 0) {
                    return moves;
                }

                // No energy -> cannot move
                if (currEnergy == 0) {
                    continue;
                }

                // Try 4 directions
                for (int d = 0; d < 4; d++) {

                    int nr = row + dr[d];
                    int nc = col + dc[d];

                    // Outside grid
                    if (nr < 0 || nr >= m ||
                        nc < 0 || nc >= n) {
                        continue;
                    }

                    // Obstacle
                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    char nextCell = classroom[nr].charAt(nc);

                    // Calculate new energy
                    int newEnergy;

                    if (nextCell == 'R') {
                        newEnergy = energy;
                    } else {
                        newEnergy = currEnergy - 1;
                    }

                    // Calculate new litter mask
                    int newMask = mask;

                    if (nextCell == 'L') {

                        int id = litterId[nr][nc];

                        newMask = newMask & ~(1 << id);
                    }

                    // Avoid visiting the same state again
                    if (!visited[nr][nc][newEnergy][newMask]) {

                        visited[nr][nc][newEnergy][newMask] = true;

                        queue.add(new int[] {
                            nr,
                            nc,
                            newEnergy,
                            newMask
                        });
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}