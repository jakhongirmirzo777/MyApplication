@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AddScreen(){

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

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        OutlinedTextField(
            label = { Text("Product name") },
            value = firstInput,
            onValueChange = { input ->
                firstInput = input
                isFirstInputValid = input.isNotEmpty()
            },
            isError = !isFirstInputValid && isSubmitTried,
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
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
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
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
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
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
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
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
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )
        if (!isFifthInputValid && isSubmitTried) {
            Text(text = "Please fill in this field", color = Color.Red)
        }

        Button(onClick = {
            isSubmitTried = true
        }) {
            Text(text = "Submit")
        }
    }
}