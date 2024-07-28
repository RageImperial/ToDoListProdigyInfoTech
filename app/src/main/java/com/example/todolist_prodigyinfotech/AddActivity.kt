package com.example.todolist_prodigyinfotech

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist_prodigyinfotech.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {
            val newItem = binding.editText.text.toString()
            if (newItem.isNotEmpty()) {
                val intent = Intent().apply {
                    putExtra(MainActivity.EXTRA_TODO_ITEM, newItem)
                }
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }
}
