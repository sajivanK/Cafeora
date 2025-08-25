package com.example.cafeora

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class ProductDetailActivity : AppCompatActivity() {

    private var basePrice = 0.0
    private var quantity = 1
    private var sizeExtra = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        val backButton = findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener {
            finish() // Goes back to MenuActivity
        }


        // UI elements from layout
        val productTitle = findViewById<TextView>(R.id.productTitle)
        val productDescription = findViewById<TextView>(R.id.productDescription)
        val productImage = findViewById<ImageView>(R.id.productImage)

        val quantityText = findViewById<TextView>(R.id.quantityText)
        val plusBtn = findViewById<Button>(R.id.plusButton)
        val minusBtn = findViewById<Button>(R.id.minusButton)
        val priceText = findViewById<TextView>(R.id.priceText)
        val sizeGroup = findViewById<RadioGroup>(R.id.sizeSelector)
        val addToCartBtn = findViewById<Button>(R.id.addToCartBtn)

        // Get values passed from MenuActivity
        val coffeeTitle = intent.getStringExtra("title")
        val coffeeDescription = intent.getStringExtra("description")
        val coffeeImage = intent.getIntExtra("image", 0)
        basePrice = intent.getDoubleExtra("basePrice", 0.0)

        // Set dynamic content
        productTitle.text = coffeeTitle
        productDescription.text = coffeeDescription
        productImage.setImageResource(coffeeImage)
        quantityText.text = String.format(Locale.US, "%d", quantity)

        // Initial price
        sizeExtra = 0.0
        updatePrice(priceText)

        // Size change listener
        sizeGroup.setOnCheckedChangeListener { _, checkedId ->
            sizeExtra = when (checkedId) {
                R.id.sizeSmall -> 0.0
                R.id.sizeMedium -> 0.5
                R.id.sizeLarge -> 1.0
                else -> 0.0
            }
            updatePrice(priceText)
        }

        // Quantity +
        plusBtn.setOnClickListener {
            quantity++
            quantityText.text = String.format(Locale.US, "%d", quantity)
            updatePrice(priceText)
        }

        // Quantity -
        minusBtn.setOnClickListener {
            if (quantity > 1) {
                quantity--
                quantityText.text = String.format(Locale.US, "%d", quantity)
                updatePrice(priceText)
            }
        }

        // Add to Cart button
        addToCartBtn.setOnClickListener {
            val selectedSize = when (sizeGroup.checkedRadioButtonId) {
                R.id.sizeSmall -> "Small"
                R.id.sizeMedium -> "Medium"
                R.id.sizeLarge -> "Large"
                else -> "Small"
            }

            val totalPrice = (basePrice + sizeExtra) * quantity

            val cartItem = CartItem(
                name = productTitle.text.toString(),
                size = selectedSize,
                quantity = quantity,
                totalPrice = totalPrice,
                imageRes = intent.getIntExtra("image", 0)
            )

            CartManager.addItem(cartItem)
            Toast.makeText(this, "Added to Cart!", Toast.LENGTH_SHORT).show()

            // Optional: navigate to cart
            startActivity(Intent(this, CartActivity::class.java))
        }
    }

    private fun updatePrice(priceView: TextView) {
        val total = (basePrice + sizeExtra) * quantity
        priceView.text = String.format(Locale.US, "$%.2f", total)
    }
}
