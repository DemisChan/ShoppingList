package com.udemyAndroid.shoppinglist.domain

import javax.inject.Inject

class GetShopItemCase @Inject constructor(private val shopListRepository: ShopListRepository) {

    suspend fun getShopItem(shopItemId: Int): ShopItem {
        return shopListRepository.getShopItem(shopItemId)
    }
}