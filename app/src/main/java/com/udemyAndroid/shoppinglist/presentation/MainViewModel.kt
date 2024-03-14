package com.udemyAndroid.shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
// not correct data layer should not be present her but injected
import com.udemyAndroid.shoppinglist.data.ShopListRepositoryImpl
import com.udemyAndroid.shoppinglist.domain.DeleteFromShopListCase
import com.udemyAndroid.shoppinglist.domain.EditShopListCase
import com.udemyAndroid.shoppinglist.domain.GetShopListCase
import com.udemyAndroid.shoppinglist.domain.ShopItem

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListCase = GetShopListCase(repository)
    private val deleteShopListCase= DeleteFromShopListCase(repository)
    private val editShopListCase = EditShopListCase (repository)

    val shopList = MutableLiveData<List<ShopItem>>()

    fun getShopList() {
        val list = getShopListCase.getShopList()
        shopList.value = list
    }

    fun deleteShopList(shopItem: ShopItem) {
        deleteShopListCase.deleteShopItem(shopItem)
        getShopList()
    }

    fun changeEnabledState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopListCase.editShopItem(newItem)
        getShopList()
    }
}