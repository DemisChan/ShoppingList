package com.udemyAndroid.shoppinglist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
// not correct data layer should not be present her but injected
import com.udemyAndroid.shoppinglist.data.ShopListRepositoryImpl
import com.udemyAndroid.shoppinglist.domain.AddToShopListCase
import com.udemyAndroid.shoppinglist.domain.DeleteFromShopListCase
import com.udemyAndroid.shoppinglist.domain.EditShopListCase
import com.udemyAndroid.shoppinglist.domain.GetShopListCase
import com.udemyAndroid.shoppinglist.domain.ShopItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    // not correct but works
    private val repository = ShopListRepositoryImpl(application)

    private val getShopListCase = GetShopListCase(repository)
    private val deleteShopListCase = DeleteFromShopListCase(repository)
    private val editShopListCase = EditShopListCase(repository)
    private val addToShopListCase = AddToShopListCase(repository)

    val shopList = getShopListCase.getShopList()

    fun deleteShopList(shopItem: ShopItem) {
        viewModelScope.launch {
            deleteShopListCase.deleteShopItem(shopItem)
        }
    }

    fun changeEnabledState(shopItem: ShopItem) {
        viewModelScope.launch {
            val newItem = shopItem.copy(enabled = !shopItem.enabled)
            editShopListCase.editShopItem(newItem)
        }
    }

    fun addToShopList(shopItem: ShopItem) {
        viewModelScope.launch {
            addToShopListCase.addShopItem(shopItem)
        }
    }
}