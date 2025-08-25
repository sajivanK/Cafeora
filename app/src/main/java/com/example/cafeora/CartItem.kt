package com.example.cafeora

data class CartItem(
    val name: String,
    val size: String,
    var quantity: Int,
    var totalPrice: Double,
    val imageRes: Int
)
