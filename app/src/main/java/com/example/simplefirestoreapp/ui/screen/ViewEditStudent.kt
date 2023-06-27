package com.example.simplefirestoreapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.simplefirestoreapp.ui.StudentViewModel

@Composable
fun ViewEditStudent(
    item:String,
    navController: NavController,
    studentViewModel: StudentViewModel = viewModel()
){
    studentViewModel.doGetOneStudent(id = item)
    val selectedStudent = studentViewModel.selectedStudent

    Column {
        Text(text = "The View Edit Screen")
        Button(onClick = {navController.navigate("Students")}) {
            Text(text = "Go Back")
        }
        Text(text = "The selected student id is:${selectedStudent.id}")
        Text(text = "The selected student name is:${selectedStudent.name}")
        Text(text = "The selected student email is:${selectedStudent.email}")
        Text(text = "The selected student level is:${selectedStudent.level}")
        Text(text = "The selected id level is:${item}")
    }
    
}