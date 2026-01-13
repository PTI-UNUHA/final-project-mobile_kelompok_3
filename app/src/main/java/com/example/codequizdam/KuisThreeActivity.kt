package com.example.codequizdam

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class KuisThreeActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_quiz4)

        val btnKunciJawaban3 = findViewById<Button>( R.id.btnKuncitiga)

        btnKunciJawaban3.setOnClickListener {
            val intent = Intent( this, KuisFourActivity::class.java)
            startActivity(intent)
        }
    }
}