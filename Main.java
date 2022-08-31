package tictactoe;

import java.util.Scanner;

public class Main {
    static int numberOfGames = 0;
    static boolean thereIsAResult = false;

    public static void main(String[] args) {
        char[][] board = new char[3][3];
        boolean turnOfX = true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
        printBoard(board);

        //Here Game start!
        while (!Main.thereIsAResult) {
            play(board, turnOfX ? "X" : "O");
            printBoard(board);
            turnOfX = !turnOfX;
            checkForWinner(board);
        }
    }
    public static void printBoard(char[][] board) {
        System.out.println("---------");
        System.out.print("|");
        System.out.print(" " + board[0][0] + " " + board[0][1] + " " + board[0][2] + " ");
        System.out.println("|");
        System.out.print("|");
        System.out.print(" " + board[1][0] + " " + board[1][1] + " " + board[1][2] + " ");
        System.out.println("|");
        System.out.print("|");
        System.out.print(" " + board[2][0] + " " + board[2][1] + " " + board[2][2] + " ");
        System.out.println("|");
        System.out.println("---------");
    }
    public static void play(char[][] board, String symbol) {
        Scanner scanner = new Scanner(System.in);
        boolean inputIsInvalid = true;
        int rowUser;
        int columnUser;

        while (inputIsInvalid) {
            System.out.println("Please insert your coordinates.");
            if (scanner.hasNextInt()) {
                rowUser = scanner.nextInt();
                columnUser = scanner.nextInt();
                if (rowUser < 1 || rowUser > 3 || columnUser < 1 || columnUser > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (board[rowUser - 1][columnUser - 1] != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    board[rowUser - 1][columnUser - 1] = symbol.charAt(0);
                    Main.numberOfGames++;
                    inputIsInvalid = false;
                }
            } else {
                scanner.nextLine();
                System.out.println("You should enter numbers!");
            }
        }
    }
    public static void checkForWinner(char[][] board) {
        char winner = '0';
        if (board[0][0] == board[0][1] && board[0][1] == board[0][2] && board[0][0] != ' ') {
            Main.thereIsAResult = true;
            winner = board[0][0];
        }
        if (board[1][0] == board[1][1] && board[1][1] == board[1][2] && board[1][0] != ' ') {
            Main.thereIsAResult = true;
            winner = board[1][0];
        }
        if (board[2][0] == board[2][1] && board[2][1] == board[2][2] && board[2][0] != ' ') {
            Main.thereIsAResult = true;
            winner = board[2][0];
        }
        if (board[0][0] == board[1][0] && board[1][0] == board[2][0] && board[0][0] != ' ') {
            Main.thereIsAResult = true;
            winner = board[0][0];
        }
        if (board[0][1] == board[1][1] && board[1][1] == board[2][1] && board[0][1] != ' ') {
            Main.thereIsAResult = true;
            winner = board[0][1];
        }
        if (board[0][2] == board[1][2] && board[1][2] == board[2][2] && board[0][2] != ' ') {
            Main.thereIsAResult = true;
            winner = board[0][2];
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
            Main.thereIsAResult = true;
            winner = board[0][0];
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') {
            Main.thereIsAResult = true;
            winner = board[0][2];
        }
        //Logic to print results
        if (Main.thereIsAResult) {
            System.out.println(winner + " wins");
        } else {
            if (Main.numberOfGames >= 9){
                System.out.println("Draw");
                Main.thereIsAResult = true;
            }
        }
    }
}
