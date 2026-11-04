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
import com.example.abdiwelizo.data.User
import com.example.abdiwelizo.data.UserRole
import com.example.abdiwelizo.databinding.ActivityAdminUsersBinding
import com.google.android.material.card.MaterialCardView

class AdminUsersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.btnAddUser.setOnClickListener {
            showAddUserDialog()
        }

        renderUsers()
    }

    private fun showAddUserDialog() {
        val formLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 24, 32, 24)
        }

        val etName = EditText(this).apply { hint = "Full Name (e.g. Dr. Jane Kamau)" }
        val etEmail = EditText(this).apply { hint = "Email Address (e.g. jane@umma.ac.ke)" }
        val etReg = EditText(this).apply { hint = "Reg or Staff Number" }

        formLayout.addView(etName)
        formLayout.addView(etEmail)
        formLayout.addView(etReg)

        AlertDialog.Builder(this)
            .setTitle("Add New University User")
            .setView(formLayout)
            .setPositiveButton("Create User") { _, _ ->
                val name = etName.text.toString().trim()
                val email = etEmail.text.toString().trim()
                val reg = etReg.text.toString().trim()

                if (name.isNotEmpty() && email.isNotEmpty()) {
                    val newUser = User(
                        id = "U${System.currentTimeMillis()}",
                        name = name,
                        email = email,
                        role = UserRole.STUDENT,
                        regOrStaffNumber = reg.ifEmpty { "REG/2025/101" },
                        department = "Department of Computing & IT",
                        programOrDesignation = "BSc in Information Technology",
                        phone = "+254 700 000 000"
                    )
                    AppRepository.addUser(newUser)
                    Toast.makeText(this, "New user $name added successfully!", Toast.LENGTH_SHORT).show()
                    renderUsers()
                } else {
                    Toast.makeText(this, "Please fill in name and email.", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun renderUsers() {
        binding.containerUsers.removeAllViews()
        val users = AppRepository.users.value

        for (u in users) {
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
                text = "${u.name} [Role: ${u.role}]"
                textSize = 16f
                setTypeface(null, Typeface.BOLD)
                setTextColor(0xFF004D25.toInt())
            }

            val tvDetails = TextView(this).apply {
                text = "Email: ${u.email}\nID/Staff No: ${u.regOrStaffNumber}\nDept: ${u.department}\nPhone: ${u.phone}"
                textSize = 13f
                setTextColor(0xFF333333.toInt())
                setPadding(0, 4, 0, 0)
            }

            layout.addView(tvTitle)
            layout.addView(tvDetails)
            card.addView(layout)
            binding.containerUsers.addView(card)
        }
    }
}
