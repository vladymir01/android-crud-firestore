package com.example.simplefirestoreapp.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.simplefirestoreapp.TAG
import com.example.simplefirestoreapp.model.Student
import com.example.simplefirestoreapp.ui.StudentViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun StudentsScreen(
    studentViewModel:StudentViewModel= viewModel(),
    doAdd:()->Unit,
    navController: NavController

){
    val studentList = studentViewModel.listOfElements
    Scaffold(
        floatingActionButton = {
                                    FloatingActionButton(onClick = { doAdd()})
                                    { Text(text = "Add")}
                                }
    )
    {innerPadding->
        LazyColumn(modifier = Modifier.padding(innerPadding)){
            itemsIndexed(
                items = studentList,
                key = {index, item ->
                    item.hashCode()
                }
            ){index, item ->
                val state = rememberDismissState{
                    if (it == DismissValue.DismissedToStart){
                        // HERE we will do the delete Action
                        item?.let { it1 -> studentViewModel.doDeleteStudent(it1.id) }
                    }
                    true
                }
                SwipeToDismiss(
                    dismissThresholds = {FractionalThreshold(0.9f)},
                    state = state,
                    background = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Red)
                                .padding(8.dp)
                        ){
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )
                        }
                    },
                    dismissContent = { item?.let { StudentItem(student = it, navController = navController) } },
                    directions = setOf(DismissDirection.EndToStart)
                )
                Divider()
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentItem(student: Student, navController: NavController){
    ListItem(
        leadingContent = { Icon(Icons.Default.AccountCircle, contentDescription = null)},
        headlineText = { Text(student.name)},
        supportingText = { Text(student.email)},
        trailingContent = { Text(student.level.toString())},
        modifier = Modifier.clickable(onClick = {
            Log.d(TAG, "The selected student id: ${student.id}")
            navController.navigate("Detail/${student.id}")
        })
    )
}