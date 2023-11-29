package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.entities.Product
import com.example.myapplication.data.apiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductsViewModel : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.Main)

    init {
        loadData()
    }

    val products = MutableStateFlow<List<Product>>(emptyList())

    private fun loadData() {
        Log.d("jprq", "load data invoked")

        scope.launch {
            withContext(Dispatchers.IO) {
                val records = apiService.getRecords()
                products.emit(records.data)
            }
        }
    }
}