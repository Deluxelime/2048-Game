import java.util.Arrays;

public class Board {
    // 2D array to represent the game board
    private final int[][] grid;

    public Board() {
        grid = new int[4][4];
    }
    public void printBoard() {
        for (int i = 0; i < 4; i++) {
            System.out.println(Arrays.toString(grid[i]));
        }
    }
    public void setTile(int row, int col, int value) {
        grid[row][col] = value;
    }
    public int getTile(int row, int col) {
        return grid[row][col];
    }
    // Check if the board is full
    public boolean isFull() {
        // Check if there are any empty tiles (0) in the grid
        // Iterate through each row of the grid
        for (int i = 0; i < 4; i++) {
            // Check each column for empty tiles
            for (int j = 0; j < 4; j++) {
                // If an empty tile is found, the board is not full
                if (grid[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    // Spawn a new tile (2 or 4) in a random empty position on the board
    public void spawnTile() {
        int row, col;
        // If the board is not full, spawn a new tile
        if (!isFull()) {
            do {
                // Generate random row and column indices between 0 and 3
                row = (int) (Math.random() * 4);
                col = (int) (Math.random() * 4);
                // Repeat until an empty tile (0) is found
            } while (grid[row][col] != 0);
            // Set the value of the empty tile to either 2 or 4, with a higher probability for 2
            grid[row][col] = Math.random() < 0.8 ? 2 : 4;
        }
    }
    public boolean canMove() {
        // Check if the board is full
        if (isFull()) {
            // If the board is full, check for possible moves by looking for adjacent tiles with the same value
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    // Check if the current tile can be merged with its right neighbor
                    if (j < 3 && grid[i][j] == grid[i][j + 1]) {
                        return true;
                    }
                    // Check if the current tile can be merged with its bottom neighbor
                    if (i < 3 && grid[i][j] == grid[i + 1][j]) {
                        return true;
                    }
                }
            }
            // If no possible moves are found, return false
            return false;
        }
        // If the board is not full, return true (moves are possible)
        return true;
    }
    // Move all tiles to the left and merge adjacent tiles with the same value
    public void moveLeft() {
        // Iterate through each row of the grid
        for (int i = 0; i < 4; i++) {
            // Create a new row to store the merged values
            int[] newRow = new int[4];
            // Index to keep track of the position in the new row
            int index = 0;
            // Move non-zero tiles to the new row
            // Iterate through each column in the current row
            for (int j = 0; j < 4; j++) {
                // If the current tile is not empty (not 0), add it to the new row
                if (grid[i][j] != 0) {
                    // Add the non-zero tile to the new row and increment the index
                    newRow[index++] = grid[i][j];
                }
            }
            // Merge adjacent tiles with the same value in the new row
            for (int j = 0; j < 3; j++) {
                // If the current tile is not empty and is equal to the next tile, merge them
                if (newRow[j] != 0 && newRow[j] == newRow[j + 1]) {
                    // Double the value of the current tile and set the next tile to 0
                    newRow[j] *= 2;
                    newRow[j + 1] = 0;
                }
            }
            // Move the merged tiles back to the original grid and fill the remaining positions with 0
            index = 0;
            // Move the merged tiles back to the original grid and fill the remaining positions with 0
            for (int j = 0; j < 4; j++) {
                // If the current tile in the new row is not empty, move it back to the original grid
                if (newRow[j] != 0) {
                    // Move the non-zero tile back to the original grid and increment the index
                    grid[i][index++] = newRow[j];
                }
            }
            // Fill the remaining positions in the original grid with 0
            while (index < 4) {
                grid[i][index++] = 0;
            }
        }
    }
    public void moveRight() {
        // Iterate through each row of the grid
        for (int i = 0; i < 4; i++) {
            // Create a new row to store the merged values
            int[] newRow = new int[4];
            // Index to keep track of the position in the new row
            int index = 3;
            // Move non-zero tiles to the new row
            // Iterate through each column in the current row in reverse order
            for (int j = 3; j >= 0; j--) {
                // If the current tile is not empty (not 0), add it to the new row
                if (grid[i][j] != 0) {
                    // Add the non-zero tile to the new row and decrement the index
                    newRow[index--] = grid[i][j];
                }
            }
            // Merge adjacent tiles with the same value in the new row
            for (int j = 3; j > 0; j--) {
                // If the current tile is not empty and is equal to the previous tile, merge them
                if (newRow[j] != 0 && newRow[j] == newRow[j - 1]) {
                    // Double the value of the current tile and set the previous tile to 0
                    newRow[j] *= 2;
                    newRow[j - 1] = 0;
                }
            }
            // Move the merged tiles back to the original grid and fill the remaining positions with 0
            index = 3;
            for (int j = 3; j >= 0; j--) {
                if (newRow[j] != 0) {
                    grid[i][index--] = newRow[j];
                }
            }
            while (index >= 0) {
                grid[i][index--] = 0;
            }
        }
    }
    public void moveUp() {
        // Iterate through each column of the grid
        for (int j = 0; j < 4; j++) {
            // Create a new column to store the merged values
            int[] newCol = new int[4];
            // Index to keep track of the position in the new column
            int index = 0;
            // Move non-zero tiles to the new column
            for (int i = 0; i < 4; i++) {
                if (grid[i][j] != 0) {
                    newCol[index++] = grid[i][j];
                }
            }
            // Merge adjacent tiles with the same value in the new column
            for (int i = 0; i < 3; i++) {
                if (newCol[i] != 0 && newCol[i] == newCol[i + 1]) {
                    newCol[i] *= 2;
                    newCol[i + 1] = 0;
                }
            }
            // Move the merged tiles back to the original grid and fill the remaining positions with 0
            index = 0;
            for (int i = 0; i < 4; i++) {
                if (newCol[i] != 0) {
                    grid[index++][j] = newCol[i];
                }
            }
            while (index < 4) {
                grid[index++][j] = 0;
            }
        }
    }
    public void moveDown() {
        // Iterate through each column of the grid
        for (int j = 0; j < 4; j++) {
            // Create a new column to store the merged values
            int[] newCol = new int[4];
            // Index to keep track of the position in the new column
            int index = 3;
            // Move non-zero tiles to the new column
            for (int i = 3; i >= 0; i--) {
                // If the current tile is not empty (not 0), add it to the new column
                if (grid[i][j] != 0) {
                    // Add the non-zero tile to the new column and decrement the index
                    newCol[index--] = grid[i][j];
                }
            }
            // Merge adjacent tiles with the same value in the new column
            for (int i = 3; i > 0; i--) {
                if (newCol[i] != 0 && newCol[i] == newCol[i - 1]) {
                    newCol[i] *= 2;
                    newCol[i - 1] = 0;
                }
            }
            // Move the merged tiles back to the original grid and fill the remaining positions with 0
            index = 3;
            // Move the merged tiles back to the original grid and fill the remaining positions with 0
            for (int i = 3; i >= 0; i--) {
                if (newCol[i] != 0) {
                    // Move the non-zero tile back to the original grid and decrement the index
                    grid[index--][j] = newCol[i];
                }
            }
            // Fill the remaining positions in the original grid with 0
            while (index >= 0) {
                grid[index--][j] = 0;
            }
        }
    }
    public boolean hasWon() {
        // Check if any tile has reached the value of 2048
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[i][j] == 2048) {
                    System.out.println("You win!");
                    return true;
                }
            }
        }
        return false;
    }
    public void reset() {
        // Reset the board by setting all tiles to 0
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                grid[i][j] = 0;
            }
        }
        // Spawn two new tiles on the board
        spawnTile();
        spawnTile();
    }
}
