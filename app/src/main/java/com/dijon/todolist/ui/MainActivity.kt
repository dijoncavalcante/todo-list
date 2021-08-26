package com.dijon.todolist.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dijon.todolist.MainViewModel
import com.dijon.todolist.databinding.ActivityMainBinding
import com.dijon.todolist.model.data.Task
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObserver()
        setListeners()
    }

    private fun initObserver() {
        mainViewModel.allTasks.observe(this, { tasks ->
            if (tasks.isNotEmpty()) {
                loadingVisibility(false)
                populateList(tasks)
            } else {
                loadingVisibility(true)
                populateList(tasks)
            }
        })
    }

    private fun populateList(tasks: List<Task>) {
        val adapterTask = TaskAdapter(tasks).apply {
            listenerDelete = { callDelete(it) }
            listenerEdit = { callEdit(it) }
        }
        with(binding.rvTasks) {
            setHasFixedSize(true)
            adapter = adapterTask
        }
    }

    private fun loadingVisibility(isLoading: Boolean) {
        binding.includeEmpty.emptyState.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun setListeners() {
        binding.fabAddTask.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddTaskActivity::class.java))
        }
    }

    private fun callEdit(task: Task) {
        Toast.makeText(this@MainActivity, "${task.title}", Toast.LENGTH_SHORT).show()
        val intent = Intent(this@MainActivity, AddTaskActivity::class.java)
        intent.putExtra(AddTaskActivity.TASK_ID, task)
        startActivity(intent)
    }

    private fun callDelete(task: Task) {
        mainViewModel.delete(task.id)
    }

    override fun onResume() {
        super.onResume()
        getAllTasks()
    }

    private fun getAllTasks() {
        mainViewModel.getAllTasks()
    }
}