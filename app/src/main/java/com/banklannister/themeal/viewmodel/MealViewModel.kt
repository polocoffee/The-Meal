package com.banklannister.themeal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.banklannister.themeal.model.Category
import com.banklannister.themeal.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MealViewModel : ViewModel() {

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories = _categories.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    init {
        fetchData()
    }


    private fun fetchData() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.network.getCategories()
                if (response.isSuccessful) {
                    response.body()?.let { itemsResponse ->
                        _categories.value = itemsResponse.categories
                    }
                } else {
                    _error.value = "Error ${response.code()} ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = "Error: ${e.message}"
            }
        }
    }


}