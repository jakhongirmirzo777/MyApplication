package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.screens.AddScreen
import com.example.myapplication.screens.MainScreen
import com.example.myapplication.screens.SingleScreen
import androidx.navigation.NavBackStackEntry

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val path: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NavigationBar()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationBar(){
    val navController = rememberNavController()
    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            path = "main"
        ),
        BottomNavigationItem(
            title = "Drafts",
            selectedIcon = Icons.Filled.ShoppingCart,
            unselectedIcon = Icons.Outlined.ShoppingCart,
            path = "main"
        ),
        BottomNavigationItem(
            title = "Add",
            selectedIcon = Icons.Filled.Add,
            unselectedIcon = Icons.Filled.Add,
            path = "add"
        ),
        BottomNavigationItem(
            title = "Profile",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Filled.Person,
            path = "main"
        )
    )

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    Scaffold (
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                modifier = Modifier
            .border(
                width = 1.dp,
                color = Color(android.graphics.Color.parseColor("#089D40")),
        )
            ) {
                items.forEachIndexed{ index, item ->
                    val textColor = if (index == selectedItemIndex) {
                        Color(android.graphics.Color.parseColor("#089D40"))
                    } else Color(android.graphics.Color.parseColor("#9DA2AA"))
                    NavigationBarItem(
                        label = {
                            Text(
                                text = item.title,
                                color = textColor
                            )
                        },
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            navController.navigate(item.path)
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex){
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = item.title,
                                tint = textColor
                            )
                        })
                }
            }
        }
    ){
        NavHost(
            navController = navController,
            startDestination = "main",
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(bottom = 100.dp)
        ) {
            composable("main") {
                MainScreen(navController = navController )
            }
            composable("add") {
                AddScreen(navController = navController )
            }
            composable("single/{productId}") {
                SingleScreen(navController = navController)
            }
        }
    }
}