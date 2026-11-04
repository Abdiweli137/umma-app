package com.example.abdiwelizo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.abdiwelizo.databinding.ActivityLecturerMaterialsBinding

class LecturerMaterialsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLecturerMaterialsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLecturerMaterialsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.btnUpload.setOnClickListener {
            val code = binding.etUnitCode.text.toString().trim()
            val title = binding.etTitle.text.toString().trim()
            val url = binding.etUrl.text.toString().trim()

            if (code.isNotEmpty() && title.isNotEmpty()) {
                Toast.makeText(this, "Lecture material '$title' uploaded for $code!", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "Please enter unit code and title.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
