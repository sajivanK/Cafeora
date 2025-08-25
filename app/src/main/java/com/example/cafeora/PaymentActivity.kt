package com.example.cafeora

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val nameInput = findViewById<EditText>(R.id.inputName)
        val cardInput = findViewById<EditText>(R.id.inputCard)
        val expiryInput = findViewById<EditText>(R.id.inputExpiry)
        val cvvInput = findViewById<EditText>(R.id.inputCVV)
        val payButton = findViewById<Button>(R.id.btnPlaceOrder)

        payButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val card = cardInput.text.toString().trim()
            val expiry = expiryInput.text.toString().trim()
            val cvv = cvvInput.text.toString().trim()

            if (name.isEmpty() || card.length != 16 || expiry.length != 5 || cvv.length != 3) {
                Toast.makeText(this, "Please fill in valid payment details.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Payment Successful!", Toast.LENGTH_LONG).show()

                // Optional: Clear cart
                CartManager.clearCart()

                // Go back to Menu or show a Thank You screen
                // ✅ Navigate to custom success screen
                val intent = Intent(this, SuccessActivity::class.java)
                startActivity(intent)
                finish()

            }
        }
    }
}
