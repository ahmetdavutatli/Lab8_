package com.example.lab8_;

public class Board {
    private static byte PLAYER_1_SYMBOL = 1;
    private static byte PLAYER_2_SYMBOL = 2;
    private boolean player1Turn = true;
    byte[][] board = new byte[3][3];


    BoardListener boardListener;

    public Board(BoardListener boardListener) {
        this.boardListener = boardListener;
    }

    public void move(byte row, byte col) {
        if (board[row][col] != 0) {
            boardListener.invalidPlay(row, col);
            return;
        }
        if (player1Turn) {
            board[row][col] = PLAYER_1_SYMBOL;
            boardListener.playedAt(BoardListener.PLAYER_1, row, col);
        } else {
            board[row][col] = PLAYER_2_SYMBOL;
            boardListener.playedAt(BoardListener.PLAYER_2, row, col);
        }
        player1Turn = !player1Turn;
    }

    public void checkWinner() {
        byte winner = BoardListener.NO_ONE;
        if (board[0][0] != 0 && board[0][0] == board[0][1] && board[0][0] == board[0][2]) {
            winner = board[0][0];
        } else if (board[1][0] != 0 && board[1][0] == board[1][1] && board[1][0] == board[1][2]) {
            winner = board[1][0];
        } else if (board[2][0] != 0 && board[2][0] == board[2][1] && board[2][0] == board[2][2]) {
            winner = board[2][0];
        } else if (board[0][0] != 0 && board[0][0] == board[1][0] && board[0][0] == board[2][0]) {
            winner = board[0][0];
        } else if (board[0][1] != 0 && board[0][1] == board[1][1] && board[0][1] == board[2][1]) {
            winner = board[0][1];
        } else if (board[0][2] != 0 && board[0][2] == board[1][2] && board[0][2] == board[2][2]) {
            winner = board[0][2];
        } else if (board[0][0] != 0 && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            winner = board[0][0];
        } else if (board[0][2] != 0 && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            winner = board[0][2];
        } else {
            boolean draw = true;
            for (byte row = 0; row < 3; row++) {
                for (byte col = 0; col < 3; col++) {
                    if (board[row][col] == 0) {
                        draw = false;
                        break;
                    }
                }
            }
            if (draw) {
                winner = BoardListener.NO_ONE;

            }
        }
        if (winner != BoardListener.NO_ONE) {
            boardListener.gameEnded(winner);
        }
    }
}