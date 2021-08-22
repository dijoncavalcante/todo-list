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
                populateList(tasks)
                loadingVisibility(false)
            }
        })

        mainViewModel.allTasks.observe(this){
            allTasks ->
            val taskAdapter = TaskAdapter(allTasks).apply {
                onItemClick = {
                    Toast.makeText(this@MainActivity, "$it", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun populateList(tasks: List<Task>) {
        binding.rvTasks.apply {
            hasFixedSize()
            adapter = TaskAdapter(tasks)
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

//    private fun insertListeners() {
//        adapterTaskListAdapter.listenerEdit = {
//            val intent = Intent(this, AddTaskActivity::class.java)
//            intent.putExtra(AddTaskActivity.TASK_ID, it.id)
//            startActivityForResult(intent, CREATE_NEW_TASK)
//        }
//        adapterTaskListAdapter.listenerDelete = {
//            TaskDataSource.deleteTask(it)
//            updateList()
//        }
//    }


}