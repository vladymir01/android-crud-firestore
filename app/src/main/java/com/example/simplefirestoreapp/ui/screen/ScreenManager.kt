package com.example.simplefirestoreapp.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
//import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.simplefirestoreapp.ui.StudentViewModel


//enum class TheScreens(){
//    Students,
//    Add,
//    Detail
//}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenManager(studentViewModel:StudentViewModel = viewModel()){
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route?:"Students"

    Scaffold() {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "Students",
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = "Students"){
                StudentsScreen(
                    studentViewModel = studentViewModel,
                    doAdd = {navController.navigate("Add")},
                    navController = navController
                )
            }

            composable(route = "Add"){
                AddStudent(
                    studentViewModel = studentViewModel,
                    goBack = {navController.navigate("Students")}
                )
            }

            composable(
                route = "Detail/{item}",
                arguments = listOf(navArgument("item"){
                    type = NavType.StringType
                    defaultValue = "Default"
                })
            ){navBackStackEntry->
                val itemId = navBackStackEntry.arguments?.getString("item")
                itemId?.let {ViewEditStudent(
                    item = it,
                    navController = navController
                ) }
            }
        }
    }


}