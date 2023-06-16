package com.example.simplefirestoreapp.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.simplefirestoreapp.TAG
import com.example.simplefirestoreapp.model.Student
import com.example.simplefirestoreapp.model.StudentRepo
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class StudentViewModel: ViewModel() {

    //region properties

    var studentList = mutableStateListOf<Student?>()
    var nameEntered by mutableStateOf("")
        private set
    var emailEntered by mutableStateOf("")
        private set
    var levelEntered by mutableStateOf("")
        private set

    fun getNameEntered(name:String){
        nameEntered = name
    }
    fun getEmailEntered(email:String){
        emailEntered = email
    }
    fun getLevelEntered(level:String){
        levelEntered = level
    }

    //endregion


    //function to be called when the user cliks on the add button
    fun doAddStudent(){
        val newStudent = Student(name = nameEntered,email = emailEntered,level = levelEntered.toInt())
        Log.d(TAG, "$newStudent")
        addToFirebase(student = newStudent)
    }

    //Function to actually add the student in Firebase
    private fun addToFirebase(student:Student){
        StudentRepo().addStudentToFirebase(student)
        //Empty the list before getting the list from the database
        studentList = mutableStateListOf()
        StudentRepo().doGetStudents { students ->
            studentList.addAll(students)
        }

    }

    init {
        StudentRepo().doGetStudents { students ->
            studentList.addAll(students)
        }
    }
}