package com.example.codequizdam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_result)

        val btnulangikuis = findViewById<Button>( R.id.btnUlangi)

        btnulangikuis.setOnClickListener {
            val intent = Intent( this, KuisOneActivity::class.java)
            startActivity(intent)
        }

        val btnjawaban = findViewById<Button>( R.id.btnJawaban)

        btnjawaban.setOnClickListener {
            val intent = Intent( this, JawabanActivity::class.java)
            startActivity(intent)
        }

        val btnleaderboard = findViewById<Button>( R.id.btnLeaderboard)

        btnleaderboard.setOnClickListener {
            val intent = Intent( this, LeaderboardActivity::class.java)
            startActivity(intent)
        }
    }
}