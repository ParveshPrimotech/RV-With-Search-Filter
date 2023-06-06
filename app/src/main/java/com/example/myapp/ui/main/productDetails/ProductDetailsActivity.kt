package com.example.myapp.ui.main.productDetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.myapp.R
import com.example.myapp.databinding.ActivityProductDetailsBinding
import com.example.myapp.network.ApiConstant
import com.example.myapp.repository.MainRepository
import com.example.myapp.viewModel.ProductDetailsViewModel
import com.example.myapp.viewModel.ViewModelFactory

class ProductDetailsActivity : AppCompatActivity() {

    lateinit var binding : ActivityProductDetailsBinding
    lateinit var viewModel: ProductDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofitService = ApiConstant.getInstance()
        val mainRepository = MainRepository(retrofitService)

        viewModel =
            ViewModelProvider(this, ViewModelFactory(mainRepository))[ProductDetailsViewModel::class.java]

        val intent = intent
        viewModel.getProductDetail(intent.getIntExtra("position",0))

        viewModel.productsList.observe(this, Observer {
            setImageUsingGlide(it.image)
            binding.tvCategory.text = it.category
            binding.tvDesc.text = it.description
            binding.tvPrice.text = it.price.toString()
            binding.tvTitle.text = it.title
        })
    }

    private fun setImageUsingGlide(image: String) {
        Glide.with(this).load(image).into(binding.ivProduct)
    }
}