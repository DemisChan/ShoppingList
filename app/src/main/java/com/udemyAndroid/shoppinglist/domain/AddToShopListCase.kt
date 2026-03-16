package com.udemyAndroid.shoppinglist.domain

import javax.inject.Inject

class AddToShopListCase @Inject constructor(private val shopListRepository: ShopListRepository) {

    suspend fun addShopItem(shopItem: ShopItem) {
        shopListRepository.addShopItem(shopItem)
    }
}