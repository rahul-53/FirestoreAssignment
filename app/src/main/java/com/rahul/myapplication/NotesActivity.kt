package com.rahul.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.rahul.myapplication.databinding.ActivityNotesBinding

class NotesActivity : AppCompatActivity() {
    private lateinit var binding:ActivityNotesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_notes)
    }
}