package com.vmbb.myapp.data.local.estandar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<T>(
    private val repository: BaseRepository<T>
) : ViewModel() {

    private val _items = repository.items
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val items: StateFlow<List<T>> = _items

    fun insert(item: T) {
        viewModelScope.launch {
            repository.insert(item)
        }
    }

    fun update(item: T) {
        viewModelScope.launch {
            repository.update(item)
        }
    }

    fun delete(item: T) {
        viewModelScope.launch {
            repository.delete(item)
        }
    }
}