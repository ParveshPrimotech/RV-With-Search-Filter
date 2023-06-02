package com.example.myapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapp.model.Cryptocurrency
import com.example.myapp.repository.CryptocurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val cryptocurrencyRepository: CryptocurrencyRepository
) : ViewModel() {

    private val cryptoCurrencyEmitter = MutableLiveData<List<Cryptocurrency>>()
    val cryptoCurrency: LiveData<List<Cryptocurrency>> = cryptoCurrencyEmitter

    init {
        loadCryptoCurrency()
    }

    private fun loadCryptoCurrency() {
        cryptoCurrencyEmitter.value = cryptocurrencyRepository.getCryptoCurrency()
    }
}