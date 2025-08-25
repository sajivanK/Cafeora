package com.example.cafeora

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class CheckoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        val summaryText = findViewById<TextView>(R.id.checkoutSummary)
        val confirmBtn = findViewById<Button>(R.id.confirmCheckoutBtn)

        // Show summary of cart
        val summary = StringBuilder()
        for (item in CartManager.cartItems) {
            summary.append("${item.name} (${item.size}) x${item.quantity} - $${String.format(Locale.US, "%.2f", item.totalPrice)}\n")
        }
        summary.append("\nTotal: $${String.format(Locale.US, "%.2f", CartManager.getTotalPrice())}")
        summaryText.text = summary.toString()

        // ✅ Go to payment screen instead of placing order immediately
        confirmBtn.setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
        }
    }
}
