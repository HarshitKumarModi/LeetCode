class Solution {
    public int minimumEffortPath(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        int[][] effort = new int[rows][cols];

        for(int i = 0; i<rows; i++){
            Arrays.fill(effort[i], Integer.MAX_VALUE);
        }

        effort[0][0] = 0;

        int[][] directions = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
        };

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);

        pq.add(new int[]{0, 0, 0});

        while(!pq.isEmpty()){
            int[] current = pq.poll();

            int currentEffort = current[0];
            int row = current[1];
            int col = current[2];

            if(currentEffort > effort[row][col]){
                continue;
            }

            if(row == rows-1 && col == cols-1){
                return currentEffort;
            }

            for(int[] direction : directions){
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if(newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols){
                    continue;
                }

                int edgeEffort = Math.abs(heights[row][col] - heights[newRow][newCol]);

                int newEffort = Math.max(currentEffort, edgeEffort);

                if(newEffort < effort[newRow][newCol]){
                    effort[newRow][newCol] = newEffort;

                    pq.add(new int[]{newEffort, newRow, newCol});
                }
            }
        }

        return 0;
    }
}