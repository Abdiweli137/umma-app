package com.example.abdiwelizo

import android.graphics.Typeface
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.abdiwelizo.data.AppRepository
import com.example.abdiwelizo.databinding.ActivityStudentFeesBinding
import com.google.android.material.card.MaterialCardView

class StudentFeesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentFeesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentFeesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }

        val fee = AppRepository.feeRecord.value
        binding.tvBalance.text = "Outstanding Balance: KES ${String.format("%,.0f", fee.balance)}"
        binding.tvChargedPaid.text = "Total Charged: KES ${String.format("%,.0f", fee.totalCharged)} | Paid: KES ${String.format("%,.0f", fee.totalPaid)}"

        binding.btnReceipt.setOnClickListener {
            Toast.makeText(this, "Official Fee Statement & Payment Receipt generated.", Toast.LENGTH_LONG).show()
        }

        renderTransactions()
    }

    private fun renderTransactions() {
        binding.containerTransactions.removeAllViews()
        val fee = AppRepository.feeRecord.value

        // Bursary Section
        val bursaryCard = MaterialCardView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 16) }
            radius = 14f
            cardElevation = 2f
            strokeWidth = 1
            setStrokeColor(0xFFD4AF37.toInt())
            setCardBackgroundColor(0xFFFFFDF5.toInt())
        }
        val bursaryLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }
        val tvBursaryHeader = TextView(this).apply {
            text = "🏅 Scholarship & Bursary Allocations"
            textSize = 15f
            setTypeface(null, Typeface.BOLD)
            setTextColor(0xFF004D25.toInt())
        }
        val tvBursaryBody = TextView(this).apply {
            text = fee.scholarshipInfo
            textSize = 13f
            setTextColor(0xFF222222.toInt())
            setPadding(0, 4, 0, 0)
        }
        bursaryLayout.addView(tvBursaryHeader)
        bursaryLayout.addView(tvBursaryBody)
        bursaryCard.addView(bursaryLayout)
        binding.containerTransactions.addView(bursaryCard)

        // Transactions List
        for (tx in fee.transactions) {
            val card = MaterialCardView(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(0, 0, 0, 12) }
                radius = 12f
                cardElevation = 2f
                strokeWidth = 1
                setStrokeColor(0xFFE2E9E4.toInt())
                setCardBackgroundColor(0xFFFFFFFF.toInt())
            }

            val layout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(16, 16, 16, 16)
            }

            val tvRef = TextView(this).apply {
                text = "Ref: ${tx.referenceNo} (${tx.mode})"
                textSize = 14f
                setTypeface(null, Typeface.BOLD)
                setTextColor(0xFF004D25.toInt())
            }

            val tvAmount = TextView(this).apply {
                text = "Amount Paid: KES ${String.format("%,.0f", tx.amount)} | Date: ${tx.date}\nStatus: ${tx.status}"
                textSize = 13f
                setTextColor(0xFF333333.toInt())
                setPadding(0, 4, 0, 0)
            }

            layout.addView(tvRef)
            layout.addView(tvAmount)
            card.addView(layout)
            binding.containerTransactions.addView(card)
        }
    }
}
