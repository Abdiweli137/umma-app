package com.example.abdiwelizo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.abdiwelizo.data.MockData
import com.example.abdiwelizo.data.UserRole
import com.example.abdiwelizo.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Demo quick fill
        binding.btnDemoStudent.setOnClickListener {
            binding.etEmail.setText("student@umma.ac.ke")
            binding.etPassword.setText("Student123")
        }
        binding.btnDemoLecturer.setOnClickListener {
            binding.etEmail.setText("lecturer@umma.ac.ke")
            binding.etPassword.setText("Lecturer123")
        }
        binding.btnDemoAdmin.setOnClickListener {
            binding.etEmail.setText("admin@umma.ac.ke")
            binding.etPassword.setText("Admin123")
        }
        binding.btnDemoSupport.setOnClickListener {
            binding.etEmail.setText("support@umma.ac.ke")
            binding.etPassword.setText("Support123")
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                showError("Please enter both email and password.")
                return@setOnClickListener
            }

            val expectedPass = MockData.demoCredentials[email]
            if (expectedPass != null && expectedPass == password) {
                binding.tvError.visibility = View.GONE
                routeUser(email)
            } else {
                showError("Invalid demonstration credentials. Please check or use quick buttons.")
            }
        }

        binding.tvForgotPassword.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Forgot Password")
                .setMessage("For demonstration accounts, please contact the University ICT Administrator at ict@umma.ac.ke or use the preset demo credentials.")
                .setPositiveButton("OK", null)
                .show()
        }
    }

    private fun showError(msg: String) {
        binding.tvError.text = msg
        binding.tvError.visibility = View.VISIBLE
    }

    private fun routeUser(email: String) {
        val user = MockData.users.find { it.email == email }
        if (user == null) {
            showError("User profile not found.")
            return
        }

        val intent = when (user.role) {
            UserRole.STUDENT -> Intent(this, StudentDashboardActivity::class.java)
            UserRole.LECTURER -> Intent(this, LecturerDashboardActivity::class.java)
            UserRole.ADMIN -> Intent(this, AdminDashboardActivity::class.java)
            UserRole.SUPPORT -> Intent(this, SupportDashboardActivity::class.java)
        }
        intent.putExtra("USER_EMAIL", email)
        startActivity(intent)
        finish()
    }
}
