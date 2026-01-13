package com.example.codequizdam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MateriActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_materi)

        val btnDashboard = findViewById<Button>( R.id.btnDashboard)

        btnDashboard.setOnClickListener {
            val intent = Intent( this, DashboardActivity::class.java)
            startActivity(intent)
        }
    }
}