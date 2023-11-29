package com.example.myapplication.data.entities

import com.example.myapplication.data.entities.Product

data class ProductsData(
    val code: Int,
    val message: String,
    val data: List<Product>,
)