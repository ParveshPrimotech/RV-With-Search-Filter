package com.example.myapp.ui.main

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.R
import com.example.myapp.databinding.ActivityMainBinding
import com.example.myapp.databinding.CryptoListOrderBinding
import com.example.myapp.model.Cryptocurrency
import com.example.myapp.repository.CryptocurrencyRepository
import com.example.myapp.viewModel.MainViewModel
import com.example.myapp.viewModel.Search
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    
    var alertDialog: AlertDialog ?=null

    private var cryptoAdapter : CryptoListAdapter  = CryptoListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerviewInit()
        initDialogBinding()

        binding.ivListOrder.setOnClickListener {
            alertDialog?.show()
        }

        binding.etFilterList.addTextChangedListener {
            viewModel.filterList(it.toString())
        }
    }

    private fun initDialogBinding(){

        val alert: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        val mView = layoutInflater.inflate(R.layout.crypto_list_order,null)
        alert.setView(mView)
        alertDialog  = alert.create()
        alertDialog?.setCanceledOnTouchOutside(false)

        mView?.findViewById<RadioGroup>(R.id.radioGroup)?.setOnCheckedChangeListener { _, _ ->
            alertDialog?.dismiss()

            if (mView.findViewById<RadioButton>(R.id.rbAscendingOrder)?.isChecked!!) {
                viewModel.reorderList(Order.ASCENDING)
            } else {
                viewModel.reorderList(Order.DESCEDING)
            }
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
    private val list = listOf(
        Cryptocurrency("bnb"),
        Cryptocurrency("bitcoin"),
        Cryptocurrency("tether"),
        Cryptocurrency("ethereum"),
        Cryptocurrency("dogecoin"),
        Cryptocurrency("cardano"),
        Cryptocurrency("polygon")
    )

    override suspend fun getCryptoCurrency(
      search:Search
    ): List<Cryptocurrency> {
        return list.filter {
            it.name.contains(search.searchQuery)
        }.let { filteredList ->
            when (search.order) {
                Order.ASCENDING -> {
                    filteredList.sortedBy {
                        it.name
                    }
                }
                Order.DESCEDING -> {
                    filteredList.sortedByDescending {
                        it.name
                    }
                }
            }
        }
    }
}

enum class Order{
    ASCENDING,
    DESCEDING
}

/*
 private var dialogBinding : CryptoListOrderBinding ?=null
 private var dialogView : View?=null
 var mDialog : Dialog ?=null
 
 dialogBinding = CryptoListOrderBinding.inflate(LayoutInflater.from(this))
 mDialog = Dialog(this@MainActivity,R.style.DialogTheme)
 mDialog?.setCancelable(true)
 dialogView = dialogBinding?.root
 mDialog?.setContentView(dialogView!!)*/
