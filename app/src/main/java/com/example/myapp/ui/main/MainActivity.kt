package com.example.myapp.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp.R
import com.example.myapp.model.Cryptocurrency
import com.example.myapp.repository.CryptocurrencyRepository
import com.example.myapp.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observeCryptoCurrency()
    }

    private fun observeCryptoCurrency() {
        viewModel.cryptoCurrency.observe(this) {
            Log.e("taggerValue", "observeCryptoCurrency: $it")
        }
    }
}

class CryptoCurrencyRepoImpl : CryptocurrencyRepository {
    override fun getCryptoCurrency() = listOf(
        Cryptocurrency("Aakash"),
        Cryptocurrency("rakesh"),
        Cryptocurrency("rahul"),
        Cryptocurrency("Harshit")
    )
}