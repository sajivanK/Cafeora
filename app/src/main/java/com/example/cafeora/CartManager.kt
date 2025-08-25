package com.example.cafeora

object CartManager {
    val cartItems = mutableListOf<CartItem>()


    fun getTotalPrice(): Double {
        return cartItems.sumOf { it.totalPrice }
    }

    fun clearCart() {
        cartItems.clear()
    }

    fun addItem(item: CartItem) {
        val existingItem = cartItems.find {
            it.name == item.name && it.size == item.size && it.imageRes == item.imageRes
        }

        if (existingItem != null) {
            // Same item, update quantity and price
            val updatedQuantity = existingItem.quantity + item.quantity
            val pricePerUnit = existingItem.totalPrice / existingItem.quantity
            val updatedTotalPrice = pricePerUnit * updatedQuantity

            val updatedItem = existingItem.copy(
                quantity = updatedQuantity,
                totalPrice = updatedTotalPrice
            )

            cartItems.remove(existingItem)
            cartItems.add(updatedItem)
        } else {
            // New item
            cartItems.add(item)
        }
    }


}


