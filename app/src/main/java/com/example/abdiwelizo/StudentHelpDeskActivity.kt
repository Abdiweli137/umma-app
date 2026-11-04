package com.example.abdiwelizo

import android.graphics.Typeface
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.abdiwelizo.data.AppRepository
import com.example.abdiwelizo.data.SupportTicket
import com.example.abdiwelizo.databinding.ActivityStudentHelpdeskBinding
import com.google.android.material.card.MaterialCardView

class StudentHelpDeskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentHelpdeskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentHelpdeskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.btnNewTicket.setOnClickListener {
            showNewTicketDialog()
        }

        renderTickets()
    }

    private fun showNewTicketDialog() {
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 24, 32, 24)
        }

        val etSubject = EditText(this).apply {
            hint = "Ticket Subject / Issue Title"
        }
        val etDesc = EditText(this).apply {
            hint = "Detailed description of your request..."
            minLines = 3
        }

        layout.addView(etSubject)
        layout.addView(etDesc)

        AlertDialog.Builder(this)
            .setTitle("Create Support Ticket")
            .setView(layout)
            .setPositiveButton("Submit Ticket") { _, _ ->
                val subject = etSubject.text.toString().trim()
                val desc = etDesc.text.toString().trim()
                if (subject.isNotEmpty() && desc.isNotEmpty()) {
                    val ticket = SupportTicket(
                        id = "TC${System.currentTimeMillis()}",
                        ticketNo = "TKT-2025-${(100..999).random()}",
                        category = "Student Inquiry",
                        subject = subject,
                        description = desc,
                        status = "Submitted",
                        date = "2025-05-19",
                        studentName = "Amina Omar"
                    )
                    AppRepository.createSupportTicket(ticket)
                    Toast.makeText(this, "Support ticket submitted successfully!", Toast.LENGTH_SHORT).show()
                    renderTickets()
                } else {
                    Toast.makeText(this, "Please enter subject and description.", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun renderTickets() {
        binding.containerTickets.removeAllViews()
        val tickets = AppRepository.supportTickets.value

        for (item in tickets) {
            val card = MaterialCardView(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(0, 0, 0, 16) }
                radius = 16f
                cardElevation = 3f
                strokeWidth = 2
                setStrokeColor(if (item.status == "Resolved") 0xFF14A350.toInt() else 0xFFD4AF37.toInt())
                setCardBackgroundColor(0xFFFFFFFF.toInt())
            }

            val layout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(20, 20, 20, 20)
            }

            val tvTitle = TextView(this).apply {
                text = "${item.ticketNo}: ${item.subject} [${item.status}]"
                textSize = 15f
                setTypeface(null, Typeface.BOLD)
                setTextColor(0xFF004D25.toInt())
            }

            val tvBody = TextView(this).apply {
                text = "Category: ${item.category} | Date: ${item.date}\nDescription: ${item.description}"
                textSize = 13f
                setTextColor(0xFF333333.toInt())
                setPadding(0, 4, 0, 8)
            }

            layout.addView(tvTitle)
            layout.addView(tvBody)

            if (item.responses.isNotEmpty()) {
                val tvRespHeader = TextView(this).apply {
                    text = "💬 Staff Responses:"
                    textSize = 13f
                    setTypeface(null, Typeface.BOLD)
                    setTextColor(0xFF14A350.toInt())
                }
                layout.addView(tvRespHeader)

                for (r in item.responses) {
                    val tvReply = TextView(this).apply {
                        text = "• ${r.senderName}: ${r.message} (${r.timestamp})"
                        textSize = 12f
                        setTextColor(0xFF222222.toInt())
                        setPadding(8, 2, 0, 4)
                    }
                    layout.addView(tvReply)
                }
            }

            card.addView(layout)
            binding.containerTickets.addView(card)
        }
    }
}
