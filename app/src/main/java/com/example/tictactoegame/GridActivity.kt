package com.example.tictactoegame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GridActivity : AppCompatActivity() {

    private var currentPlayer = "X"
    private val board = Array(3) { arrayOfNulls<String>(3) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)

        currentPlayer = intent.getStringExtra("firstPlayer") ?: "X"
        setupBoard()
    }

    private fun setupBoard() {
        val buttons = listOf<Button>(
            findViewById(R.id.btn00), findViewById(R.id.btn01), findViewById(R.id.btn02),
            findViewById(R.id.btn10), findViewById(R.id.btn11), findViewById(R.id.btn12),
            findViewById(R.id.btn20), findViewById(R.id.btn21), findViewById(R.id.btn22)
        )

        buttons.forEachIndexed { index, button ->
            val row = index / 3
            val col = index % 3
            button.setOnClickListener {
                if (button.text.isEmpty()) {
                    button.text = currentPlayer
                    board[row][col] = currentPlayer
                    if (checkWinner()) {
                        val intent = Intent(this, WinnerActivity::class.java)
                        intent.putExtra("winner", currentPlayer)
                        startActivity(intent)
                        finish()
                    } else if (isDraw()) {
                        startActivity(Intent(this, DrawActivity::class.java))
                        finish()
                    } else {
                        currentPlayer = if (currentPlayer == "X") "O" else "X"
                    }
                }
            }
        }
    }

    private fun checkWinner(): Boolean {
        // Simple winner check
        for (i in 0..2) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) return true
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) return true
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) return true
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) return true
        return false
    }

    private fun isDraw(): Boolean {
        return board.all { row -> row.all { it != null } }
    }
}