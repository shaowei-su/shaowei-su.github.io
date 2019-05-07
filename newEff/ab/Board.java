public class Board {

    public int getScore(String[] input) {
        int row = input.length;
        int col = input[0].split(" ").length;
        String[][] grid = new String[row][col];
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                grid[i][j] = input[i].split(" ")[j];
            }
        }
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!visited[i][j]) {
                    int[] cur = new int[2]; //area, points
                    dfs(cur, grid, i, j, row, col, visited);
                    res += cur[0] * cur[1];
                }
            }
        }

        return res;
    }

    public void dfs(int[] cur, String[][] grid, int i, int j, int row, int col, boolean[][] visited) {
        char type = grid[i][j].charAt(0);
        cur[0]++;
        cur[1] += (int) (grid[i][j].charAt(1) - '0');
        visited[i][j] = true;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < 0 || x >= row || y < 0 || y >= col) {
                continue;
            }
            if (visited[x][y]) {
                continue;
            }
            if (grid[x][y].charAt(0) != type) {
                continue;
            }
            dfs(cur, grid, x, y, row, col, visited);
        }
    }


    public static void main(String[] args) {
        String[] input = {"S0 W1 W1 W0 L2",
                "W0 W0 T0 T0 T0",
                "W0 W1 T0 M2 M1",
                "S0 L0 S1 S0 S0",
                "M0 R2 R0 S1 T0"};
        Board b = new Board();
        System.out.println(b.getScore(input));
    }


}
