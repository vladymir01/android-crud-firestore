package com.example.simplefirestoreapp.model

import android.util.Log
import com.example.simplefirestoreapp.TAG
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class StudentRepo() {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val dbStudent: CollectionReference = db.collection("Students")

    fun addStudentToFirebase(student: Student){
        dbStudent.add(student).addOnSuccessListener {
            Log.d(TAG, "Adding succeeded")
        }.addOnFailureListener{
            Log.d(TAG, "Failed to add")
        }

    }

     fun doGetStudents(callback: (List<Student>) -> Unit) {
         dbStudent.get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                val studentList = mutableListOf<Student>()
                if (!queryDocumentSnapshots.isEmpty) {
                    val list = queryDocumentSnapshots.documents
                    for (d in list) {
                        val c: Student? = d.toObject(Student::class.java)
                        c?.let { studentList.add(it) }
                    }
                    callback(studentList)
                } else {
                    Log.d(TAG, "No data found in Database")
                    callback(emptyList()) // Return an empty list if no data found
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "Failed to get the data")
                callback(emptyList()) // Return an empty list on failure
            }
    }

}