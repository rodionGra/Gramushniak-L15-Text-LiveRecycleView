package com.a7acdhmwtext_liverecycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.a7acdhmwtext_liverecycleview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val s1: String = binding.editTextFirstName.text.toString().trim()
            val s2: String = binding.editTextSecondName.text.toString().trim()
            binding.btnAddUser.isEnabled = s1.isNotEmpty() && s2.isNotEmpty()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupListeners()
        setupRecycleView()
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private val adapter = PersonAdapter()
    private fun setupRecycleView() {
        binding.recycleViewPerson.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter.list = Person.listOfPerson
        binding.recycleViewPerson.adapter = adapter
    }

    private fun setupListeners() {
        binding.btnAddUser.setOnClickListener {
            addPerson()
        }
        binding.editTextFirstName.addTextChangedListener(textWatcher)
        binding.editTextSecondName.addTextChangedListener(textWatcher)
    }

    private fun addPerson() {
        val stringFirstName = binding.editTextFirstName.text.toString()
        val stringSecondName = binding.editTextSecondName.text.toString()

        if (isUpperCase(stringFirstName, stringSecondName)) {
            Person.listOfPerson.add(Person(stringFirstName, stringSecondName))
            clearAllEditText()
            updateRecycleView()
            binding.editTextSecondName.onEditorAction(EditorInfo.IME_ACTION_DONE)
        }
        else Toast.makeText(this, "First later must be uppercase", Toast.LENGTH_SHORT).show()
    }

    private fun isUpperCase(firstString: String, secondString: String): Boolean {
        return Character.isUpperCase(firstString.codePointAt(0)) && Character.isUpperCase(secondString.codePointAt(0))
    }

    private fun updateRecycleView() {
        //adapter.notifyDataSetChanged() reset all recycleView
        adapter.notifyItemInserted(Person.listOfPerson.size)
    }

    private fun clearAllEditText() {
        binding.editTextFirstName.text?.clear()
        binding.editTextSecondName.text?.clear()
    }
}