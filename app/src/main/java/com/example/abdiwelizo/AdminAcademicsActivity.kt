package com.example.abdiwelizo

import android.graphics.Typeface
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.abdiwelizo.data.AcademicUnit
import com.example.abdiwelizo.data.AppRepository
import com.example.abdiwelizo.databinding.ActivityAdminAcademicsBinding
import com.google.android.material.card.MaterialCardView

class AdminAcademicsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminAcademicsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminAcademicsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.btnAddUnit.setOnClickListener {
            showAddUnitDialog()
        }

        renderUnits()
    }

    private fun showAddUnitDialog() {
        val formLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 24, 32, 24)
        }

        val etCode = EditText(this).apply { hint = "Unit Code (e.g. BIT 316)" }
        val etName = EditText(this).apply { hint = "Unit Name (e.g. Cloud Computing)" }
        val etLec = EditText(this).apply { hint = "Lecturer Name" }

        formLayout.addView(etCode)
        formLayout.addView(etName)
        formLayout.addView(etLec)

        AlertDialog.Builder(this)
            .setTitle("Add New Academic Unit")
            .setView(formLayout)
            .setPositiveButton("Save Unit") { _, _ ->
                val code = etCode.text.toString().trim()
                val name = etName.text.toString().trim()
                val lec = etLec.text.toString().trim()

                if (code.isNotEmpty() && name.isNotEmpty()) {
                    val newUnit = AcademicUnit(
                        id = "ACT${System.currentTimeMillis()}",
                        unitCode = code,
                        unitName = name,
                        credits = 3,
                        semester = "Semester II, 2024/2025",
                        description = "Academic unit offered by Department of Computing & IT.",
                        lecturerName = lec.ifEmpty { "Dr. Hassan Al-Mumin" },
                        isRegistered = false
                    )
                    MockData_addUnit(newUnit)
                    Toast.makeText(this, "Academic Unit $code added to university curriculum!", Toast.LENGTH_SHORT).show()
                    renderUnits()
                } else {
                    Toast.makeText(this, "Please enter unit code and name.", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun MockData_addUnit(unit: AcademicUnit) {
        AppRepository.registerUnit(unit.id) // Adds or registers
    }

    private fun renderUnits() {
        binding.containerAdminUnits.removeAllViews()
        val units = AppRepository.academicUnits.value

        for (u in units) {
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
                text = "${u.unitCode}: ${u.unitName}"
                textSize = 16f
                setTypeface(null, Typeface.BOLD)
                setTextColor(0xFF004D25.toInt())
            }

            val tvDetails = TextView(this).apply {
                text = "Lecturer: ${u.lecturerName}\nCredits: ${u.credits} | Semester: ${u.semester}\n${u.description}"
                textSize = 13f
                setTextColor(0xFF333333.toInt())
                setPadding(0, 4, 0, 0)
            }

            layout.addView(tvTitle)
            layout.addView(tvDetails)
            card.addView(layout)
            binding.containerAdminUnits.addView(card)
        }
    }
}
