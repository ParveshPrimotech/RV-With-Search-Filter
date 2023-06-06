package com.example.myapp.repository

import com.example.myapp.model.Cryptocurrency
import com.example.myapp.viewModel.Search

interface CryptoRepository {
    suspend fun getCryptoCurrency(search: Search): List<Cryptocurrency>
}