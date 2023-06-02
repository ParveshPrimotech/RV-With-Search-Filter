package com.example.myapp.repository

import com.example.myapp.model.Cryptocurrency

interface CryptocurrencyRepository {
    fun getCryptoCurrency(): List<Cryptocurrency>
}