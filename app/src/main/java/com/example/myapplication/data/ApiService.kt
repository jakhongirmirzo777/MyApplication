package com.example.myapplication.data

import com.example.myapplication.data.entities.ProductAdd
import com.example.myapplication.data.entities.ProductAddResponse
import com.example.myapplication.data.entities.ProductData
import com.example.myapplication.data.entities.ProductDeleteResponse
import com.example.myapplication.data.entities.ProductsData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("records/all?student_id=00013540")
    suspend fun getRecords(): ProductsData

    @POST("records?student_id=00013540")
    fun postData(@Body postData: ProductAdd): Call<ProductAddResponse>

    @GET("https://wiutmadcw.uz/api/v1/records/{productId}?student_id=00013540")
    fun getRecord(@Path("productId") productId: String): Call<ProductData>

    @DELETE("https://wiutmadcw.uz/api/v1/records/{productId}?student_id=00013540")
    fun deleteRecord(@Path("productId") productId: String): Call<ProductDeleteResponse>
}