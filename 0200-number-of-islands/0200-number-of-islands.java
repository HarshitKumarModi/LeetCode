class Solution{
    public int numIslands(char[][] grid){
        int rows = grid.length;
        int cols = grid[0].length;

        int count = 0;

        int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };


        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(grid[r][c] == '1'){
                    count++;
                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[]{r, c});

                    grid[r][c] = '0';

                    while(!queue.isEmpty()){
                        int[] current = queue.poll();

                        int row = current[0];
                        int col = current[1];

                        for(int[] direction : directions){
                            int newRow = row + direction[0];
                            int newCol = col + direction[1];

                            if(newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && grid[newRow][newCol] == '1'){
                                queue.add(new int[]{newRow, newCol});
                                grid[newRow][newCol] = '0';
                            }
                        }

                    }
                }
            }
        }

        return  count;
    }
}