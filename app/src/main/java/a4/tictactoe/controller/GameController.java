package a4.tictactoe.controller;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import a4.tictactoe.R;
import a4.tictactoe.model.Marker;
import a4.tictactoe.model.Position;
import a4.tictactoe.model.TicTacToeBoard;
import a4.tictactoe.view.TicTacToeView;


public class GameController {
    private  TicTacToeBoard board;
    private  TicTacToeView view;
    private  Activity activity;

    private Marker currentPlayer;
    private boolean gameEnded;
    private int xWins;
    private int oWins;
    private int draws;


    public GameController(Activity activity, TicTacToeBoard board, TicTacToeView view) {
        this.activity = activity;
        this.board = board;
        this.view = view;

        this.xWins = 0;
        this.oWins = 0;
        this.draws = 0;

        initializeGame();
        setupButtonListeners();
    }


    private void initializeGame() {
        board.resetBoard();
        view.resetButtonColors();
        view.clearButtons();
        gameEnded = false;

        // Set X as the first player initially
        currentPlayer = Marker.X;
        view.updateDirections(R.string.player_x_turn);

        // Initialize score displays
        view.updateXWins(xWins);
        view.updateOWins(oWins);
        view.updateDraws(draws);
    }


    private void setupButtonListeners() {
        // Set listeners for board buttons
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                final int finalRow = row;
                final int finalCol = col;

                view.getButton(row, col).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!gameEnded && board.addMarker(finalRow, finalCol, currentPlayer)) {
                            // Update the button text
                            view.updateButton(finalRow, finalCol, currentPlayer);

                            // Check for win
                            Position[] winningPositions = board.winningPositions(currentPlayer);
                            if (winningPositions != null) {
                                gameEnded = true;
                                view.highlightWinningPositions(winningPositions);

                                if (currentPlayer == Marker.X) {
                                    xWins++;
                                    view.updateXWins(xWins);
                                    view.updateDirections(R.string.player_x_wins);
                                } else {
                                    oWins++;
                                    view.updateOWins(oWins);
                                    view.updateDirections(R.string.player_o_wins);
                                }
                            }
                            // Check for draw
                            else if (board.numUnplayedSquares() == 0) {
                                gameEnded = true;
                                draws++;
                                view.updateDraws(draws);
                                view.updateDirections(R.string.game_draw);
                            }
                            // Switch players
                            else {
                                currentPlayer = (currentPlayer == Marker.X) ? Marker.O : Marker.X;
                                view.updateDirections(
                                        currentPlayer == Marker.X ?
                                                R.string.player_x_turn :
                                                R.string.player_o_turn
                                );
                            }
                        }
                    }
                });
            }
        }

        // Set listener for new game button
        view.getNewGameButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // For subsequent games, alternate the starting player
                if (gameEnded) {
                    currentPlayer = (currentPlayer == Marker.X) ? Marker.O : Marker.X;
                }

                initializeGame();

                // Update direction for the current player
                view.updateDirections(
                        currentPlayer == Marker.X ?
                                R.string.player_x_turn :
                                R.string.player_o_turn
                );
            }
        });
    }
}