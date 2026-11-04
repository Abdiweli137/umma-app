package com.example.abdiwelizo

import android.graphics.Typeface
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.abdiwelizo.data.AppRepository
import com.example.abdiwelizo.databinding.ActivityLecturerCoursesBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class LecturerCoursesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLecturerCoursesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLecturerCoursesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }

        renderCourses()
    }

    private fun renderCourses() {
        binding.containerCourses.removeAllViews()
        val units = AppRepository.academicUnits.value.filter { it.lecturerName.contains("Hassan") }

        for (unit in units) {
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

            val tvTitle = TextView(this).apply {
                text = "${unit.unitCode} - ${unit.unitName}"
                textSize = 16f
                setTypeface(null, Typeface.BOLD)
                setTextColor(0xFF004D25.toInt())
            }

            val tvDetails = TextView(this).apply {
                text = "Semester: ${unit.semester}\nCredits: ${unit.credits} | Enrolled Students: 3"
                textSize = 13f
                setTextColor(0xFF333333.toInt())
                setPadding(0, 4, 0, 10)
            }

            val btnAttendance = MaterialButton(this).apply {
                text = "Mark Class Attendance"
                setBackgroundColor(0xFF004D25.toInt())
                setTextColor(0xFFFFFFFF.toInt())
                cornerRadius = 10
                setOnClickListener {
                    showAttendanceRegister(unit.unitName)
                }
            }

            layout.addView(tvTitle)
            layout.addView(tvDetails)
            layout.addView(btnAttendance)
            card.addView(layout)
            binding.containerCourses.addView(card)
        }
    }

    private fun showAttendanceRegister(unitName: String) {
        val students = arrayOf("Amina Omar (BIT/2023/4589)", "John Kamau (BIT/2023/1022)", "Sarah Wanjiku (BIT/2023/3391)")
        val checked = booleanArrayOf(true, true, false)

        AlertDialog.Builder(this)
            .setTitle("Attendance Register: $unitName")
            .setMultiChoiceItems(students, checked) { _, which, isChecked ->
                checked[which] = isChecked
            }
            .setPositiveButton("Save Attendance") { _, _ ->
                Toast.makeText(this, "Class attendance for $unitName saved successfully!", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
