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
import com.example.abdiwelizo.data.TicketResponse
import com.example.abdiwelizo.databinding.ActivitySupportTicketsBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class SupportTicketsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySupportTicketsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySupportTicketsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }

        renderSupportQueue()
    }

    private fun renderSupportQueue() {
        binding.containerStaffTickets.removeAllViews()
        val tickets = AppRepository.supportTickets.value

        for (t in tickets) {
            val card = MaterialCardView(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(0, 0, 0, 16) }
                radius = 16f
                cardElevation = 3f
                strokeWidth = 2
                setStrokeColor(if (t.status == "Resolved") 0xFF14A350.toInt() else 0xFFD4AF37.toInt())
                setCardBackgroundColor(0xFFFFFFFF.toInt())
            }

            val layout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(20, 20, 20, 20)
            }

            val tvTitle = TextView(this).apply {
                text = "${t.ticketNo}: ${t.subject} [${t.status}]"
                textSize = 15f
                setTypeface(null, Typeface.BOLD)
                setTextColor(0xFF004D25.toInt())
            }

            val tvDetails = TextView(this).apply {
                text = "Student: ${t.studentName} | Category: ${t.category}\nDate: ${t.date}\nDescription: ${t.description}"
                textSize = 13f
                setTextColor(0xFF333333.toInt())
                setPadding(0, 4, 0, 8)
            }

            layout.addView(tvTitle)
            layout.addView(tvDetails)

            if (t.responses.isNotEmpty()) {
                val tvRespHeader = TextView(this).apply {
                    text = "💬 Previous Staff Replies:"
                    textSize = 13f
                    setTypeface(null, Typeface.BOLD)
                    setTextColor(0xFF14A350.toInt())
                }
                layout.addView(tvRespHeader)

                for (r in t.responses) {
                    val tvReply = TextView(this).apply {
                        text = "• ${r.senderName}: ${r.message}"
                        textSize = 12f
                        setTextColor(0xFF222222.toInt())
                        setPadding(8, 2, 0, 4)
                    }
                    layout.addView(tvReply)
                }
            }

            val btnReply = MaterialButton(this).apply {
                text = "Reply & Update Status"
                setBackgroundColor(0xFF004D25.toInt())
                setTextColor(0xFFFFFFFF.toInt())
                cornerRadius = 10
                setOnClickListener {
                    showReplyDialog(t.id, t.ticketNo)
                }
            }

            layout.addView(btnReply)
            card.addView(layout)
            binding.containerStaffTickets.addView(card)
        }
    }

    private fun showReplyDialog(ticketId: String, ticketNo: String) {
        val formLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 24, 32, 24)
        }

        val etReply = EditText(this).apply {
            hint = "Type response to student..."
            minLines = 3
        }

        formLayout.addView(etReply)

        AlertDialog.Builder(this)
            .setTitle("Respond to $ticketNo")
            .setView(formLayout)
            .setPositiveButton("Send Response & Mark Resolved") { _, _ ->
                val msg = etReply.text.toString().trim()
                if (msg.isNotEmpty()) {
                    AppRepository.updateTicketStatus(ticketId, "Resolved")
                    AppRepository.addTicketResponse(ticketId, TicketResponse("Yusuf Kassim (Support)", msg, "Just now", true))
                    Toast.makeText(this, "Response sent to student and ticket marked Resolved!", Toast.LENGTH_LONG).show()
                    renderSupportQueue()
                } else {
                    Toast.makeText(this, "Please type a message.", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
