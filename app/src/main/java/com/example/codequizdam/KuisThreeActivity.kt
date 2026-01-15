package com.example.codequizdam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class KuisThreeActivity : AppCompatActivity() {

    private lateinit var btnTrue: Button
    private lateinit var btnFalse: Button

    private val correctAnswer = "TRUE"
    private var selectedAnswer: String? = null

    // Variabel untuk menyimpan skor dari aktivitas sebelumnya
    private var correctAnswersFromPrevious = 0
    private var incorrectAnswersFromPrevious = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz3)

        // Menerima skor dari KuisTwoActivity
        correctAnswersFromPrevious = intent.getIntExtra("CORRECT_ANSWERS", 0)
        incorrectAnswersFromPrevious = intent.getIntExtra("INCORRECT_ANSWERS", 0)

        btnTrue = findViewById(R.id.btnTrue)
        btnFalse = findViewById(R.id.btnFalse)

        btnTrue.setOnClickListener {
            selectedAnswer = "TRUE"
            btnTrue.setBackgroundResource(R.drawable.bg_true) // Highlight
            btnFalse.setBackgroundResource(R.drawable.bg_false) // Reset
        }

        btnFalse.setOnClickListener {
            selectedAnswer = "FALSE"
            btnFalse.setBackgroundResource(R.drawable.bg_true) // Highlight
            btnTrue.setBackgroundResource(R.drawable.bg_false) // Reset
        }

        findViewById<Button>(R.id.btnKunci).setOnClickListener {
            if (selectedAnswer == null) {
                Toast.makeText(this, "Silakan pilih jawaban terlebih dahulu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var totalCorrect = correctAnswersFromPrevious
            var totalIncorrect = incorrectAnswersFromPrevious

            if (selectedAnswer == correctAnswer) {
                totalCorrect++
                Toast.makeText(this, "Jawaban Benar!", Toast.LENGTH_SHORT).show()
            } else {
                totalIncorrect++
                Toast.makeText(this, "Jawaban Salah!", Toast.LENGTH_SHORT).show()
            }

            // Kirim skor akumulatif ke KuisFourActivity
            val intent = Intent(this, KuisFourActivity::class.java).apply {
                putExtra("CORRECT_ANSWERS", totalCorrect)
                putExtra("INCORRECT_ANSWERS", totalIncorrect)
            }
            
            btnTrue.postDelayed({
                startActivity(intent)
                finish()
            }, 1000)
        }
    }
}
