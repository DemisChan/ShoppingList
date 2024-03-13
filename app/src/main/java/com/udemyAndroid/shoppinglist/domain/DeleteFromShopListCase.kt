package com.udemyAndroid.shoppinglist.domain

class DeleteFromShopListCase(private val shopListRepository: ShopListRepository) {

    fun deleteShopItem(shopItemId: Int) {
        shopListRepository.deleteShopItem(shopItemId)
    }
}