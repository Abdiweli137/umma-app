package com.example.abdiwelizo

import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.abdiwelizo.data.AppRepository
import com.example.abdiwelizo.databinding.ActivityStudentLibraryBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class StudentLibraryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentLibraryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentLibraryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                renderBooks(s?.toString() ?: "")
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        renderBooks("")
    }

    private fun renderBooks(query: String) {
        binding.containerBooks.removeAllViews()
        val books = AppRepository.libraryBooks.value.filter {
            it.title.contains(query, ignoreCase = true) ||
            it.author.contains(query, ignoreCase = true) ||
            it.category.contains(query, ignoreCase = true)
        }

        for (book in books) {
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
                text = book.title
                textSize = 16f
                setTypeface(null, Typeface.BOLD)
                setTextColor(0xFF004D25.toInt())
            }

            val tvInfo = TextView(this).apply {
                text = "Author: ${book.author} | Category: ${book.category}\nLocation: ${book.location}\nAvailable: ${book.availableCopies} of ${book.totalCopies} copies\n\n${book.description}"
                textSize = 13f
                setTextColor(0xFF333333.toInt())
                setPadding(0, 4, 0, 10)
            }

            layout.addView(tvTitle)
            layout.addView(tvInfo)

            val btnReserve = MaterialButton(this).apply {
                text = if (book.isReserved) "Reserved" else if (book.availableCopies > 0) "Reserve Book" else "Out of Stock"
                isEnabled = !book.isReserved && book.availableCopies > 0
                setBackgroundColor(if (book.isReserved) 0xFF707972.toInt() else 0xFF004D25.toInt())
                setTextColor(0xFFFFFFFF.toInt())
                cornerRadius = 10
                setOnClickListener {
                    AppRepository.reserveBook(book.id)
                    Toast.makeText(this@StudentLibraryActivity, "Book reserved! Collect at Library Desk.", Toast.LENGTH_SHORT).show()
                    renderBooks(query)
                }
            }

            layout.addView(btnReserve)
            card.addView(layout)
            binding.containerBooks.addView(card)
        }
    }
}
