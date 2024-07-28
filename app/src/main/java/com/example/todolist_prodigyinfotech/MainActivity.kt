package com.example.todolist_prodigyinfotech

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist_prodigyinfotech.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter
    private val todoList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        todoAdapter = TodoAdapter(todoList) { position ->
            deleteItem(position)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = todoAdapter
        }

        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK) {
            val newItem = data?.getStringExtra(EXTRA_TODO_ITEM)
            newItem?.let {
                todoList.add(it)
                todoAdapter.notifyItemInserted(todoList.size - 1)
            }
        }
    }

    private fun deleteItem(position: Int) {
        todoList.removeAt(position)
        todoAdapter.notifyItemRemoved(position)
    }

    companion object {
        const val REQUEST_CODE_ADD = 1
        const val EXTRA_TODO_ITEM = "EXTRA_TODO_ITEM"
    }
}
