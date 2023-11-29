package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.rememberImagePainter
import com.example.myapplication.data.apiService
import com.example.myapplication.data.entities.Product
import com.example.myapplication.data.entities.ProductAdd
import com.example.myapplication.data.entities.ProductData
import com.google.accompanist.flowlayout.FlowRow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.entities.ProductAddResponse
import com.example.myapplication.data.entities.ProductDeleteResponse

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
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp)
                ) {
                    items(1) {
                        IconButton(
                            onClick = { navController.navigate("main") },
                            modifier = Modifier
                                .size(48.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "sds",
                                tint = Color.Gray
                            )
                        }

                        Image(
                            painter = rememberImagePainter(product!!.url),
                            contentDescription = product!!.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(220.dp)
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(bottom = 12.dp)
                        )

                        Text(
                            product!!.title,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(android.graphics.Color.parseColor("#1F1F1F")),
                            modifier = Modifier
                                .padding(bottom = 16.dp)
                        )
                        Text(
                            "Vegetable Family: ${product!!.type}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(android.graphics.Color.parseColor("#616F7D")),
                            modifier = Modifier
                                .padding(bottom = 16.dp)
                        )
                        Text(
                            product!!.description,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color(android.graphics.Color.parseColor("#1F1F1F")),
                            modifier = Modifier
                                .padding(bottom = 16.dp)
                        )
                        Text(
                            "Nutritions:",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color(android.graphics.Color.parseColor("#FF005C")),
                            modifier = Modifier
                                .padding(bottom = 2.dp)
                        )
                        FlowRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            mainAxisSpacing = 8.dp,
                            crossAxisSpacing = 8.dp,
                        ) {
                            for (nutrition in product!!.text_list) {
                                Text(
                                    nutrition.value,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(android.graphics.Color.parseColor("#969696"))
                                )
                            }
                        }

                        Text(
                            "${product!!.age} days passed since harvested",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color(android.graphics.Color.parseColor("#FB8951")),
                            modifier = Modifier
                                .padding(bottom = 20.dp)
                        )
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(bottom = 30.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Price",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(android.graphics.Color.parseColor("#1F1F1F")),
                                modifier = Modifier.padding(end = 4.dp)
                            )
                            Text(
                                text = "$${product!!.price}",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(android.graphics.Color.parseColor("#1B327D")),
                            )
                        }

                        Row(
                            Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedButton(
                                onClick = {
                                    makeDeleteRequest(product!!.id.toString(), navController)
                                },
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(150.dp)
                                    .padding(end = 12.dp)
                            ) {
                                Text(
                                    text = "Delete",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(android.graphics.Color.parseColor("#FF005C")),
                                )
                            }
                            OutlinedButton(
                                onClick = {
                                    navController.navigate("edit/${product!!.id}")
                                },
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(150.dp)
                            ) {
                                Text(
                                    text = "Edit",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(android.graphics.Color.parseColor("#FB8951"))
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

fun makeDeleteRequest(productId: String, navController: NavHostController) {
    val call = apiService.deleteRecord(productId)
    call.enqueue(object : Callback<ProductDeleteResponse> {
        override fun onResponse(
            call: Call<ProductDeleteResponse>,
            response: Response<ProductDeleteResponse>
        ) {
            if (response.isSuccessful) {
                navController.navigate("main")
            }
        }

        override fun onFailure(call: Call<ProductDeleteResponse>, t: Throwable) {

        }
    })
}