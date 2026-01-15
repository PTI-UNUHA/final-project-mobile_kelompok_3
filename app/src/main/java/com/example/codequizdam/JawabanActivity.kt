package com.example.codequizdam

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class JawabanActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_lihat_jawaban)

        val btnulangi = findViewById<Button>( R.id.btnUlangiKuis)

        btnulangi.setOnClickListener {
            val intent = Intent( this, QuizActivity::class.java)
            startActivity(intent)
        }

        val btnleaderboardjawaban = findViewById<Button>( R.id.btnLeaderboardJawaban)

        btnleaderboardjawaban.setOnClickListener {
            val intent = Intent( this, LeaderboardActivity::class.java)
            startActivity(intent)
        }
    }
}