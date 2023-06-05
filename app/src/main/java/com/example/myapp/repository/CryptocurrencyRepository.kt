package com.example.myapp.repository

import com.example.myapp.model.Cryptocurrency
import com.example.myapp.viewModel.Search

interface CryptocurrencyRepository {
    suspend fun getCryptoCurrency(search: Search): List<Cryptocurrency>
}