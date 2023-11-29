package com.example.myapplication.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.myapplication.data.entities.Product
import com.example.myapplication.viewmodel.ProductsViewModel


@Composable
fun MainScreen(viewModel: ProductsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){

    val recordsC = viewModel.products.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        items(recordsC.value) { product ->
            OutlinedCard (
                border = BorderStroke(1.dp, Color(android.graphics.Color.parseColor("#D7D7D7"))),
                modifier = Modifier
                    .height(250.dp)
                    .padding(8.dp)
                    .background(Color.White)
            ){
                Image(
                    painter = rememberImagePainter(product.url),
                    contentDescription = product.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(160.dp)
                        .fillMaxWidth()
                        .background(Color.White)
                )
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize()
                        .background(Color.White)

                ) {
                    Text(
                        product.title,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(android.graphics.Color.parseColor("#1D1B20"))
                    )
                    Text(
                        "Vegetable family: ${product.type}",
                        fontSize = 7.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(android.graphics.Color.parseColor("#49454F"))
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        product.description,
                        fontSize = 7.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(android.graphics.Color.parseColor("#49454F"))
                    )
                }
            }
        }
    }
}
