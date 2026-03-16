package com.udemyAndroid.shoppinglist.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetShopListCase @Inject constructor(private val shopListRepository: ShopListRepository) {

    fun getShopList(): LiveData<List<ShopItem>> {
        return shopListRepository.getShopList()
    }
}