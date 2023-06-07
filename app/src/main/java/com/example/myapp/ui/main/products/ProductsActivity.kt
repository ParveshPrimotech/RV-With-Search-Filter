package com.example.myapp.ui.main.products

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.databinding.ActivityProductsBinding
import com.example.myapp.model.ProductModelItem
import com.example.myapp.network.ApiConstant
import com.example.myapp.repository.MainRepository
import com.example.myapp.ui.main.productDetails.ProductDetailsActivity
import com.example.myapp.ui.main.signup.SignupActivity
import com.example.myapp.viewModel.ProductsViewModel
import com.example.myapp.viewModel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class ProductsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductsBinding
    lateinit var viewModel: ProductsViewModel
    private lateinit var productsAdapter: ProductsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofitService = ApiConstant.getInstance()
        val mainRepository = MainRepository(retrofitService)

        productsAdapter = ProductsListAdapter(
            context = applicationContext,
            itemClick = { position ->
                startActivity(
                    Intent(applicationContext, SignupActivity::class.java).apply {
                        putExtra("position", position)
                    })
            }
        )
        viewModel =
            ViewModelProvider(this, ViewModelFactory(mainRepository))[ProductsViewModel::class.java]

        recyclerviewInit()
    }

    private fun recyclerviewInit() {
        binding.rvProducts.apply {
            layoutManager = LinearLayoutManager(
                applicationContext
            )
            adapter = productsAdapter
        }

        viewModel.loading.observe(this) {
            if (it) {
                binding.pbProducts.visibility = View.VISIBLE
            } else {
                binding.pbProducts.visibility = View.GONE
            }
        }

        viewModel.productsList.observe(this) {
            productsAdapter.submitList(it)
        }
    }
}