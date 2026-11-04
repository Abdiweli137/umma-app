package com.example.abdiwelizo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.abdiwelizo.data.AppRepository
import com.example.abdiwelizo.data.MockData
import com.example.abdiwelizo.databinding.ActivityStudentDashboardBinding

class StudentDashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentDashboardBinding
    private var userEmail: String = "student@umma.ac.ke"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userEmail = intent.getStringExtra("USER_EMAIL") ?: "student@umma.ac.ke"
        val student = MockData.users.find { it.email == userEmail } ?: MockData.users[0]

        // Header Greeting
        binding.tvStudentName.text = "Welcome, ${student.name}"
        binding.tvStudentProgram.text = student.programOrDesignation

        // Dedicated Page Navigation Actions
        binding.cardUnits.setOnClickListener {
            startActivity(Intent(this, StudentUnitRegistrationActivity::class.java))
        }

        binding.cardResults.setOnClickListener {
            startActivity(Intent(this, StudentResultsActivity::class.java))
        }

        binding.cardTimetable.setOnClickListener {
            startActivity(Intent(this, StudentTimetableActivity::class.java))
        }

        binding.cardAssignments.setOnClickListener {
            startActivity(Intent(this, StudentAssignmentsActivity::class.java))
        }

        binding.cardLibrary.setOnClickListener {
            startActivity(Intent(this, StudentLibraryActivity::class.java))
        }

        binding.cardFees.setOnClickListener {
            startActivity(Intent(this, StudentFeesActivity::class.java))
        }

        binding.cardHelpDesk.setOnClickListener {
            startActivity(Intent(this, StudentHelpDeskActivity::class.java))
        }

        binding.cardAnnouncements.setOnClickListener {
            startActivity(Intent(this, StudentAnnouncementsActivity::class.java))
        }

        binding.cardProfile.setOnClickListener {
            startActivity(Intent(this, StudentProfileActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh Stats dynamically when returning to Dashboard
        binding.tvGpa.text = "3.85"
        binding.tvUnits.text = AppRepository.academicUnits.value.count { it.isRegistered }.toString()
        binding.tvAttendance.text = "92%"
        binding.tvFeeBalance.text = "KES ${String.format("%,.0f", AppRepository.feeRecord.value.balance)}"
    }
}
