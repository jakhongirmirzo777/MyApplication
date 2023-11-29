package com.example.myapplication.data

import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit


val retrofit = Retrofit.Builder()
    .baseUrl("https://wiutmadcw.uz/api/v1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val apiService = retrofit.create(ApiService::class.java)