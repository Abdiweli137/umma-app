package com.example.abdiwelizo

import android.graphics.Typeface
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.abdiwelizo.data.AppRepository
import com.example.abdiwelizo.data.MockData
import com.example.abdiwelizo.databinding.ActivityStudentResultsBinding
import com.google.android.material.card.MaterialCardView

class StudentResultsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentResultsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.btnDownloadPdf.setOnClickListener {
            Toast.makeText(this, "Official Academic Transcript PDF downloaded to device storage.", Toast.LENGTH_LONG).show()
        }

        renderTranscripts()
    }

    private fun renderTranscripts() {
        binding.containerTranscripts.removeAllViews()

        val transcripts = MockData.transcripts

        for (sem in transcripts) {
            val card = MaterialCardView(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(0, 0, 0, 16) }
                radius = 16f
                cardElevation = 3f
                strokeWidth = 1
                setStrokeColor(0xFFE2E9E4.toInt())
                setCardBackgroundColor(0xFFFFFFFF.toInt())
            }

            val layout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(20, 20, 20, 20)
            }

            val tvSemHeader = TextView(this).apply {
                text = "${sem.semesterName} — GPA: ${sem.gpa}"
                textSize = 16f
                setTypeface(null, Typeface.BOLD)
                setTextColor(0xFF004D25.toInt())
            }
            layout.addView(tvSemHeader)

            for (unit in sem.units) {
                val tvUnit = TextView(this).apply {
                    text = "• ${unit.unitCode} ${unit.unitName}: ${unit.marks}% (${unit.grade})"
                    textSize = 14f
                    setTextColor(0xFF222222.toInt())
                    setPadding(12, 6, 0, 6)
                }
                layout.addView(tvUnit)
            }

            card.addView(layout)
            binding.containerTranscripts.addView(card)
        }

        // Add Live Graded Assignments Section
        val graded = AppRepository.assignments.value.filter { it.status == "Graded" }
        if (graded.isNotEmpty()) {
            val liveCard = MaterialCardView(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(0, 0, 0, 16) }
                radius = 16f
                cardElevation = 3f
                strokeWidth = 2
                setStrokeColor(0xFF14A350.toInt())
                setCardBackgroundColor(0xFFF1F8F4.toInt())
            }

            val liveLayout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(20, 20, 20, 20)
            }

            val tvLiveHeader = TextView(this).apply {
                text = "Recently Graded Continuous Assessment (Live Lecturer Inputs)"
                textSize = 15f
                setTypeface(null, Typeface.BOLD)
                setTextColor(0xFF004D25.toInt())
            }
            liveLayout.addView(tvLiveHeader)

            for (g in graded) {
                val tvGraded = TextView(this).apply {
                    text = "• ${g.unitCode} ${g.title}: ${g.gradeScore}/${g.totalMarks} Marks\n  Feedback: ${g.lecturerFeedback}"
                    textSize = 13f
                    setTextColor(0xFF222222.toInt())
                    setPadding(12, 6, 0, 6)
                }
                liveLayout.addView(tvGraded)
            }

            liveCard.addView(liveLayout)
            binding.containerTranscripts.addView(liveCard)
        }
    }
}
