package com.example.myapp.repository

import com.example.myapp.network.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getProductsList() = retrofitService.getProducts()

    suspend fun getProductDetails(id : Int) = retrofitService.getProductDetails(id)
}