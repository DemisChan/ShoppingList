package com.udemyAndroid.shoppinglist.domain

import javax.inject.Inject

class DeleteFromShopListCase @Inject constructor(private val shopListRepository: ShopListRepository) {

    suspend fun deleteShopItem(shopItem: ShopItem) {
        shopListRepository.deleteShopItem(shopItem)
    }
}