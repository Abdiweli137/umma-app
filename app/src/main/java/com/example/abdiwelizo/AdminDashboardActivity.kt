package com.example.abdiwelizo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.abdiwelizo.data.AppRepository
import com.example.abdiwelizo.databinding.ActivityAdminDashboardBinding

class AdminDashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnManageUsers.setOnClickListener {
            startActivity(Intent(this, AdminUsersActivity::class.java))
        }

        binding.btnManageAcademics.setOnClickListener {
            startActivity(Intent(this, AdminAcademicsActivity::class.java))
        }

        binding.btnManageAnnouncements.setOnClickListener {
            startActivity(Intent(this, AdminAnnouncementsActivity::class.java))
        }

        binding.btnFinancialReports.setOnClickListener {
            val fee = AppRepository.feeRecord.value
            AlertDialog.Builder(this)
                .setTitle("University Financial Ledger Summary")
                .setMessage("Total Fee Projected: KES 45,000,000\nTotal Collected: KES ${String.format("%,.0f", fee.totalPaid)}\nOutstanding Receivables: KES ${String.format("%,.0f", fee.balance)}\n\nAll accounts reconciled successfully.")
                .setPositiveButton("Export PDF Report", null)
                .setNegativeButton("Close", null)
                .show()
        }

        binding.btnLogout.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
