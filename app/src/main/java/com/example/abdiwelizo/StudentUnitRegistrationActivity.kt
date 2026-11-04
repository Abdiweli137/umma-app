package com.example.abdiwelizo

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.abdiwelizo.data.AcademicUnit
import com.example.abdiwelizo.data.AppRepository
import com.example.abdiwelizo.databinding.ActivityStudentUnitRegistrationBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class StudentUnitRegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentUnitRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentUnitRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        renderUnitsList()
    }

    private fun renderUnitsList() {
        binding.containerUnits.removeAllViews()
        val units = AppRepository.academicUnits.value

        val registeredCount = units.count { it.isRegistered }
        val registeredCredits = units.filter { it.isRegistered }.sumOf { it.credits }
        binding.tvCreditSummary.text = "Registered Units: $registeredCount Units ($registeredCredits Credits)"

        for (unit in units) {
            val card = MaterialCardView(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 0, 0, 16)
                }
                radius = 16f
                cardElevation = 4f
                strokeWidth = 2
                setStrokeColor(if (unit.isRegistered) 0xFF14A350.toInt() else 0xFFE2E9E4.toInt())
                setCardBackgroundColor(if (unit.isRegistered) 0xFFF1F8F4.toInt() else 0xFFFFFFFF.toInt())
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

            val tvDesc = TextView(this).apply {
                text = "${unit.description}\nLecturer: ${unit.lecturerName} | Credits: ${unit.credits}"
                textSize = 13f
                setTextColor(0xFF444444.toInt())
                setPadding(0, 8, 0, 12)
            }

            val btnAction = MaterialButton(this).apply {
                text = if (unit.isRegistered) "Drop Unit" else "Register Unit"
                setBackgroundColor(if (unit.isRegistered) 0xFFD32F2F.toInt() else 0xFF004D25.toInt())
                setTextColor(0xFFFFFFFF.toInt())
                cornerRadius = 10
                setOnClickListener {
                    if (unit.isRegistered) {
                        AppRepository.dropUnit(unit.id)
                        Toast.makeText(this@StudentUnitRegistrationActivity, "Dropped ${unit.unitCode}", Toast.LENGTH_SHORT).show()
                    } else {
                        AppRepository.registerUnit(unit.id)
                        Toast.makeText(this@StudentUnitRegistrationActivity, "Registered ${unit.unitCode} successfully!", Toast.LENGTH_SHORT).show()
                    }
                    renderUnitsList()
                }
            }

            layout.addView(tvTitle)
            layout.addView(tvDesc)
            layout.addView(btnAction)
            card.addView(layout)

            binding.containerUnits.addView(card)
        }
    }
}
