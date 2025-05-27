package com.example.tictactoegame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tictactoegame.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val xButton = findViewById<Button>(R.id.btnX)
        val oButton = findViewById<Button>(R.id.btn0)
        xButton.setOnClickListener {
            startGame("X")
        }
        oButton.setOnClickListener {
            startGame("O")
        }
    }

    private fun startGame(firstPlayer: String) {
        val intent = Intent(this, GridActivity::class.java)
        intent.putExtra("firstPlayer", firstPlayer)
        startActivity(intent)
    }
}