package com.udemyAndroid.shoppinglist.presentation

import androidx.lifecycle.ViewModel
import com.udemyAndroid.shoppinglist.domain.AddToShopListCase
import com.udemyAndroid.shoppinglist.domain.DeleteFromShopListCase
import com.udemyAndroid.shoppinglist.domain.EditShopListCase
import com.udemyAndroid.shoppinglist.domain.GetShopListCase
import com.udemyAndroid.shoppinglist.domain.ShopItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getShopListCase: GetShopListCase,
    private val deleteShopListCase: DeleteFromShopListCase,
    private val editShopListCase: EditShopListCase,
    private val addToShopListCase: AddToShopListCase,
    ) : ViewModel() {


    val shopList = getShopListCase.getShopList()

    private val scope = CoroutineScope(Dispatchers.IO)


    fun deleteShopList(shopItem: ShopItem) {
        scope.launch {
            deleteShopListCase.deleteShopItem(shopItem)
        }
    }

    fun changeEnabledState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        scope.launch {
            editShopListCase.editShopItem(newItem)
        }
    }


    fun addToShopList(shopItem: ShopItem) {
        scope.launch {
            addToShopListCase.addShopItem(shopItem)
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}