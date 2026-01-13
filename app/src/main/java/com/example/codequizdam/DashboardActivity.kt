package com.example.codequizdam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val btnCardMateri = findViewById<LinearLayout>(R.id.cardMateri)

        btnCardMateri.setOnClickListener {
            val intentMateri = Intent(this, MateriActivity::class.java)

            startActivity(intentMateri)
        }

        val btnCardAdventure = findViewById<LinearLayout>(R.id.cardAdventure)

        btnCardAdventure.setOnClickListener {
            val intentKuis = Intent (this, KuisOneActivity::class.java)

            startActivity(intentKuis)
        }

        val btnCardPrestasi = findViewById<LinearLayout>(R.id.cardPrestasi)

        btnCardPrestasi.setOnClickListener {
            val intentKuis = Intent (this, LeaderboardActivity::class.java)

            startActivity(intentKuis)
        }
    }

}
