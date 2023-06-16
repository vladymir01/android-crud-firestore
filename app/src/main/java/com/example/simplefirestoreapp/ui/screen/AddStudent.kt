package com.example.simplefirestoreapp.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.simplefirestoreapp.TAG
import com.example.simplefirestoreapp.ui.StudentViewModel

@Composable
fun AddStudent(studentViewModel: StudentViewModel,goBack:()->Unit){
    Column {
        Spacer(modifier = Modifier.height(20.dp))
        MyTextField(
            value = studentViewModel.nameEntered,
            onValueChange = {studentViewModel.getNameEntered(it)},
            placeHolder = "Enter The Student Name" )
        Spacer(modifier = Modifier.height(20.dp))

        MyTextField(
            value = studentViewModel.emailEntered,
            onValueChange = {studentViewModel.getEmailEntered(it)},
            placeHolder = "Enter The Student Email")
        Spacer(modifier = Modifier.height(20.dp))

        MyTextField(
            value = studentViewModel.levelEntered.toString(),
            onValueChange = {studentViewModel.getLevelEntered(it)},
            placeHolder = "Enter The Student Level")
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            studentViewModel.doAddStudent()
            goBack()
        }) {
            Text(text = "Add")
        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(
    value:String,
    onValueChange:(String)->Unit,
    placeHolder:String
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        placeholder = {Text(placeHolder)}

    )
}