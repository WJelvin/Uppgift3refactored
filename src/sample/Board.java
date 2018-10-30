package sample;

import java.util.Random;

public class Board {

    private int puzzleHeight = 4;
    private int puzzleWidth = 4;
    int[][] board = new int[puzzleWidth][puzzleHeight];
    private int numberOfTiles = puzzleHeight * puzzleWidth;

    public Board(int puzzleHeight, int puzzleWidth) {
        this.puzzleHeight = puzzleHeight;
        this.puzzleWidth = puzzleWidth;
        createTiles();
        shuffleTiles();
    }

    private void createTiles() {
        int i = 1;
        for (int x = 0; x < puzzleWidth; x++) {
            for (int y = 0; y < puzzleHeight; y++) {
                board[x][y] = i;
                i++;
            }
        }
    }

    public void shuffleTiles() {
        Random r = new Random();
        for (int x = board.length - 1; x > 0; x--) {
            for (int y = board[x].length - 1; y > 0; y--) {
                int m = r.nextInt(x + 1);
                int n = r.nextInt(y + 1);

                int temp = board[x][y];
                board[x][y] = board[m][n];
                board[m][n] = temp;
            }
        }
    }


//    public boolean isPuzzleSolved() {
//        int counter = 0;
//        for (int i = 0; i < numberOfTiles; i++) {
//            if (board[numberOfTiles - 1] != 0) {
//                return false;
//            }
//            if (i != board[i]) {
//                return false;
//            } else {
//                counter++;
//            }
//        }
//        if (counter == numberOfTiles - 1) {
//            System.out.println("\nNumber of tiles lined up: " + counter);
//            return true;
//        }
//        return false;
//    }

    public int getPuzzleHeight() {
        return puzzleHeight;
    }

    public int getPuzzleWidth() {
        return puzzleWidth;
    }

    public int getNumberOfTiles() {
        return numberOfTiles;
    }


    public int getValue(int x, int y) {
        if (x > puzzleWidth - 1 || y > puzzleHeight - 1 || x < 0 || y < 0)
            return -1;
        return board[x][y];
    }


    public void tryToSwitch(String valueOnButton) {
        int value = Integer.parseInt(valueOnButton);
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                if (getValue(x, y) == value) {
                    //check up
                    if (getValue(x, y - 1) == numberOfTiles) {
                        System.out.println("found empty on up");
                        checkUp(x, y);
                    }
                    //check down
                    if (getValue(x, y + 1) == numberOfTiles) {
                        System.out.println("found empty on down");
                        checkDown(x, y);
                    }
                    //check left
                    if (getValue(x - 1, y) == numberOfTiles) {
                        System.out.println("found empty on left");
                        checkLeft(x, y);
                    }
                    //check right
                    if (getValue(x + 1, y) == numberOfTiles) {
                        System.out.println("found empty on right");
                        checkRight(x, y);
                    }
                }
            }
        }
    }

    public void checkUp(int x, int y) {
        if (board[x][y - 1] == numberOfTiles) {
            board[x][y - 1] = board[x][y];
            board[x][y] = 16;
        }
    }

    public void checkDown(int x, int y) {
        if (board[x][y + 1] == numberOfTiles) {
            board[x][y + 1] = board[x][y];
            board[x][y] = 16;
        }
    }

    public void checkLeft(int x, int y) {
        if (board[x - 1][y] == numberOfTiles) {
            board[x - 1][y] = board[x][y];
            board[x][y] = 16;
        }
    }

    public void checkRight(int x, int y) {
        if (board[x + 1][y] == numberOfTiles) {
            board[x + 1][y] = board[x][y];
            board[x][y] = 16;
        }
    }

    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.println("x: " + i + " y: " + j + " " + board[i][j]);
            }
        }
    }

}
