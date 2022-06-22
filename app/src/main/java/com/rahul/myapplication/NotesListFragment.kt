package com.rahul.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.rahul.myapplication.databinding.FragmentNotesListBinding

class NotesListFragment : Fragment() {
    private lateinit var binding: FragmentNotesListBinding
    lateinit var adapter: NotesAdapter
    private var notesList = mutableListOf<Note>()
    lateinit var  databaseRef:FirebaseFirestore
    lateinit var vieModel:NotesViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_notes_list, container, false)
        databaseRef = FirebaseFirestore.getInstance()

        adapter = NotesAdapter(this.requireContext(),notesList)
        binding.rvNotes.layoutManager = LinearLayoutManager(this.requireContext())
        binding.rvNotes.adapter = adapter

        gotoAddNote()
        observeData()
        return binding.root
    }

    private fun gotoAddNote(){
        binding.apply {
            buttonAdd.setOnClickListener {
                findNavController().navigate(R.id.action_notesListFragment_to_addNoteFragment)
            }
        }
    }

    private fun observeData(){
        vieModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        vieModel.fetchNotes().observe(this.requireActivity(), Observer {
            adapter.differ.submitList(it)
        })
    }

}