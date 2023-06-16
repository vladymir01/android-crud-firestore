package com.example.simplefirestoreapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
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
import androidx.compose.ui.Modifier
import com.example.simplefirestoreapp.model.Student
import com.example.simplefirestoreapp.ui.StudentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentsScreen(studentViewModel:StudentViewModel,doAdd:()->Unit){
    Scaffold(
        floatingActionButton = {
                                    FloatingActionButton(onClick = { doAdd()})
                                    { Text(text = "Add")}
                                }
    )
    { innerPadding->
        Column(modifier = Modifier
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())) {
            Text(text = "The list of students")
            studentViewModel.studentList.forEach {
                it?.let { it1 ->
                    ListItem(
                        leadingContent = { Icon(Icons.Default.AccountCircle, contentDescription = null)},
                        headlineText = { Text(text = it1.name)},
                        supportingText = { Text(text = it1.email)},
                        trailingContent = { Text(text = it1.level.toString())}
                    )
                    Divider()
                }
            }

        }
    }


}