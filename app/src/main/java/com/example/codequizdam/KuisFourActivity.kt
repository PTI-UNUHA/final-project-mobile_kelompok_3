package com.example.codequizdam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class KuisFourActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_snippet)

        val btnKunciSnippet = findViewById<Button>( R.id.btnKuncisnippet)

        btnKunciSnippet.setOnClickListener {
            val intent = Intent( this, ResultActivity::class.java)
            startActivity(intent)
        }
    }
}