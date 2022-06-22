package com.rahul.myapplication

import android.content.Context
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rahul.myapplication.databinding.NotesItemBinding

 class NotesAdapter(val context: Context, noteList:List<Note>):
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    inner class NotesViewHolder(val notesItemBinding: NotesItemBinding)
        :RecyclerView.ViewHolder(notesItemBinding.root){}

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
         val itemLayoutBinding =NotesItemBinding
             .inflate(
                 LayoutInflater
                 .from(parent.context),parent,false)
         return NotesViewHolder(itemLayoutBinding)
     }

     var differCallBack = object :DiffUtil.ItemCallback<Note>() {
         override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
             return oldItem.title == newItem.title
         }
         override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
             return oldItem == newItem
         }
     }
     val differ = AsyncListDiffer(this,differCallBack)


     override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
         val currentNote =differ.currentList[position]
         holder.notesItemBinding.apply {
             tvTitle.text =currentNote.title
             tvDescription.text = currentNote.desc
         }
     }

     override fun getItemCount(): Int {
         return differ.currentList.size
     }
 }