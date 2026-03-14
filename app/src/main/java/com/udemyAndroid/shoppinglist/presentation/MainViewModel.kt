package com.udemyAndroid.shoppinglist.presentation

// not correct data layer should not be present her but injected
import android.app.Application
import androidx.lifecycle.AndroidViewModel
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