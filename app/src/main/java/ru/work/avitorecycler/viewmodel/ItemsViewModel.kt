package ru.work.avitorecycler.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.work.avitorecycler.data.Item


class ItemsViewModel : ViewModel() {

    private val _mutableItemsList = MutableLiveData<List<Item>>()
    val itemsList: LiveData<List<Item>> get() = _mutableItemsList
    private val poolIds = mutableListOf<Int>()
    private val defCount = 14
    private var id = defCount + 1

    init {
        viewModelScope.launch {
            _mutableItemsList.postValue(generateDefaultItems(defCount))
            while (true) {
                delay(5000)
                if (poolIds.isEmpty()) {
                    _mutableItemsList.postValue(generateRandomPosItem(id))
                    id++
                } else {
                    _mutableItemsList.postValue(generateRandomPosItem(poolIds.removeFirst()))
                }
            }
        }
    }

    fun removeItem(itemId: Int) {
        val list = _mutableItemsList.value!!.toMutableList()
        list.removeAt(itemId)
        poolIds.add(_mutableItemsList.value!![itemId].id)
        _mutableItemsList.postValue(list)
    }

    private suspend fun generateRandomPosItem(id: Int): List<Item> = withContext(Dispatchers.IO) {
        val list = _mutableItemsList.value!!.toMutableList()
        list.add((0.._mutableItemsList.value!!.size).random(), Item(id))
        list
    }

    private suspend fun generateDefaultItems(count: Int): List<Item> = withContext(Dispatchers.IO) {
        val list = mutableListOf<Item>()
        for (i in 0..count) list.add(Item(i))
        list
    }
}