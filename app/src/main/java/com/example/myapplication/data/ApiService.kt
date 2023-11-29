package com.example.myapplication.data

import com.example.myapplication.data.entities.ProductAdd
import com.example.myapplication.data.entities.ProductAddResponse
import com.example.myapplication.data.entities.ProductsData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("records/all?student_id=00013540")
    suspend fun getRecords(): ProductsData

    @POST("records?student_id=00013540")
    fun postData(@Body postData: ProductAdd): Call<ProductAddResponse>
}