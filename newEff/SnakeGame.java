import java.util.*;
/*
 * @lc app=leetcode id=353 lang=java
 *
 * [353] Design Snake Game
 *
 * https://leetcode.com/problems/design-snake-game/description/
 *
 * algorithms
 * Medium (30.01%)
 * Total Accepted:    19.2K
 * Total Submissions: 64K
 * Testcase Example:  '["SnakeGame","move","move","move","move","move","move"]\n[[3,2,[[1,2],[0,1]]],["R"],["D"],["R"],["U"],["L"],["U"]]'
 *
 * Design a Snake game that is played on a device with screen size = width x
 * height. Play the game online if you are not familiar with the game.
 * 
 * The snake is initially positioned at the top left corner (0,0) with length =
 * 1 unit.
 * 
 * You are given a list of food's positions in row-column order. When a snake
 * eats the food, its length and the game's score both increase by 1.
 * 
 * Each food appears one by one on the screen. For example, the second food
 * will not appear until the first food was eaten by the snake.
 * 
 * When a food does appear on the screen, it is guaranteed that it will not
 * appear on a block occupied by the snake.
 * 
 * Example:
 * 
 * 
 * Given width = 3, height = 2, and food = [[1,2],[0,1]].
 * 
 * Snake snake = new Snake(width, height, food);
 * 
 * Initially the snake appears at position (0,0) and the food at (1,2).
 * 
 * |S| | |
 * | | |F|
 * 
 * snake.move("R"); -> Returns 0
 * 
 * | |S| |
 * | | |F|
 * 
 * snake.move("D"); -> Returns 0
 * 
 * | | | |
 * | |S|F|
 * 
 * snake.move("R"); -> Returns 1 (Snake eats the first food and right after
 * that, the second food appears at (0,1) )
 * 
 * | |F| |
 * | |S|S|
 * 
 * snake.move("U"); -> Returns 1
 * 
 * | |F|S|
 * | | |S|
 * 
 * snake.move("L"); -> Returns 2 (Snake eats the second food)
 * 
 * | |S|S|
 * | | |S|
 * 
 * snake.move("U"); -> Returns -1 (Game over because snake collides with
 * border)
 * 
 */
class SnakeGame {
    int width;
    int height;
    LinkedList<int[]> snake;
    Set<String> foodSet;
    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
        snake = new LinkedList<>();
        snake.offerFirst(new int[] {0, 0});
        this.width = width;
        this.height = height;
        foodSet = new HashSet<>();
        for (int[] f : food) {
            foodSet.add(f[0] + "-" + f[1]);
        }
    }

    public static void main(String[] args) {
        SnakeGame sg = new SnakeGame(3, 2, new int[][] {{1, 2}, {0, 1}});
        System.out.println(sg.move("R"));
        System.out.println(sg.move("D"));
        System.out.println(sg.move("R"));
        System.out.println(sg.move("U"));
        System.out.println(sg.move("L"));
    }
    
    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int[] first = snake.peekFirst();
        int[] next = new int[2];
        switch (direction) {
            case "U":
                next[0] = first[0] - 1;
                next[1] = first[1];
                break;
            case "D":
                next[0] = first[0] + 1;
                next[1] = first[1];
                break;
            case "L":
                next[0] = first[0];
                next[1] = first[1] - 1;
                break;
            case "R":
                next[0] = first[0];
                next[1] = first[1] + 1;
                break;
            default:
                break;
        }
        if (next[0] < 0 || next[0] >= height || next[1] < 0 || next[1] >= width) {
            return -1;
        }
        System.out.println("next[0] = " + next[0] + " next[1] = " + next[1]);
        if (foodSet.contains(next[0] + "-" + next[1])) {
            snake.offerFirst(next);
        } else {
            snake.offerFirst(next);
            snake.pollLast();
        }
        System.out.println("snake = " + snake);
        return snake.size() - 1;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */
