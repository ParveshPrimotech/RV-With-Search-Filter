package com.example.myapp.network

import com.example.myapp.model.ProductModelItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("products")
    suspend fun getProducts() : Response<List<ProductModelItem>>

    @GET("products/{productId}")
    suspend fun getProductDetails(@Path("productId") productId: Int) : Response<ProductModelItem>
}