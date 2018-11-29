/*
 * [688] Knight Probability in Chessboard
 *
 * https://leetcode.com/problems/knight-probability-in-chessboard/description/
 *
 * algorithms
 * Medium (41.19%)
 * Total Accepted:    13.3K
 * Total Submissions: 32.2K
 * Testcase Example:  '3\n2\n0\n0'
 *
 * On an NxN chessboard, a knight starts at the r-th row and c-th column and
 * attempts to make exactly K moves. The rows and columns are 0 indexed, so the
 * top-left square is (0, 0), and the bottom-right square is (N-1, N-1).
 * 
 * A chess knight has 8 possible moves it can make, as illustrated below. Each
 * move is two squares in a cardinal direction, then one square in an
 * orthogonal direction.
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Each time the knight is to move, it chooses one of eight possible moves
 * uniformly at random (even if the piece would go off the chessboard) and
 * moves there.
 * 
 * The knight continues moving until it has made exactly K moves or has moved
 * off the chessboard. Return the probability that the knight remains on the
 * board after it has stopped moving.
 * 
 * 
 * 
 * Example:
 * 
 * 
 * Input: 3, 2, 0, 0
 * Output: 0.0625
 * Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight
 * on the board.
 * From each of those positions, there are also two moves that will keep the
 * knight on the board.
 * The total probability the knight stays on the board is 0.0625.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * N will be between 1 and 25.
 * K will be between 0 and 100.
 * The knight always initially starts on the board.
 * 
 * 
 */
class Solution {
    public double knightProbability(int N, int K, int r, int c) {
        Map<String, Double> memo = new HashMap<>();
        double pos = dfs(N, K, r, c, memo);
        return pos / Math.pow(8, K);
    }
    private double dfs(int N, int K, int r, int c, Map<String, Double> memo) {
        if (r < 0 || r >= N || c < 0 || c >= N) {
            return 0.0d;
        }
        if (memo.containsKey(K + "#" + r + "#" + c)) {
            return memo.get(K + "#" + r + "#" + c);
        }
        if (K == 0) {
            return 1.0d;
        }
        double cur = dfs(N, K - 1, r + 2, c + 1, memo) + dfs(N, K - 1, r - 2, c + 1, memo) + dfs(N, K - 1, r + 1, c + 2, memo) + dfs(N, K - 1, r - 1,  c - 2, memo) + 
            dfs(N, K - 1, r + 2, c - 1, memo) + dfs(N, K - 1, r - 2, c - 1, memo) + dfs(N, K - 1, r + 1, c - 2, memo) + dfs(N, K - 1, r - 1, c + 2, memo);
        memo.put(K + "#" + r + "#" + c, cur);
        return cur;
    }
}
