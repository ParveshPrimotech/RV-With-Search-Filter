package com.example.myapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.model.Cryptocurrency
import com.example.myapp.repository.CryptoRepository
import com.example.myapp.ui.main.home.Order
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val cryptocurrencyRepository: CryptoRepository
) : ViewModel() {

    private val cryptoCurrencyEmitter = MutableLiveData<List<Cryptocurrency>>()
    val cryptoCurrency: LiveData<List<Cryptocurrency>> = cryptoCurrencyEmitter
    private var searchJob: Job? = null

    private var searchState : Search = Search.InitialState

    init {
        loadCryptoCurrency()
    }

    private fun loadCryptoCurrency() {
        viewModelScope.launch(Dispatchers.IO) {
            cryptoCurrencyEmitter.postValue(cryptocurrencyRepository.getCryptoCurrency(searchState))
        }
    }

    fun filterList(cryptoName: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(1000)
            searchState = searchState.copy(
                searchQuery = cryptoName
            )
            performSearch()
        }
    }

    fun reorderList(order: Order) {
        viewModelScope.launch {
            searchState = searchState.copy(
                order = order
            )
            performSearch()
        }
    }

    private suspend fun performSearch(){
        withContext(Dispatchers.IO){
            cryptoCurrencyEmitter.postValue(
                cryptocurrencyRepository.getCryptoCurrency(search = searchState)
            )
        }
    }
}

data class Search(
    val searchQuery:String,
    val order: Order
){
    companion object{
        val InitialState = Search(
            searchQuery = "",
            order = Order.ASCENDING
        )
    }
}
