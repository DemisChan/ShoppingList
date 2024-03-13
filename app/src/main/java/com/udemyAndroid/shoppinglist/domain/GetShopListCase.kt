package com.udemyAndroid.shoppinglist.domain

class GetShopListCase(private val shopListRepository: ShopListRepository) {

    fun getShopList(): List<ShopItem> {
        return shopListRepository.getShopList()
    }
}