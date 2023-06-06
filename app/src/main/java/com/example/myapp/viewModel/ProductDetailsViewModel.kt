package com.example.myapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.model.ProductModelItem
import com.example.myapp.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductDetailsViewModel(private val mainRepository: MainRepository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val productsList = MutableLiveData<ProductModelItem>()
    val loading : MutableLiveData<Boolean> = MutableLiveData()

    fun getProductDetail(id: Int){
        loading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val response = mainRepository.getProductDetails(id = id)

            if (response.isSuccessful) {
                productsList.postValue(response.body())
                loading.postValue(false)
            } else {
                loading.postValue(false)
                onError("Error : ${response.message()} ")
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.postValue(false)
    }
}