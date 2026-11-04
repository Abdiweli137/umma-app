package com.example.abdiwelizo

import android.graphics.Typeface
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.abdiwelizo.data.AppRepository
import com.example.abdiwelizo.databinding.ActivityStudentTimetableBinding
import com.google.android.material.card.MaterialCardView

class StudentTimetableActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentTimetableBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentTimetableBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.btnDayMon.setOnClickListener { renderSchedule("Monday") }
        binding.btnDayTue.setOnClickListener { renderSchedule("Tuesday") }
        binding.btnDayThu.setOnClickListener { renderSchedule("Thursday") }
        binding.btnDayFri.setOnClickListener { renderSchedule("Friday") }
        binding.btnDayExams.setOnClickListener { renderExams() }

        renderSchedule("Monday")
    }

    private fun renderSchedule(day: String) {
        binding.containerTimetable.removeAllViews()
        val items = AppRepository.timetables.value.filter { !it.isExam && it.dayOfWeek == day }

        if (items.isEmpty()) {
            val tvEmpty = TextView(this).apply {
                text = "No scheduled lectures for $day."
                textSize = 15f
                setTextColor(0xFF666666.toInt())
            }
            binding.containerTimetable.addView(tvEmpty)
            return
        }

        for (item in items) {
            val card = MaterialCardView(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(0, 0, 0, 16) }
                radius = 14f
                cardElevation = 3f
                strokeWidth = 1
                setStrokeColor(0xFFE2E9E4.toInt())
                setCardBackgroundColor(0xFFFFFFFF.toInt())
            }

            val layout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(20, 20, 20, 20)
            }

            val tvTime = TextView(this).apply {
                text = "⏰ ${item.timeString}"
                textSize = 13f
                setTypeface(null, Typeface.BOLD)
                setTextColor(0xFF14A350.toInt())
            }

            val tvTitle = TextView(this).apply {
                text = "${item.unitCode}: ${item.unitName}"
                textSize = 16f
                setTypeface(null, Typeface.BOLD)
                setTextColor(0xFF004D25.toInt())
                setPadding(0, 4, 0, 4)
            }

            val tvDetails = TextView(this).apply {
                text = "Venue: ${item.venue}\nLecturer: ${item.lecturerName}"
                textSize = 13f
                setTextColor(0xFF333333.toInt())
            }

            layout.addView(tvTime)
            layout.addView(tvTitle)
            layout.addView(tvDetails)
            card.addView(layout)
            binding.containerTimetable.addView(card)
        }
    }

    private fun renderExams() {
        binding.containerTimetable.removeAllViews()
        val exams = AppRepository.timetables.value.filter { it.isExam }

        for (item in exams) {
            val card = MaterialCardView(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(0, 0, 0, 16) }
                radius = 16f
                cardElevation = 4f
                strokeWidth = 2
                setStrokeColor(0xFFD4AF37.toInt())
                setCardBackgroundColor(0xFFFFFDF5.toInt())
            }

            val layout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(20, 20, 20, 20)
            }

            val tvTitle = TextView(this).apply {
                text = "📝 ${item.unitName} (${item.unitCode})"
                textSize = 16f
                setTypeface(null, Typeface.BOLD)
                setTextColor(0xFF004D25.toInt())
            }

            val tvDetails = TextView(this).apply {
                text = "Exam Date: ${item.examDate}\nTime: ${item.timeString}\nVenue: ${item.venue}\nRules: ${item.examRules}"
                textSize = 13f
                setTextColor(0xFF222222.toInt())
                setPadding(0, 6, 0, 0)
            }

            layout.addView(tvTitle)
            layout.addView(tvDetails)
            card.addView(layout)
            binding.containerTimetable.addView(card)
        }
    }
}
