package com.rahul.myapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class NotesViewModel: ViewModel() {

    val firStore = FirebaseFirestore.getInstance()
    val repo = NotesRepository()
    fun fetchNotes():LiveData<MutableList<Note>>{
        val mutableLiveData = MutableLiveData<MutableList<Note>>()
        repo.fetchNotes().observeForever { notes ->
            mutableLiveData.value = notes
        }
        return mutableLiveData
    }

}