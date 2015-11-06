---
layout: post
title: Leetcode[79] Word Search
---
###Task
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.

###Python

```python

class Solution(object):
    def exist(self, board, word):
        """
        :type board: List[List[str]]
        :type word: str
        :rtype: bool
        """
        row = len(board)
        col = len(board[0])
        used = [[False for j in range(col)] for i in range(row)]
        for i in range(row):
            for j in range(col):
                if self.find(board, i, j, word, row, col, 0, used):
                    return True
        return False
        
    def find(self, board, i, j, word, row, col, cur, used):
        if cur >= len(word):
            return True
        if i < 0 or i >= row or j < 0 or j >= col:
            return False
        if board[i][j] != word[cur] or used[i][j]:
            return False
        used[i][j] = True
        if self.find(board, i + 1, j, word, row, col, cur + 1, used):
            return True
        if self.find(board, i - 1, j, word, row, col, cur + 1, used):
            return True
        if self.find(board, i, j + 1, word, row, col, cur + 1, used):
            return True
        if self.find(board, i, j - 1, word, row, col, cur + 1, used):
            return True
        used[i][j] = False
        
            
```

###Java

```java

public class Solution {
	public boolean helper(char[][] board, String word, int pos, int i, int j, int row, int col) {
		if (i < 0 || j < 0 || i >= row || j >= col) {
			return false;
		}

		if (board[i][j] == word.charAt(pos)) {
			char temp = board[i][j];
			board[i][j] = '#';
			if (pos == word.length() - 1) {
				return true;
			}
			if (helper(board, word, pos + 1, i + 1, j, row, col)
				|| helper(board, word, pos + 1, i - 1, j, row, col)
				|| helper(board, word, pos + 1, i, j + 1, row, col)
				|| helper(board, word, pos + 1, i, j - 1, row, col)) {
				return true;
			}
			board[i][j] = temp;
		}



		return false;  
	}

    public boolean exist(char[][] board, String word) {
        //edge cases
        if (board == null || board.length == 0 || board[0].length ==0) {
        	return false;
        }

        int row = board.length;
        int col = board[0].length;

        for (int i = 0; i < row; i++) {
        	for (int j = 0; j < col; j++) {
        		if (helper(board, word, 0, i, j, row, col)) {
        			return true;
        		}
        	}
        }

        return false;
    }

}

```

###Points
* Mark the position already __visited__, either with extra boolean array or change the original board
* DFS / backtracking