package com.example.codequizdam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    private lateinit var optionA: LinearLayout
    private lateinit var optionB: LinearLayout
    private lateinit var optionC: LinearLayout
    private lateinit var optionD: LinearLayout

    private val correctAnswer = "A"
    private var selectedAnswer: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        optionA = findViewById(R.id.option_a)
        optionB = findViewById(R.id.option_b)
        optionC = findViewById(R.id.option_c)
        optionD = findViewById(R.id.option_d)

        optionA.setOnClickListener { selectOption("A", it as LinearLayout) }
        optionB.setOnClickListener { selectOption("B", it as LinearLayout) }
        optionC.setOnClickListener { selectOption("C", it as LinearLayout) }
        optionD.setOnClickListener { selectOption("D", it as LinearLayout) }

        findViewById<Button>(R.id.btnKunciJawaban).setOnClickListener {
            if (selectedAnswer == null) {
                Toast.makeText(this, "Silakan pilih jawaban terlebih dahulu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var correctAnswers = 0
            var incorrectAnswers = 0

            if (selectedAnswer == correctAnswer) {
                correctAnswers++
                Toast.makeText(this, "Jawaban Benar!", Toast.LENGTH_SHORT).show()
            } else {
                incorrectAnswers++
                Toast.makeText(this, "Jawaban Salah!", Toast.LENGTH_SHORT).show()
            }

            // Kirim data ke KuisTwoActivity
            val intent = Intent(this, KuisTwoActivity::class.java).apply {
                putExtra("CORRECT_ANSWERS", correctAnswers)
                putExtra("INCORRECT_ANSWERS", incorrectAnswers)
            }

            // Tunda sebelum pindah agar Toast terlihat
            optionA.postDelayed({
                startActivity(intent)
                finish()
            }, 1000)
        }
    }

    private fun selectOption(answer: String, view: LinearLayout) {
        resetOptionBackgrounds()
        selectedAnswer = answer
        view.setBackgroundResource(R.drawable.bg_true) // Sorot jawaban yang dipilih
    }

    private fun resetOptionBackgrounds() {
        optionA.setBackgroundResource(R.drawable.option_green)
        optionB.setBackgroundResource(R.drawable.option_orange)
        optionC.setBackgroundResource(R.drawable.option_purple)
        optionD.setBackgroundResource(R.drawable.option_yellow)
    }
}
