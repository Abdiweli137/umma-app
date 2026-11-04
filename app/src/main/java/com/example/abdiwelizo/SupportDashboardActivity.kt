package com.example.abdiwelizo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.abdiwelizo.databinding.ActivitySupportDashboardBinding

class SupportDashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySupportDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySupportDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnViewTickets.setOnClickListener {
            startActivity(Intent(this, SupportTicketsActivity::class.java))
        }

        binding.btnWelfareInquiries.setOnClickListener {
            startActivity(Intent(this, SupportTicketsActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
