package com.example.codequizdam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class KuisTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_quiz3)

        val btnKunciJawaban2 = findViewById<Button>( R.id.btnKunci)

        btnKunciJawaban2.setOnClickListener {
            val intent = Intent( this, KuisThreeActivity::class.java)
            startActivity(intent)
        }
    }
}