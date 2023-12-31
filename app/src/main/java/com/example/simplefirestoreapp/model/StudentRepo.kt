package com.example.simplefirestoreapp.model

import android.util.Log
import com.example.simplefirestoreapp.TAG
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

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
                    val result = queryDocumentSnapshots.documents
                    for (item in result) {
                        val student: Student? = item.toObject(Student::class.java)
                        student?.id = item.id
                        student?.let { studentList.add(it) }
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

    fun deleteStudent(id: String){
        dbStudent.document(id)
            .delete()
            .addOnSuccessListener { Log.d(TAG, "Document deleted") }
            .addOnFailureListener { e-> Log.d(TAG,"Error deleting...$e") }
    }

    fun getOneStudent(id: String, callback: (student:Student) -> Unit){
        dbStudent.document(id)
            .get()
            .addOnSuccessListener { document ->
                if (document != null){
//                    Log.d(TAG, "Student selected: ${document.id}")
                    val student = document.toObject<Student>()
                    student?.id = document.id
                    student?.let { callback(it) }
                }else{
                    Log.d(TAG, "No such data")
                }
            }
            .addOnFailureListener {exception ->
                Log.d(TAG, "get failed with: ", exception)
            }
    }
}

