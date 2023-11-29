package com.example.myapplication.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.data.apiService
import com.example.myapplication.data.entities.Product
import com.example.myapplication.data.entities.ProductData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun SingleScreen(navController: NavHostController) {
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val productId: String? = navBackStackEntry?.arguments?.getString("productId")

    var product by remember { mutableStateOf<Product?>(null) }
    LaunchedEffect(productId) {
        if (!productId.isNullOrBlank()) {
            val call = apiService.getRecord(productId!!)
            call.enqueue(object : Callback<ProductData> {
                override fun onResponse(call: Call<ProductData>, response: Response<ProductData>) {
                    if (response.isSuccessful) {
                        product = response.body()!!.data
                        isLoading = false
                    } else {
                        isError = true
                        isLoading = false
                    }
                }

                override fun onFailure(call: Call<ProductData>, t: Throwable) {
                    isError = true
                    isLoading = false
                }
            })
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when {
            isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            isError -> {
                Text("Error loading data")
            }

            else -> {
                // Display the product title
                Text(product?.title ?: "No title available")
            }
        }
    }
}