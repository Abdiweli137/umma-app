package com.example.abdiwelizo

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.abdiwelizo.data.AppRepository
import com.example.abdiwelizo.databinding.ActivityStudentAssignmentsBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class StudentAssignmentsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentAssignmentsBinding
    private var pendingAssignmentId: String? = null

    private val filePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK && result.data != null) {
            val uri: Uri? = result.data?.data
            val fileName = uri?.lastPathSegment ?: "submitted_solution.pdf"
            pendingAssignmentId?.let { id ->
                AppRepository.submitAssignment(id, fileName)
                Toast.makeText(this, "File Selected: $fileName\nSubmitted successfully!", Toast.LENGTH_LONG).show()
                renderAssignments()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentAssignmentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }

        renderAssignments()
    }

    private fun renderAssignments() {
        binding.containerAssignments.removeAllViews()
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
                setStrokeColor(when (item.status) {
                    "Graded" -> 0xFF14A350.toInt()
                    "Submitted" -> 0xFFD4AF37.toInt()
                    else -> 0xFFE2E9E4.toInt()
                })
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

            val tvInfo = TextView(this).apply {
                text = "Deadline: ${item.deadline}\nStatus: ${item.status}\n\nTask: ${item.description}"
                textSize = 13f
                setTextColor(0xFF333333.toInt())
                setPadding(0, 6, 0, 8)
            }

            layout.addView(tvTitle)
            layout.addView(tvInfo)

            if (item.status == "Graded") {
                val tvGrade = TextView(this).apply {
                    text = "🏆 Score: ${item.gradeScore}/${item.totalMarks}\nFeedback: ${item.lecturerFeedback}"
                    textSize = 14f
                    setTypeface(null, Typeface.BOLD)
                    setTextColor(0xFF14A350.toInt())
                    setPadding(0, 4, 0, 8)
                }
                layout.addView(tvGrade)
            }

            if (item.status == "Submitted") {
                val tvFile = TextView(this).apply {
                    text = "📁 Submitted File: ${item.submittedFileUri}"
                    textSize = 12f
                    setTextColor(0xFF004D25.toInt())
                    setPadding(0, 2, 0, 8)
                }
                layout.addView(tvFile)
            }

            val btnSubmit = MaterialButton(this).apply {
                text = if (item.status == "Pending") "Select & Submit File" else "Re-submit File"
                setBackgroundColor(0xFF004D25.toInt())
                setTextColor(0xFFFFFFFF.toInt())
                cornerRadius = 10
                setOnClickListener {
                    pendingAssignmentId = item.id
                    val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                        type = "*/*"
                        addCategory(Intent.CATEGORY_OPENABLE)
                    }
                    filePickerLauncher.launch(Intent.createChooser(intent, "Select Assignment File"))
                }
            }

            layout.addView(btnSubmit)
            card.addView(layout)
            binding.containerAssignments.addView(card)
        }
    }
}
