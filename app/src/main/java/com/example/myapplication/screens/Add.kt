@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication.screens

import androidx.compose.foundation.layout.Arrangement
import com.google.accompanist.flowlayout.FlowRow
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.data.apiService
import com.example.myapplication.data.entities.ProductAdd
import com.example.myapplication.data.entities.ProductAddResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.compose.runtime.derivedStateOf

@Composable
fun AddScreen(navController: NavHostController) {
    var isSubmitTried by remember { mutableStateOf(false) }

    var firstInput by remember { mutableStateOf("") }
    var isFirstInputValid by remember { mutableStateOf(false) }

    var secondInput by remember { mutableStateOf("") }
    var isSecondInputValid by remember { mutableStateOf(false) }

    var thirdInput by remember { mutableStateOf("") }
    var isThirdInputValid by remember { mutableStateOf(false) }

    var fourthInput by remember { mutableStateOf("") }
    var isFourthInputValid by remember { mutableStateOf(false) }

    var fifthInput by remember { mutableStateOf("") }
    var isFifthInputValid by remember { mutableStateOf(false) }

    var sixthInput by remember { mutableStateOf("") }
    var isSixthInputValid by remember { mutableStateOf(false) }

    var seventhInput by remember { mutableStateOf("") }
    var isSeventhInputValid by remember { mutableStateOf(false) }

    val (checkedState, onStateChange) = remember { mutableStateOf(false) }

    var nutritionList by remember { mutableStateOf(arrayOf<String>()) }

    val areAllInputsValid: Boolean by remember {
        derivedStateOf {
            firstInput.isNotEmpty() &&
            secondInput.isNotEmpty() &&
            thirdInput.isNotEmpty() &&
            fourthInput.isNotEmpty() &&
            fifthInput.isNotEmpty() &&
            seventhInput.isNotEmpty() &&
            nutritionList.isNotEmpty()
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(1) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
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
                Text(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    text = "Add New Product",
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            OutlinedTextField(
                label = { Text("Product name") },
                value = firstInput,
                onValueChange = { input ->
                    firstInput = input
                    isFirstInputValid = input.isNotEmpty()
                },
                isError = !isFirstInputValid && isSubmitTried,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            if (!isFirstInputValid && isSubmitTried) {
                Text(text = "Please fill in this field", color = Color.Red)
            }

            OutlinedTextField(
                label = { Text("Description") },
                value = secondInput,
                onValueChange = { input ->
                    secondInput = input
                    isSecondInputValid = input.isNotEmpty()
                },
                isError = !isSecondInputValid && isSubmitTried,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            if (!isSecondInputValid && isSubmitTried) {
                Text(text = "Please fill in this field", color = Color.Red)
            }

            OutlinedTextField(
                label = { Text("Vegetable family") },
                value = thirdInput,
                onValueChange = { input ->
                    thirdInput = input
                    isThirdInputValid = input.isNotEmpty()
                },
                isError = !isThirdInputValid && isSubmitTried,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            if (!isThirdInputValid && isSubmitTried) {
                Text(text = "Please fill in this field", color = Color.Red)
            }

            OutlinedTextField(
                label = { Text("When harvested") },
                value = fourthInput,
                onValueChange = { input ->
                    fourthInput = input
                    isFourthInputValid = input.isNotEmpty()
                },
                isError = !isFourthInputValid && isSubmitTried,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            if (!isFourthInputValid && isSubmitTried) {
                Text(text = "Please fill in this field", color = Color.Red)
            }

            OutlinedTextField(
                label = { Text("Price") },
                value = fifthInput,
                onValueChange = { input ->
                    fifthInput = input
                    isFifthInputValid = input.isNotEmpty()
                },
                isError = !isFifthInputValid && isSubmitTried,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            if (!isFifthInputValid && isSubmitTried) {
                Text(text = "Please fill in this field", color = Color.Red)
            }

            OutlinedTextField(
                label = { Text("Product image url") },
                value = seventhInput,
                onValueChange = { input ->
                    seventhInput = input
                    isSeventhInputValid = input.isNotEmpty()
                },
                isError = !isSeventhInputValid && isSubmitTried,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            if (!isSeventhInputValid && isSubmitTried) {
                Text(text = "Please fill in this field", color = Color.Red)
            }

            Column(modifier = Modifier.padding(bottom = 16.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    OutlinedTextField(
                        label = { Text("Nutrition") },
                        value = sixthInput,
                        onValueChange = { input ->
                            sixthInput = input
                            isSixthInputValid = input.isNotEmpty()
                        },
                        isError = !isSixthInputValid && isSubmitTried,
                        modifier = Modifier
                            .width(250.dp)
                            .padding(end = 16.dp)
                    )
                    Button(
                        enabled = sixthInput.isNotBlank(),
                        onClick = {
                            nutritionList += sixthInput
                            sixthInput = ""
                        },
                        modifier = Modifier
                            .height(40.dp)
                    ) {
                        Text(text = "Add")
                    }
                }

                if (nutritionList.size === 0 && isSubmitTried) {
                    Text(
                        text = "Please fill in this field", color = Color.Red
                    )
                }
            }

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                mainAxisSpacing = 8.dp,
                crossAxisSpacing = 8.dp,
            ) {
                for (nutrition in nutritionList) {
                    InputChipExample(nutrition, onDismiss = {
                        nutritionList = nutritionList.filter { it != nutrition }.toTypedArray()
                    })
                }
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Save as draft?",
                    modifier = Modifier.padding(end = 16.dp)
                )
                Checkbox(
                    checked = checkedState,
                    onCheckedChange = {
                        onStateChange(!checkedState)
                    },
                )
            }

            Button(
                onClick = {
                    isSubmitTried = true
                    if (areAllInputsValid){
                        makePostRequest(
                            ProductAdd(
                                title = firstInput,
                                description = secondInput,
                                type = thirdInput,
                                age = fourthInput.toInt(),
                                price = fifthInput.toInt(),
                                text_list = nutritionList.toList(),
                                url = seventhInput
                            ),
                            navController
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Create a new product")
            }
        }
    }
}


@Composable
fun InputChipExample(
    text: String,
    onDismiss: () -> Unit,
) {
    var enabled by remember { mutableStateOf(true) }
    if (!enabled) return
    InputChip(
        onClick = {
            onDismiss()
        },
        label = { Text(text) },
        selected = enabled,
        trailingIcon = {
            Icon(
                Icons.Default.Close,
                contentDescription = "Localized description",
                Modifier.size(InputChipDefaults.AvatarSize)
            )
        },
    )
}

fun makePostRequest(bodyAdd: ProductAdd, navController: NavHostController) {
    val call = apiService.postData(bodyAdd)
    call.enqueue(object : Callback<ProductAddResponse> {
        override fun onResponse(
            call: Call<ProductAddResponse>,
            response: Response<ProductAddResponse>
        ) {
            if (response.isSuccessful) {
                navController.navigate("main")
            }
        }

        override fun onFailure(call: Call<ProductAddResponse>, t: Throwable) {

        }
    })
}