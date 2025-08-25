package com.example.cafeora

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        val backToMenuBtn = findViewById<Button>(R.id.btnBackToMenu)

        backToMenuBtn.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }
    }
}
