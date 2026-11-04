package com.example.abdiwelizo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.abdiwelizo.data.Announcement
import com.example.abdiwelizo.data.AppRepository
import com.example.abdiwelizo.databinding.ActivityAdminAnnouncementsBinding

class AdminAnnouncementsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminAnnouncementsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminAnnouncementsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.btnPublish.setOnClickListener {
            val title = binding.etTitle.text.toString().trim()
            val category = binding.etCategory.text.toString().trim()
            val content = binding.etContent.text.toString().trim()

            if (title.isNotEmpty() && content.isNotEmpty()) {
                val ann = Announcement(
                    id = "AN${System.currentTimeMillis()}",
                    title = title,
                    category = category.ifEmpty { "General" },
                    content = content,
                    date = "2025-05-19",
                    author = "Academic Registrar & Admin"
                )
                AppRepository.addAnnouncement(ann)
                Toast.makeText(this, "Announcement published! Students will see it in their feed.", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "Please fill in title and content.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
