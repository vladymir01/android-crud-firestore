package com.example.simplefirestoreapp.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.simplefirestoreapp.ui.StudentViewModel


enum class TheScreens(){
    Students,
    Add
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenManager(studentViewModel:StudentViewModel = viewModel()){
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = TheScreens.valueOf(
        backStackEntry?.destination?.route?:TheScreens.Students.name
    )
    Scaffold() {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = TheScreens.Students.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = TheScreens.Students.name){
                StudentsScreen(
                    studentViewModel = studentViewModel,
                    doAdd = {navController.navigate(TheScreens.Add.name)}
                )
            }

            composable(route = TheScreens.Add.name){
                AddStudent(
                    studentViewModel = studentViewModel,
                    goBack = {navController.navigate(TheScreens.Students.name)}
                )
            }
        }
    }


}