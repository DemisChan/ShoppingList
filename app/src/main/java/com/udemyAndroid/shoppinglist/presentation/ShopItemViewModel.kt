package com.udemyAndroid.shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
// not correct data layer should not be present her but injected
import com.udemyAndroid.shoppinglist.data.ShopListRepositoryImpl
import com.udemyAndroid.shoppinglist.domain.AddToShopListCase
import com.udemyAndroid.shoppinglist.domain.DeleteFromShopListCase
import com.udemyAndroid.shoppinglist.domain.EditShopListCase
import com.udemyAndroid.shoppinglist.domain.GetShopItemCase
import com.udemyAndroid.shoppinglist.domain.GetShopListCase
import com.udemyAndroid.shoppinglist.domain.ShopItem

class ShopItemViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopItemCase = GetShopItemCase(repository)
    private val editShopListCase = EditShopListCase (repository)
    private val addToShopListCase = AddToShopListCase(repository)


    fun addToShopList(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)

        if (fieldsValid) {
            val shopItem = ShopItem(name, count, true)
            addToShopListCase.addShopItem(shopItem)
        }
    }

    fun editShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)

        if (fieldsValid) {
            val shopItem = ShopItem(name, count, true)
            editShopListCase.editShopItem(shopItem)
        }

    }

    fun getShopItem(shopItemId: Int) {
        val item = getShopItemCase.getShopItem(shopItemId)
    }

    private fun parseName(name: String?): String {
        return name?.trim() ?: ""
    }
    private fun parseCount(count: String?): Int {
        return count?.trim()?.toInt() ?: 0
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            // TODO: show error input name
            result = false
        }
        if (count <= 0) {
            // TODO: show error count
            result = false
        }
        return result
    }


}