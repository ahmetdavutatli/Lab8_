package com.example.lab8_;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class BoardPresenter implements BoardListener{
    private BoardView view;
    private Board board;
    private List<CellClickListener> cellClickListeners = new ArrayList<>();

    public BoardPresenter(BoardView boardView) {
        view = boardView;
        board = new Board(this);
    }

    private void move(byte row, byte col) {
        board.move(row, col);
    }

    public void checkWinner() {
        board.checkWinner();
    }


    public void addCellClickListener(CellClickListener listener) {
        cellClickListeners.add(listener);
    }


    @Override
    public void playedAt(byte player, byte row, byte col) {
        if (player == BoardListener.PLAYER_1) {
            view.putSymbol(BoardView.PLAYER_1_SYMBOL, row, col);
        } else if(player == BoardListener.PLAYER_2){
            view.putSymbol(BoardView.PLAYER_2_SYMBOL, row, col);
        }
    }

    @Override
    public void gameEnded(byte winner) {
        switch (winner){
            case BoardListener.NO_ONE:
                view.gameEnded(BoardView.draw);
                break;
            case BoardListener.PLAYER_1:
                view.gameEnded(BoardView.PLAYER_1_WINNER);
                break;
            case BoardListener.PLAYER_2:
                view.gameEnded(BoardView.PLAYER_2_WINNER);
                break;
        }
    }

    @Override
    public void invalidPlay(byte row, byte col) {
        view.invalidPlay(row, col);
    }

    static class CellClickListener implements View.OnClickListener{
        BoardPresenter presenter;
        byte row;
        byte col;

        public CellClickListener(BoardPresenter presenter, byte row, byte col) {
            this.presenter = presenter;
            this.row = row;
            this.col = col;
        }

        public void onClick(View view) {
            Log.d("CellClickListener", "at " + row + ", " + col);
            presenter.move(row, col);
            presenter.checkWinner();
        }
    }
}