package com.udemyAndroid.shoppinglist.domain

class DeleteFromShopListCase(private val shopListRepository: ShopListRepository) {

    fun deleteShopItem(shopItem: ShopItem) {
        shopListRepository.deleteShopItem(shopItem)
    }
}