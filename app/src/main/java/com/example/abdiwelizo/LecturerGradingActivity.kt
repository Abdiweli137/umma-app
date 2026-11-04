package com.example.abdiwelizo

import android.graphics.Typeface
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.abdiwelizo.data.AppRepository
import com.example.abdiwelizo.databinding.ActivityLecturerGradingBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class LecturerGradingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLecturerGradingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLecturerGradingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }

        renderAssignmentsForGrading()
    }

    private fun renderAssignmentsForGrading() {
        binding.containerGrading.removeAllViews()
        val assignments = AppRepository.assignments.value

        for (item in assignments) {
            val card = MaterialCardView(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(0, 0, 0, 16) }
                radius = 16f
                cardElevation = 3f
                strokeWidth = 2
                setStrokeColor(if (item.status == "Graded") 0xFF14A350.toInt() else 0xFFD4AF37.toInt())
                setCardBackgroundColor(0xFFFFFFFF.toInt())
            }

            val layout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(20, 20, 20, 20)
            }

            val tvTitle = TextView(this).apply {
                text = "${item.unitCode}: ${item.title}"
                textSize = 16f
                setTypeface(null, Typeface.BOLD)
                setTextColor(0xFF004D25.toInt())
            }

            val tvDetails = TextView(this).apply {
                text = "Student: Amina Omar (BIT/2023/4589)\nSubmitted File: ${item.submittedFileUri.ifEmpty { "Pending Submission" }}\nStatus: ${item.status}"
                textSize = 13f
                setTextColor(0xFF333333.toInt())
                setPadding(0, 4, 0, 6)
            }

            layout.addView(tvTitle)
            layout.addView(tvDetails)

            if (item.status == "Graded") {
                val tvGradeInfo = TextView(this).apply {
                    text = "Current Grade: ${item.gradeScore}/${item.totalMarks}\nFeedback: ${item.lecturerFeedback}"
                    textSize = 13f
                    setTypeface(null, Typeface.BOLD)
                    setTextColor(0xFF14A350.toInt())
                    setPadding(0, 0, 0, 8)
                }
                layout.addView(tvGradeInfo)
            }

            val btnGrade = MaterialButton(this).apply {
                text = if (item.status == "Graded") "Update Grade & Feedback" else "Grade Submission"
                setBackgroundColor(0xFF004D25.toInt())
                setTextColor(0xFFFFFFFF.toInt())
                cornerRadius = 10
                setOnClickListener {
                    showGradingForm(item.id, item.title, item.totalMarks)
                }
            }

            layout.addView(btnGrade)
            card.addView(layout)
            binding.containerGrading.addView(card)
        }
    }

    private fun showGradingForm(assignmentId: String, title: String, totalMarks: Int) {
        val formLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 24, 32, 24)
        }

        val etScore = EditText(this).apply {
            hint = "Enter Score (Max $totalMarks)"
            inputType = InputType.TYPE_CLASS_NUMBER
        }

        val etFeedback = EditText(this).apply {
            hint = "Enter Lecturer Feedback / Comments..."
            minLines = 2
        }

        formLayout.addView(etScore)
        formLayout.addView(etFeedback)

        AlertDialog.Builder(this)
            .setTitle("Grade: $title")
            .setView(formLayout)
            .setPositiveButton("Save Grade") { _, _ ->
                val scoreText = etScore.text.toString().trim()
                val feedback = etFeedback.text.toString().trim()
                if (scoreText.isNotEmpty()) {
                    val score = scoreText.toIntOrNull() ?: 0
                    AppRepository.gradeAssignment(assignmentId, score, feedback.ifEmpty { "Satisfactory performance." })
                    Toast.makeText(this, "Grade ($score/$totalMarks) saved! Student dashboard updated.", Toast.LENGTH_LONG).show()
                    renderAssignmentsForGrading()
                } else {
                    Toast.makeText(this, "Please enter a valid score.", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
