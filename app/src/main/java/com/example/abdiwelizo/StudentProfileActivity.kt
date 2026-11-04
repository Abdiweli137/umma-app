package com.example.abdiwelizo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.abdiwelizo.data.AppRepository
import com.example.abdiwelizo.data.MockData
import com.example.abdiwelizo.databinding.ActivityStudentProfileBinding

class StudentProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }

        val student = MockData.users.find { it.email == "student@umma.ac.ke" } ?: MockData.users[0]

        binding.tvName.text = student.name
        binding.tvRegNo.text = "Reg No: ${student.regOrStaffNumber}"
        binding.tvProgram.text = "Program: ${student.programOrDesignation}"
        binding.tvDept.text = "Department: ${student.department}"
        binding.tvEmail.text = "Email: ${student.email}"
        binding.etPhone.setText(student.phone)

        binding.btnSavePhone.setOnClickListener {
            val newPhone = binding.etPhone.text.toString().trim()
            if (newPhone.isNotEmpty()) {
                val updated = student.copy(phone = newPhone)
                AppRepository.updateUser(updated)
                Toast.makeText(this, "Phone number updated successfully!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnChangePass.setOnClickListener {
            val curr = binding.etCurrPass.text.toString().trim()
            val newP = binding.etNewPass.text.toString().trim()
            if (curr.isNotEmpty() && newP.isNotEmpty()) {
                Toast.makeText(this, "Password updated successfully for demo account!", Toast.LENGTH_SHORT).show()
                binding.etCurrPass.text?.clear()
                binding.etNewPass.text?.clear()
            } else {
                Toast.makeText(this, "Please enter both current and new password.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
