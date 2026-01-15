package com.example.codequizdam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val correctAnswers = intent.getIntExtra("CORRECT_ANSWERS", 0)
        val incorrectAnswers = intent.getIntExtra("INCORRECT_ANSWERS", 0)
        val totalQuestions = 4 // Total ada 4 Kuis

        val score = correctAnswers * 25
        val emptyAnswers = totalQuestions - correctAnswers - incorrectAnswers

        val totalScoreTextView: TextView = findViewById(R.id.text_total_score)
        val resultTitleTextView: TextView = findViewById(R.id.text_result_title)
        val correctSummaryTextView: TextView = findViewById(R.id.text_correct_summary)
        val incorrectSummaryTextView: TextView = findViewById(R.id.text_incorrect_summary)
        val emptySummaryTextView: TextView = findViewById(R.id.text_empty_summary)
        val resultRobotImageView: ImageView = findViewById(R.id.resultRobot)

        totalScoreTextView.text = " Score: $score"
        correctSummaryTextView.text = "$correctAnswers/$totalQuestions BENAR!"
        incorrectSummaryTextView.text = "❌ $incorrectAnswers Salah"
        // Pastikan jumlah kosong tidak negatif
        emptySummaryTextView.text = "⏱ ${if (emptyAnswers < 0) 0 else emptyAnswers} Kosong"

        // PERBAIKAN: Kondisi disesuaikan dengan total 4 soal
        when {
            correctAnswers == 4 -> {
                resultTitleTextView.text = "FANTASTIC!"
                resultTitleTextView.setTextColor(ContextCompat.getColor(this, R.color.yellow_fantastic))
                correctSummaryTextView.setTextColor(ContextCompat.getColor(this, R.color.yellow_fantastic))
                resultRobotImageView.setImageResource(R.drawable.ic_smile_robot)
            }
            correctAnswers >= 2 -> { // Dianggap "Good Job" jika benar 2 atau 3
                resultTitleTextView.text = "GOOD JOB!"
                resultTitleTextView.setTextColor(ContextCompat.getColor(this, R.color.green_good_job))
                correctSummaryTextView.setTextColor(ContextCompat.getColor(this, R.color.green_good_job))
                resultRobotImageView.setImageResource(R.drawable.robot_quiz)
            }
            else -> {
                resultTitleTextView.text = "TRY AGAIN!"
                resultTitleTextView.setTextColor(ContextCompat.getColor(this, R.color.red_try_again))
                correctSummaryTextView.setTextColor(ContextCompat.getColor(this, R.color.red_try_again))
                // PERBAIKAN: Menggunakan referensi gambar yang benar
                resultRobotImageView.setImageResource(R.drawable.ic_smile_robot)
            }
        }

        findViewById<Button>(R.id.btnUlangi).setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnJawaban).setOnClickListener {
            val intent = Intent(this, JawabanActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        // PERBAIKAN: Menambahkan putExtra untuk mengirim skor ke LeaderboardActivity
        findViewById<Button>(R.id.btnLeaderboard).setOnClickListener {
            val intent = Intent(this, LeaderboardActivity::class.java)
            intent.putExtra("USER_SCORE", score)
            startActivity(intent)
        }
    }
}
