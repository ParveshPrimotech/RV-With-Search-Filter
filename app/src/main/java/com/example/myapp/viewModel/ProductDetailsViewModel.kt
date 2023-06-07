package com.example.myapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.ApiState
import com.example.myapp.model.ProductModelItem
import com.example.myapp.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProductDetailsViewModel(private val mainRepository: MainRepository) : ViewModel() {

    val apiState = MutableStateFlow(ApiState(ApiState.Status.LOADING, ProductModelItem(), ""))

    fun getProductDetail(id: Int) {
        apiState.value = ApiState.loading()

        viewModelScope.launch(Dispatchers.IO) {
            val response = mainRepository.getProductDetails(id = id)
            if (response.isSuccessful) {
                apiState.value = ApiState.success(response.body())
            } else {
                apiState.value = ApiState.error(response.message())
            }
        }
    }
}