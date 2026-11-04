package com.example.abdiwelizo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.abdiwelizo.databinding.ActivityLecturerDashboardBinding

class LecturerDashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLecturerDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLecturerDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnViewCourses.setOnClickListener {
            startActivity(Intent(this, LecturerCoursesActivity::class.java))
        }

        binding.btnRecordAttendance.setOnClickListener {
            startActivity(Intent(this, LecturerCoursesActivity::class.java))
        }

        binding.btnGradeAssignments.setOnClickListener {
            startActivity(Intent(this, LecturerGradingActivity::class.java))
        }

        binding.btnUploadMaterials.setOnClickListener {
            startActivity(Intent(this, LecturerMaterialsActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
