package com.udemyAndroid.shoppinglist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class ShopItemViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val getShopItemCase = GetShopItemCase(repository)
    private val editShopListCase = EditShopListCase(repository)
    private val addToShopListCase = AddToShopListCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    private val _errorInputCount = MutableLiveData<Boolean>()
    private val _shopItem = MutableLiveData<ShopItem>()
    private val _shouldCloseScreen = MutableLiveData<Unit>()

    val errorInputName: LiveData<Boolean>
        get() = _errorInputName
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    val shopItem: LiveData<ShopItem>
        get() = _shopItem
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    private val scope = CoroutineScope(Dispatchers.IO)

    fun addToShopList(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)

        if (fieldsValid) {
            scope.launch {
                val shopItem = ShopItem(name = name, count = count, enabled = true)
                addToShopListCase.addShopItem(shopItem)
                finishWork()
            }
        }
    }

    fun editShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)

        if (fieldsValid) {
            _shopItem.value?.let {
                scope.launch {
                    val item = it.copy(name = name, count = count)
                    editShopListCase.editShopItem(item)
                    finishWork()
                }
            }
        }
    }

    fun getShopItem(shopItemId: Int) {
        scope.launch {
            val item = getShopItemCase.getShopItem(shopItemId)
            _shopItem.postValue(item)
        }
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
            _errorInputName.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}