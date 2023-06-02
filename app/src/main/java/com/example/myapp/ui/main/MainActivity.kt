package com.example.myapp.ui.main

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.R
import com.example.myapp.databinding.ActivityMainBinding
import com.example.myapp.databinding.CryptoListOrderBinding
import com.example.myapp.model.Cryptocurrency
import com.example.myapp.repository.CryptocurrencyRepository
import com.example.myapp.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    private var cryptoAdapter : CryptoListAdapter  = CryptoListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerviewInit()

        binding.etFilterList.addTextChangedListener {
            viewModel.filterList(it.toString())
        }
    }

    private fun recyclerviewInit() {
        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(
                applicationContext
            )
            adapter = cryptoAdapter
        }

        viewModel.cryptoCurrency.observe(this) {
            cryptoAdapter.submitList(it)
        }
    }
}

class CryptoCurrencyRepoImpl : CryptocurrencyRepository {
    override fun getCryptoCurrency() = listOf(
        Cryptocurrency("bnb"),
        Cryptocurrency("bitcoin"),
        Cryptocurrency("tether"),
        Cryptocurrency("ethereum"),
        Cryptocurrency("dogecoin"),
        Cryptocurrency("cardano"),
        Cryptocurrency("polygon")
    )
}