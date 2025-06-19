package firstproject;
import java.util.*;

class tictactoe {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("Player 1: X   Player 2: O");
        System.out.println("Take turns to place your marks.");
        System.out.println("Enter row and column (1-3) to make a move.");
        System.out.println();

        boolean playAgain = true;

        while (playAgain) {
            char[][] board = new char[3][3];
            for (int row = 0; row < board.length; row++) {
                Arrays.fill(board[row], ' ');
            }

            char player = 'X';
            boolean gameover = false;

            while (!gameover) {
                printBoard(board);

                System.out.println("Player " + player + ", it's your turn.");
                int row, col;

                while (true) {
                    System.out.print("Enter the row (1-3): ");
                    row = in.nextInt() - 1;
                    System.out.print("Enter the column (1-3): ");
                    col = in.nextInt() - 1;

                    if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                        break;
                    } else {
                        System.out.println("Invalid move. Try again.");
                    }
                }

                board[row][col] = player;
                gameover = haveWon(board, player);

                if (gameover) {
                    printBoard(board);
                    System.out.println("Player " + player + " has won!");
                } else if (isBoardFull(board)) {
                    printBoard(board);
                    System.out.println("It's a draw!");
                    gameover = true;
                } else {
                    player = (player == 'X') ? 'O' : 'X';
                }
            }

            System.out.print("Do you want to play another game? (yes/no): ");
            String answer = in.next();
            playAgain = answer.equalsIgnoreCase("yes");
        }

        System.out.println("Thanks for playing!");
        in.close();
    }

    public static boolean haveWon(char[][] board, char player) {
        for (int i = 0; i < 3; i++) {
            // Rows and columns
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }

        // Diagonals
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
               (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    public static boolean isBoardFull(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == ' ') return false;
            }
        }
        return true;
    }

    public static void printBoard(char[][] board) {
        System.out.println("Current Board:");
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                System.out.print(board[row][col]);
                if (col < board[row].length - 1) System.out.print(" | ");
            }
            System.out.println();
            if (row < board.length - 1) System.out.println("---------");
        }
        System.out.println();
    }
}
