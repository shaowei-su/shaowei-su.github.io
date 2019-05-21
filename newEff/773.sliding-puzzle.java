/*
 * @lc app=leetcode id=773 lang=java
 *
 * [773] Sliding Puzzle
 *
 * https://leetcode.com/problems/sliding-puzzle/description/
 *
 * algorithms
 * Hard (51.13%)
 * Total Accepted:    14.4K
 * Total Submissions: 27.8K
 * Testcase Example:  '[[1,2,3],[4,0,5]]'
 *
 * On a 2x3 board, there are 5 tiles represented by the integers 1 through 5,
 * and an empty square represented by 0.
 * 
 * A move consists of choosing 0 and a 4-directionally adjacent number and
 * swapping it.
 * 
 * The state of the board is solved if and only if the board is
 * [[1,2,3],[4,5,0]].
 * 
 * Given a puzzle board, return the least number of moves required so that the
 * state of the board is solved. If it is impossible for the state of the board
 * to be solved, return -1.
 * 
 * Examples:
 * 
 * 
 * Input: board = [[1,2,3],[4,0,5]]
 * Output: 1
 * Explanation: Swap the 0 and the 5 in one move.
 * 
 * 
 * 
 * Input: board = [[1,2,3],[5,4,0]]
 * Output: -1
 * Explanation: No number of moves will make the board solved.
 * 
 * 
 * 
 * Input: board = [[4,1,2],[5,0,3]]
 * Output: 5
 * Explanation: 5 is the smallest number of moves that solves the board.
 * An example path:
 * After move 0: [[4,1,2],[5,0,3]]
 * After move 1: [[4,1,2],[0,5,3]]
 * After move 2: [[0,1,2],[4,5,3]]
 * After move 3: [[1,0,2],[4,5,3]]
 * After move 4: [[1,2,0],[4,5,3]]
 * After move 5: [[1,2,3],[4,5,0]]
 * 
 * 
 * 
 * Input: board = [[3,2,4],[1,5,0]]
 * Output: 14
 * 
 * 
 * Note:
 * 
 * 
 * board will be a 2 x 3 array as described above.
 * board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].
 * 
 * 
 */
class Solution {
    public int slidingPuzzle(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sb.append(board[i][j]);
            }
        }
        String start = sb.toString();
        String end = "123450";
        int row = board.length;
        int col = board[0].length;

        Set<String> visited = new HashSet<>();
        Deque<String> queue = new LinkedList<>();
        queue.offer(start);

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
            String cur = queue.poll();
            if (cur.equals(end)) {
                return step;
            }
            int zeroInd = cur.indexOf("0");
            int x = zeroInd / 3;
            int y = zeroInd % 3;
            int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            for (int[] dir : dirs) {
                int xhat = x + dir[0];
                int yhat = y + dir[1];
                if (xhat < 0 || xhat >= row || yhat < 0 || yhat >= col) {
                    continue;
                }
                String next = swap(cur, zeroInd, xhat * 3 + yhat);
                if (visited.contains(next)) {
                    continue;
                }
                visited.add(next);
                queue.offer(next);
            }
            }
            step++;
        }
        return -1;
    }

    public String swap(String cur, int i, int j) {
        char[] ch = cur.toCharArray();
        char tmp = ch[i];
        ch[i] = ch[j];
        ch[j] = tmp;
        return new String(ch);
    }
}
