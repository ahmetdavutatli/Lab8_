package com.example.lab8_;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements BoardView {

    BoardPresenter presenter;
    TableLayout boardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new BoardPresenter(this);
        boardView = findViewById(R.id.board);

        for (byte row = 0; row < 3; row ++) {
            TableRow tableRow = (TableRow) boardView.getChildAt(row);
            for (byte col = 0; col < 3; col ++) {
                Button button = (Button) tableRow.getChildAt(col);
                BoardPresenter.CellClickListener clickListener = new BoardPresenter.CellClickListener(presenter, row, col);
                button.setOnClickListener(clickListener);
                presenter.addCellClickListener(clickListener);
            }
        }
    }

    @Override
    public void newGame() {
        TableLayout boardView = findViewById(R.id.board);
        for (int row = 0; row < 3; row ++) {
            TableRow tableRow = (TableRow) boardView.getChildAt(row);
            for (int col = 0; col < 3; col ++) {
                Button button = (Button) tableRow.getChildAt(col);
                button.setText("");
                button.setEnabled(true);
            }
        }
    }

    @Override
    public void putSymbol(char symbol, byte row, byte col) {
        TableRow tableRow = (TableRow) boardView.getChildAt(row);
        Button button = (Button) tableRow.getChildAt(col);
        button.setText(Character.toString(symbol));
    }

    @Override
    public void gameEnded(byte winner) {
        for (int row = 0; row < 3; row ++) {
            TableRow tableRow = (TableRow) boardView.getChildAt(row);
            for (int col = 0; col < 3; col ++) {
                Button button = (Button) tableRow.getChildAt(col);
                button.setText("");
                button.setEnabled(false);
            }
        }

        switch (winner){
            case BoardView.draw:
                Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
                break;
            case BoardView.PLAYER_1_WINNER:
                Toast.makeText(this, "Player 1 wins", Toast.LENGTH_SHORT).show();
                break;
            case BoardView.PLAYER_2_WINNER:
                Toast.makeText(this, "Player 2 wins", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void invalidPlay(byte row, byte col) {
        Toast.makeText(this, "Invalid play at " + row + ", " + col, Toast.LENGTH_SHORT).show();
    }
}