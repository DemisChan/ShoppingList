package com.udemyAndroid.shoppinglist.domain

class AddToShopListCase(private val shopListRepository: ShopListRepository) {

    suspend fun addShopItem(shopItem: ShopItem) {
        shopListRepository.addShopItem(shopItem)
    }
}