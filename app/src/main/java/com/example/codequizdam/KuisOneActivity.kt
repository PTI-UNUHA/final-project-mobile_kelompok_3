package com.example.codequizdam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class KuisOneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_quiz)

        val btnKunciJawaban = findViewById<Button>( R.id.btnKunciJawaban)

        btnKunciJawaban.setOnClickListener {
            val intent = Intent( this, KuisTwoActivity::class.java)
            startActivity(intent)
        }
    }
}