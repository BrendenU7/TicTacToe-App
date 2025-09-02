package a4.tictactoe

import a4.tictactoe.controller.GameController
import a4.tictactoe.model.Marker
import a4.tictactoe.model.Position
import a4.tictactoe.model.TicTacToeBoard
import a4.tictactoe.view.TicTacToeView
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var board: TicTacToeBoard? = null
    private var view: TicTacToeView? = null
    private var controller: GameController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        board = TicTacToeBoard()
        view = TicTacToeView(this)
        controller = GameController(this, board, view)


    }


    private fun testModel() {
        Log.d(TAG, "Testing TicTacToeBoard model...")


        // Test creating a new board
        val testBoard = TicTacToeBoard()
        Log.d(TAG, "New board created. Unplayed squares: " + testBoard.numUnplayedSquares())


        // Test adding markers
        var added = testBoard.addMarker(0, 0, Marker.X)
        Log.d(TAG, "Add X at (0,0): " + (if (added) "Success" else "Failed"))

        added = testBoard.addMarker(0, 0, Marker.O)
        Log.d(TAG, "Add O at (0,0) (should fail): " + (if (added) "Success" else "Failed"))


        // Test getting markers
        var m = testBoard.getMarker(0, 0)
        Log.d(TAG, "Marker at (0,0): " + (m?.toString() ?: "null"))

        m = testBoard.getMarker(1, 1)
        Log.d(TAG, "Marker at (1,1): " + (m?.toString() ?: "null"))


        // Add more markers to test win conditions
        testBoard.addMarker(1, 1, Marker.X)
        testBoard.addMarker(2, 2, Marker.X)


        // Test win condition
        var winningPositions = testBoard.winningPositions(Marker.X)
        if (winningPositions != null) {
            Log.d(TAG, "X has a winning position:")
            for (pos in winningPositions) {
                Log.d(TAG, "  Position: (" + pos.rowNum + "," + pos.colNum + ")")
            }
        } else {
            Log.d(TAG, "X does not have a winning position yet")
        }


        // Test numUnplayedSquares
        Log.d(TAG, "Unplayed squares: " + testBoard.numUnplayedSquares())


        // Test resetBoard
        testBoard.resetBoard()
        Log.d(TAG, "Board reset. Unplayed squares: " + testBoard.numUnplayedSquares())


        // Test for draw condition
        testBoard.addMarker(0, 0, Marker.X)
        testBoard.addMarker(0, 1, Marker.O)
        testBoard.addMarker(0, 2, Marker.X)
        testBoard.addMarker(1, 0, Marker.X)
        testBoard.addMarker(1, 1, Marker.O)
        testBoard.addMarker(1, 2, Marker.X)
        testBoard.addMarker(2, 0, Marker.O)
        testBoard.addMarker(2, 1, Marker.X)
        testBoard.addMarker(2, 2, Marker.O)

        Log.d(TAG, "Filled board. Unplayed squares: " + testBoard.numUnplayedSquares())


        // Check for win
        winningPositions = testBoard.winningPositions(Marker.X)
        Log.d(TAG, "X winning position: " + (if (winningPositions != null) "Yes" else "No"))

        winningPositions = testBoard.winningPositions(Marker.O)
        Log.d(TAG, "O winning position: " + (if (winningPositions != null) "Yes" else "No"))
    }

    private fun testView() {
        Log.d(TAG, "Testing TicTacToeView...")


        // Test updating directions
        view!!.updateDirections(R.string.player_x_turn)
        Log.d(TAG, "Updated directions to Player X turn")


        // Test updating buttons
        view!!.updateButton(0, 0, Marker.X)
        Log.d(TAG, "Updated button (0,0) to X")

        view!!.updateButton(1, 1, Marker.O)
        Log.d(TAG, "Updated button (1,1) to O")


        // Test updating score displays
        view!!.updateXWins(3)
        Log.d(TAG, "Updated X wins to 3")

        view!!.updateOWins(2)
        Log.d(TAG, "Updated O wins to 2")

        view!!.updateDraws(1)
        Log.d(TAG, "Updated draws to 1")


        // Test highlighting winning positions
        val winningPositions = arrayOf(
            Position(0, 0),
            Position(1, 1),
            Position(2, 2)
        )
        view!!.highlightWinningPositions(winningPositions)
        Log.d(TAG, "Highlighted diagonal winning positions")


        // Wait then, then reset colors
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        view!!.resetButtonColors()
        Log.d(TAG, "Reset button colors")


        // Test clearing buttons
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        view!!.clearButtons()
        Log.d(TAG, "Cleared all buttons")
    }

    companion object {
        private const val TAG = "TicTacToe"
    }
}