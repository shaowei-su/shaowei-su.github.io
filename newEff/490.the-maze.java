/*
 * [490] The Maze
 *
 * https://leetcode.com/problems/the-maze/description/
 *
 * algorithms
 * Medium (45.36%)
 * Total Accepted:    24.8K
 * Total Submissions: 54.7K
 * Testcase Example:  '[[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]]\n[0,4]\n[4,4]'
 *
 * There is a ball in a maze with empty spaces and walls. The ball can go
 * through empty spaces by rolling up, down, left or right, but it won't stop
 * rolling until hitting a wall. When the ball stops, it could choose the next
 * direction.
 * 
 * Given the ball's start position, the destination and the maze, determine
 * whether the ball could stop at the destination.
 * 
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means
 * the empty space. You may assume that the borders of the maze are all walls.
 * The start and destination coordinates are represented by row and column
 * indexes.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input 1: a maze represented by a 2D array
 * 
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 * 
 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * Input 3: destination coordinate (rowDest, colDest) = (4, 4)
 * 
 * Output: true
 * 
 * Explanation: One possible way is : left -> down -> left -> down -> right ->
 * down -> right.
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input 1: a maze represented by a 2D array
 * 
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 * 
 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * Input 3: destination coordinate (rowDest, colDest) = (3, 2)
 * 
 * Output: false
 * 
 * Explanation: There is no way for the ball to stop at the destination.
 * 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * There is only one ball and one destination in the maze.
 * Both the ball and the destination exist on an empty space, and they will not
 * be at the same position initially.
 * The given maze does not contain border (like the red rectangle in the
 * example pictures), but you could assume the border of the maze are all
 * walls.
 * The maze contains at least 2 empty spaces, and both the width and height of
 * the maze won't exceed 100.
 * 
 * 
 */
class Solution {
    private static int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        //direction: 1: up 2: down 3: left 4: right
        Set<String> visited = new HashSet<>();
        return dfs(maze, start, destination, 1, visited) || dfs(maze, start, destination, 2, visited) || dfs(maze, start, destination, 3, visited) || dfs(maze, start, destination, 4, visited);
    }

    public boolean dfs(int[][] maze, int[] start, int[] dest, int dir, Set<String> visited) {
        int r = maze.length;
        int c = maze[0].length;
        if (start[0] < 0 || start[0] >= r || start[1] < 0 || start[1] >= c) {
            return false;
        }
        if (maze[start[0]][start[1]] == 1) {
            return false;
        }
        if (visited.contains(start[0] + "->" + start[1] + "->" + dir)) {
            return false;
        }
        visited.add(start[0] + "->" + start[1] + "->" + dir);
        if (isWall(maze, start, dir) && start[0] == dest[0] && start[1] == dest[1]) {
            return true;
        }
        if (isWall(maze, start, dir)) {
            if (dir == 1 || dir == 2) {
                return dfs(maze, new int[] {start[0], start[1] - 1}, dest, 3, visited) || dfs(maze, new int[] {start[0], start[1] + 1}, dest, 4, visited);
            } else if (dir == 3 || dir == 4) {
                return dfs(maze, new int[] {start[0] - 1, start[1]}, dest, 1, visited) || dfs(maze, new int[] {start[0] + 1, start[1]}, dest, 2, visited);
            }
        } else {
            switch(dir) {
                case 1:
                    return dfs(maze, new int[] {start[0] - 1, start[1]}, dest, 1, visited);
                case 2:
                    return dfs(maze, new int[] {start[0] + 1, start[1]}, dest, 2, visited);
                case 3:
                    return dfs(maze, new int[] {start[0], start[1] - 1}, dest, 3, visited);
                case 4:
                    return dfs(maze, new int[] {start[0], start[1] + 1}, dest, 4, visited);
                default:
                    return false;
            }
        
        }
        return false;
    }

    public boolean isWall(int[][] maze, int[] start, int dir) {
        int r = maze.length;
        int c = maze[0].length;
        switch(dir) {
            case 1:
                if (start[0] == 0) {
                    return true;
                } else {
                    if (maze[start[0] - 1][start[1]] == 1) {
                        return true;
                    } else {
                        return false;
                    }
                }
            case 2:
                if (start[0] == r - 1) {
                    return true;
                } else {
                    if (maze[start[0] + 1][start[1]] == 1) {
                        return true;
                    } else {
                        return false;
                    }
                }
            case 3:
                if (start[1] == 0) {
                    return true;
                } else {
                    if (maze[start[0]][start[1] - 1] == 1) {
                        return true;
                    } else {
                        return false;
                    }
                }
            case 4:
                if (start[1] == c - 1) {
                    return true;
                } else {
                    if (maze[start[0]][start[1] + 1] == 1) {
                        return true;
                    } else {
                        return false;
                    }
                }
            default:
                return false;

        }
    }
                
}
