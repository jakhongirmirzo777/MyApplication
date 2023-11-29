package com.example.myapplication.data.entities


data class ProductAddResponse (
    val code: Number,
    val status: String,
    val message: String
)
data class ProductAdd (
    val title: String,
    val description: String,
    val type: String,
    val age: Number,
    val price: Number,
    val text_list: List<String>,
    val url: String
)