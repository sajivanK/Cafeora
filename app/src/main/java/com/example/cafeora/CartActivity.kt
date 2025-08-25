package com.example.cafeora


import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import java.util.Locale


class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)


        // Get references from layout
        val cartContainer = findViewById<LinearLayout>(R.id.cartItemContainer)
        val totalText = findViewById<TextView>(R.id.cartTotal)


        // Check if cart is empty
        if (CartManager.cartItems.isEmpty()) {
            val emptyText = TextView(this).apply {
                text = getString(R.string.empty_cart_message)
                textSize = 16f
                gravity = Gravity.CENTER
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
            cartContainer.addView(emptyText)


            // Format total using Locale.US (not from string resource)
            val formattedTotal = String.format(Locale.US, "Total: $%.2f", 0.0)
            totalText.text = formattedTotal
            return
        }


        // Add each item to the UI
        for (item in CartManager.cartItems) {
            val itemLayout = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                setPadding(0, 16, 0, 16)
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }


            val image = ImageView(this).apply {
                setImageResource(item.imageRes)
                layoutParams = LinearLayout.LayoutParams(100, 100)
            }


            val info = TextView(this).apply {
                text = getString(
                    R.string.cart_item_info,
                    item.name,
                    item.size,
                    item.quantity,
                    item.totalPrice
                )
                textSize = 16f
                setPadding(16, 0, 0, 0)
            }


            itemLayout.addView(image)
            itemLayout.addView(info)
            cartContainer.addView(itemLayout)
        }


        // Show formatted total using String.format(Locale)
        val totalPrice = CartManager.getTotalPrice()
        val formattedTotal = String.format(Locale.US, "Total: $%.2f", totalPrice)
        totalText.text = formattedTotal


        Toast.makeText(this, getString(R.string.cart_loaded), Toast.LENGTH_SHORT).show()
        val checkoutBtn = findViewById<Button>(R.id.checkoutButton)
        checkoutBtn.setOnClickListener {
            startActivity(Intent(this, CheckoutActivity::class.java))
        }

        val backToMenuBtn = findViewById<Button>(R.id.btnBackToMenu)
        backToMenuBtn.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }



    }
}
