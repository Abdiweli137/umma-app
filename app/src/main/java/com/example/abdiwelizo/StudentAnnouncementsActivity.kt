package com.example.abdiwelizo

import android.graphics.Typeface
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.abdiwelizo.data.AppRepository
import com.example.abdiwelizo.databinding.ActivityStudentAnnouncementsBinding
import com.google.android.material.card.MaterialCardView

class StudentAnnouncementsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentAnnouncementsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentAnnouncementsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }

        renderAnnouncements()
    }

    private fun renderAnnouncements() {
        binding.containerAnnouncements.removeAllViews()
        val announcements = AppRepository.announcements.value

        for (item in announcements) {
            val card = MaterialCardView(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(0, 0, 0, 16) }
                radius = 16f
                cardElevation = 3f
                strokeWidth = if (item.isEmergency) 2 else 1
                setStrokeColor(if (item.isEmergency) 0xFFD32F2F.toInt() else 0xFFE2E9E4.toInt())
                setCardBackgroundColor(if (item.isEmergency) 0xFFFFEBEE.toInt() else 0xFFFFFFFF.toInt())
            }

            val layout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(20, 20, 20, 20)
            }

            val tvTitle = TextView(this).apply {
                text = "${if (item.isEmergency) "🚨 [EMERGENCY] " else "📌 "}${item.title}"
                textSize = 16f
                setTypeface(null, Typeface.BOLD)
                setTextColor(if (item.isEmergency) 0xFFD32F2F.toInt() else 0xFF004D25.toInt())
            }

            val tvMeta = TextView(this).apply {
                text = "Category: ${item.category} | Date: ${item.date} | Author: ${item.author}"
                textSize = 12f
                setTextColor(0xFF666666.toInt())
                setPadding(0, 4, 0, 8)
            }

            val tvContent = TextView(this).apply {
                text = item.content
                textSize = 14f
                setTextColor(0xFF222222.toInt())
            }

            layout.addView(tvTitle)
            layout.addView(tvMeta)
            layout.addView(tvContent)
            card.addView(layout)
            binding.containerAnnouncements.addView(card)
        }
    }
}
