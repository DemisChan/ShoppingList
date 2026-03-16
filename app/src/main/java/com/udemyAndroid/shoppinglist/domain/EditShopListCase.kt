package com.udemyAndroid.shoppinglist.domain

import javax.inject.Inject

class EditShopListCase @Inject constructor(private val shopListRepository: ShopListRepository) {

    suspend fun editShopItem(shopItem: ShopItem) {
        shopListRepository.editShopItem(shopItem)
    }
}