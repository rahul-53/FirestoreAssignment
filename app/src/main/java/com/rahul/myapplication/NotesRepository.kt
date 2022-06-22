package com.rahul.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.platforminfo.LibraryVersionComponent

class NotesRepository {

    fun fetchNotes(): LiveData<MutableList<Note>>{
        val mutableLiveData = MutableLiveData<MutableList<Note>>()
        FirebaseFirestore.getInstance().collection("Notes").get().addOnSuccessListener {
            result ->
            val listData = mutableListOf<Note>()
            for (document in result){
                val title = document.getString("title")
                val description = document.getString("desc")
                val note = Note(title!!, description!!)

                listData.add(note)
            }
            mutableLiveData.value = listData
        }
        return mutableLiveData
    }
}