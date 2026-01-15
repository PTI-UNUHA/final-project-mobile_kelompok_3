package com.example.codequizdam

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.gridlayout.widget.GridLayout

class KuisFourActivity : AppCompatActivity() {

    private lateinit var answerGrid: GridLayout
    private var selectedAnswerView: TextView? = null

    // Variabel untuk menyimpan skor dari aktivitas sebelumnya
    private var correctAnswersFromPrevious = 0
    private var incorrectAnswersFromPrevious = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz4)

        // Menerima skor dari KuisThreeActivity
        correctAnswersFromPrevious = intent.getIntExtra("CORRECT_ANSWERS", 0)
        incorrectAnswersFromPrevious = intent.getIntExtra("INCORRECT_ANSWERS", 0)

        answerGrid = findViewById(R.id.answer_grid)

        for (i in 0 until answerGrid.childCount) {
            val answerView = answerGrid.getChildAt(i) as TextView
            answerView.setOnClickListener { handleAnswerSelection(it as TextView) }
        }

        findViewById<Button>(R.id.btnKuncitiga).setOnClickListener {
            checkAnswerAndProceed()
        }
    }

    private fun handleAnswerSelection(selectedView: TextView) {
        selectedAnswerView?.let { resetAnswerView(it) }
        highlightAnswerView(selectedView)
        selectedAnswerView = selectedView
    }

    private fun highlightAnswerView(view: TextView) {
        val border = GradientDrawable()
        border.setColor(ContextCompat.getColor(this, R.color.selected_answer_bg))
        border.setStroke(4, ContextCompat.getColor(this, R.color.selected_answer_border))
        border.cornerRadius = 12f
        view.background = border
        view.setTextColor(Color.BLACK)
    }

    private fun resetAnswerView(view: TextView) {
        view.setBackgroundResource(R.drawable.bg_answer4)
        view.setTextColor(ContextCompat.getColor(this, R.color.answer_option_text_color))
    }

    private fun checkAnswerAndProceed() {
        if (selectedAnswerView == null) {
            Toast.makeText(this, "Silakan pilih jawaban terlebih dahulu", Toast.LENGTH_SHORT).show()
            return
        }

        var totalCorrect = correctAnswersFromPrevious
        var totalIncorrect = incorrectAnswersFromPrevious

        if (selectedAnswerView?.tag == "correct") {
            totalCorrect++
            Toast.makeText(this, "Jawaban Benar!", Toast.LENGTH_SHORT).show()
        } else {
            totalIncorrect++
            Toast.makeText(this, "Jawaban Salah!", Toast.LENGTH_SHORT).show()
        }

        // Kirim data FINAL ke ResultActivity
        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra("CORRECT_ANSWERS", totalCorrect)
            putExtra("INCORRECT_ANSWERS", totalIncorrect)
        }

        answerGrid.postDelayed({
            startActivity(intent)
            finish()
        }, 1500)
    }
}
