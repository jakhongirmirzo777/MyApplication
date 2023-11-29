package com.example.myapplication.data.entities

data class TextItem (
    val id: Number,
    val record_id: Number,
    val value: String
)

data class Product(
    val id: Number,
    val title: String,
    val description: String,
    val url: String,
    val age: Number,
    val price: Number,
    val type: String,
    val text_list: List<TextItem>
) {
}
