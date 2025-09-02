package a4.tictactoe.view;

import android.app.Activity;
import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;

import a4.tictactoe.R;
import a4.tictactoe.model.Marker;
import a4.tictactoe.model.Position;


public class TicTacToeView {
    private final Activity activity;
    private final Button[][] buttons;
    private final TextView directionsTextView;
    private final TextView xWinsTextView;
    private final TextView oWinsTextView;
    private final TextView drawsTextView;
    private final Button newGameButton;

    private final int BLUE_COLOR = Color.rgb(0, 0, 255);
    private final int RED_COLOR = Color.rgb(255, 0, 0);


    public TicTacToeView(Activity activity) {
        this.activity = activity;

        // Initialize TextViews
        directionsTextView = activity.findViewById(R.id.directions);
        xWinsTextView = activity.findViewById(R.id.x_wins);
        oWinsTextView = activity.findViewById(R.id.o_wins);
        drawsTextView = activity.findViewById(R.id.draws);

        // Initialize New Game button
        newGameButton = activity.findViewById(R.id.new_game);

        // Initialize board buttons
        buttons = new Button[3][3];
        buttons[0][0] = activity.findViewById(R.id.button_r0c0);
        buttons[0][1] = activity.findViewById(R.id.button_r0c1);
        buttons[0][2] = activity.findViewById(R.id.button_r0c2);
        buttons[1][0] = activity.findViewById(R.id.button_r1c0);
        buttons[1][1] = activity.findViewById(R.id.button_r1c1);
        buttons[1][2] = activity.findViewById(R.id.button_r1c2);
        buttons[2][0] = activity.findViewById(R.id.button_r2c0);
        buttons[2][1] = activity.findViewById(R.id.button_r2c1);
        buttons[2][2] = activity.findViewById(R.id.button_r2c2);

        // Set initial button colors
        resetButtonColors();
    }


    public Button getButton(int row, int col) {
        return buttons[row][col];
    }


    public Button getNewGameButton() {
        return newGameButton;
    }


    public void updateDirections(int messageResId) {
        directionsTextView.setText(messageResId);
    }


    public void updateButton(int row, int col, Marker marker) {
        buttons[row][col].setText(marker != null ? marker.toString() : "");
    }


    public void updateXWins(int count) {
        xWinsTextView.setText(activity.getString(R.string.x_wins_count, count));
    }


    public void updateOWins(int count) {
        oWinsTextView.setText(activity.getString(R.string.o_wins_count, count));
    }


    public void updateDraws(int count) {
        drawsTextView.setText(activity.getString(R.string.draws_count, count));
    }


    public void resetButtonColors() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setBackgroundColor(BLUE_COLOR);
            }
        }
    }


    public void highlightWinningPositions(Position[] positions) {
        if (positions != null) {
            for (Position pos : positions) {
                buttons[pos.getRowNum()][pos.getColNum()].setBackgroundColor(RED_COLOR);
            }
        }
    }


    public void clearButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
    }
}