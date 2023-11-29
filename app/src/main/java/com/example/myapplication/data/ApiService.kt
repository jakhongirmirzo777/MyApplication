package com.example.myapplication.data

import com.example.myapplication.data.entities.ProductsData
import retrofit2.http.GET

interface ApiService {

    @GET("records/all?student_id=00013540")
    suspend fun getRecords(): ProductsData
}