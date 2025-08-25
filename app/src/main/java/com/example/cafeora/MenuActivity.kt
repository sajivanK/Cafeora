package com.example.cafeora

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        findViewById<View>(R.id.coffeeCard1).setOnClickListener {
            openDetail(R.string.coffee_title1, R.string.coffee_des1, R.drawable.coffee1, 4.99)
        }
        findViewById<View>(R.id.coffeeCard2).setOnClickListener {
            openDetail(R.string.coffee_title2, R.string.coffee_des2, R.drawable.espresso, 2.49)
        }
        findViewById<View>(R.id.coffeeCard3).setOnClickListener {
            openDetail(R.string.coffee_title3, R.string.coffee_des3, R.drawable.cappuccino, 3.25)
        }
        findViewById<View>(R.id.coffeeCard4).setOnClickListener {
            openDetail(R.string.coffee_title4, R.string.coffee_des4, R.drawable.mocha, 3.99)
        }
        findViewById<View>(R.id.coffeeCard5).setOnClickListener {
            openDetail(R.string.coffee_title5, R.string.coffee_des5, R.drawable.iced_latte, 3.75)
        }
        findViewById<View>(R.id.coffeeCard6).setOnClickListener {
            openDetail(R.string.coffee_title6, R.string.coffee_des6, R.drawable.flat_white, 3.50)
        }
        findViewById<View>(R.id.coffeeCard7).setOnClickListener {
            openDetail(R.string.coffee_title7, R.string.coffee_des7, R.drawable.vanilla_cold_brew, 4.20)
        }
        findViewById<View>(R.id.coffeeCard8).setOnClickListener {
            openDetail(R.string.coffee_title8, R.string.coffee_des8, R.drawable.hazelnut_latte, 3.80)
        }
        findViewById<View>(R.id.coffeeCard9).setOnClickListener {
            openDetail(R.string.coffee_title9, R.string.coffee_des9, R.drawable.macchiato, 3.60)
        }
        findViewById<View>(R.id.coffeeCard10).setOnClickListener {
            openDetail(R.string.coffee_title10, R.string.coffee_des10, R.drawable.affogato, 4.50)
        }


    }

    // Called when a CardView is clicked
    fun goToProductDetail(view: View) {
        val intent = Intent(this, ProductDetailActivity::class.java)
        intent.putExtra("title", getString(R.string.coffee_title2))
        intent.putExtra("description", getString(R.string.coffee_des2))
        intent.putExtra("image", R.drawable.espresso)
        intent.putExtra("basePrice", 2.49)
        startActivity(intent)
    }

    private fun openDetail(titleResId: Int, descResId: Int, imageRes: Int, basePrice: Double) {
        val intent = Intent(this, ProductDetailActivity::class.java)
        intent.putExtra("title", getString(titleResId))
        intent.putExtra("description", getString(descResId))
        intent.putExtra("image", imageRes)
        intent.putExtra("basePrice", basePrice)
        startActivity(intent)
    }

}
