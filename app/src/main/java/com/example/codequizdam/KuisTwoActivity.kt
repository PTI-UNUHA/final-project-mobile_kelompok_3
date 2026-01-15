package com.example.codequizdam

import android.content.ClipData
import android.content.ClipDescription
import android.content.Intent
import android.os.Bundle
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.gridlayout.widget.GridLayout

class KuisTwoActivity : AppCompatActivity() {

    private lateinit var dragArea: LinearLayout
    private lateinit var sourceCodeContainer: GridLayout
    private lateinit var dragAreaText: TextView

    private val correctOrder = listOf("code_1", "code_2", "code_4", "code_6", "code_7", "code_3")

    // Variabel untuk menyimpan skor dari aktivitas sebelumnya
    private var correctAnswersFromPrevious = 0
    private var incorrectAnswersFromPrevious = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snippet)

        // Menerima skor dari QuizActivity
        correctAnswersFromPrevious = intent.getIntExtra("CORRECT_ANSWERS", 0)
        incorrectAnswersFromPrevious = intent.getIntExtra("INCORRECT_ANSWERS", 0)

        dragArea = findViewById(R.id.drag_area)
        sourceCodeContainer = findViewById(R.id.source_code_container)
        dragAreaText = findViewById(R.id.drag_area_text)

        dragArea.setOnDragListener(dragListener)
        sourceCodeContainer.setOnDragListener(dragListener)

        for (i in 0 until sourceCodeContainer.childCount) {
            sourceCodeContainer.getChildAt(i).setOnTouchListener(touchListener)
        }

        findViewById<Button>(R.id.btnKuncisnippet).setOnClickListener {
            checkAnswerAndProceed()
        }
    }

    private val touchListener = View.OnTouchListener { view, motionEvent ->
        if (motionEvent.action == MotionEvent.ACTION_DOWN) {
            view.parent.requestDisallowInterceptTouchEvent(true)
            val item = ClipData.Item(view.tag as CharSequence)
            val dragData = ClipData(view.tag as CharSequence, arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN), item)
            val shadowBuilder = View.DragShadowBuilder(view)
            view.startDragAndDrop(dragData, shadowBuilder, view, 0)
            view.visibility = View.INVISIBLE
            true
        } else {
            false
        }
    }

    private val dragListener = View.OnDragListener { destinationView, dragEvent ->
        val draggedView = dragEvent.localState as View
        when (dragEvent.action) {
            DragEvent.ACTION_DRAG_STARTED -> true
            DragEvent.ACTION_DROP -> {
                val owner = draggedView.parent as ViewGroup
                owner.removeView(draggedView)
                val destination = destinationView as ViewGroup
                destination.addView(draggedView)
                draggedView.visibility = View.VISIBLE
                true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                if (!dragEvent.result) {
                    draggedView.visibility = View.VISIBLE
                }
                dragAreaText.visibility = if (dragArea.childCount > 1) View.GONE else View.VISIBLE
                true
            }
            else -> true
        }
    }

    private fun checkAnswerAndProceed() {
        val droppedTags = mutableListOf<String>()
        for (i in 0 until dragArea.childCount) {
            val child = dragArea.getChildAt(i)
            if (child.id != R.id.drag_area_text) {
                child.tag?.toString()?.let { droppedTags.add(it) }
            }
        }

        var totalCorrect = correctAnswersFromPrevious
        var totalIncorrect = incorrectAnswersFromPrevious

        if (droppedTags == correctOrder) {
            totalCorrect++
            Toast.makeText(this, "Jawaban Benar!", Toast.LENGTH_SHORT).show()
        } else {
            totalIncorrect++
            Toast.makeText(this, "Jawaban Masih Salah, Coba Lagi!", Toast.LENGTH_LONG).show()
            // Jika salah, reset puzzle dan jangan lanjutkan
            resetPuzzle()
            return
        }
        
        // Kirim skor akumulatif ke KuisThreeActivity
        val intent = Intent(this, KuisThreeActivity::class.java).apply {
            putExtra("CORRECT_ANSWERS", totalCorrect)
            putExtra("INCORRECT_ANSWERS", totalIncorrect)
        }

        dragArea.postDelayed({
            startActivity(intent)
            finish()
        }, 1500)
    }

    private fun resetPuzzle() {
        val viewsToReset = mutableListOf<View>()
        for (i in 0 until dragArea.childCount) {
            if (dragArea.getChildAt(i).id != R.id.drag_area_text) {
                viewsToReset.add(dragArea.getChildAt(i))
            }
        }
        for (view in viewsToReset) {
            dragArea.removeView(view)
            sourceCodeContainer.addView(view)
        }
    }
}
