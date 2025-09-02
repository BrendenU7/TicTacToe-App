package a4.tictactoe.model;


 //create tictactoe game board and
  //Track marker placement.

public class TicTacToeBoard {
    private Marker[][] board;

    //create ttt board
    public TicTacToeBoard() {
        board = new Marker[3][3];
        // All values are initialized to null by default
    }

    public boolean addMarker(int rowNum, int colNum, Marker marker) {
        if (board[rowNum][colNum] == null) {
            board[rowNum][colNum] = marker;
            return true;
        }
        return false;
    }


    public Marker getMarker(int rowNum, int colNum) {
        return board[rowNum][colNum];
    }


    public Position[] winningPositions(Marker marker) {
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == marker && board[row][1] == marker && board[row][2] == marker) {
                return new Position[] {
                        new Position(row, 0),
                        new Position(row, 1),
                        new Position(row, 2)
                };
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == marker && board[1][col] == marker && board[2][col] == marker) {
                return new Position[] {
                        new Position(0, col),
                        new Position(1, col),
                        new Position(2, col)
                };
            }
        }

        // Check diagonal from top left to bottom right
        if (board[0][0] == marker && board[1][1] == marker && board[2][2] == marker) {
            return new Position[] {
                    new Position(0, 0),
                    new Position(1, 1),
                    new Position(2, 2)
            };
        }

        // Check diagonal from top right to bottomleft
        if (board[0][2] == marker && board[1][1] == marker && board[2][0] == marker) {
            return new Position[] {
                    new Position(0, 2),
                    new Position(1, 1),
                    new Position(2, 0)
            };
        }

        return null; // No winning positions found
    }


    public int numUnplayedSquares() {
        int count = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == null) {
                    count++;
                }
            }
        }
        return count;
    }


    public void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = null;
            }
        }
    }
}