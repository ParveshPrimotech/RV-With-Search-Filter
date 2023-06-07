package com.example.myapp.model

data class ProductModelItem(
    val category: String?=null,
    val description: String?=null,
    val id: Int=0,
    val image: String?=null,
    val price: Double?=null,
    val rating: Rating?=null,
    val title: String?=null
)

data class Rating(
    val count: Int=0,
    val rate: Double?=null
)