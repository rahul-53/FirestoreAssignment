package com.rahul.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.rahul.myapplication.databinding.FragmentAddNoteBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddNoteFragment : Fragment() {

    private lateinit var binding:FragmentAddNoteBinding
    private lateinit var fireStore: FirebaseFirestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_add_note, container, false)
        fireStore = FirebaseFirestore.getInstance()

        saveNote()
        return binding.root
    }

    private fun saveNote(){
        binding.apply {
            btnSave.setOnClickListener {
                addNotes()
                findNavController().navigate(R.id.action_addNoteFragment_to_notesListFragment)
            }
        }
    }

    private fun addNotes(){
        val notesDb: CollectionReference =fireStore.collection("Notes")
        val title = binding.etTitle.text.toString()
        val description = binding.etDescription.text.toString()

        val note = Note(title, description)

        lifecycleScope.launch(Dispatchers.IO){
            notesDb.add(note).addOnSuccessListener(OnSuccessListener {
                Toast.makeText(requireContext(), "Note added", Toast.LENGTH_SHORT).show()
            })
                .addOnFailureListener(OnFailureListener {
                    Toast.makeText(requireContext(), "Failed to add Note", Toast.LENGTH_SHORT).show()
                })
        }
    }

}