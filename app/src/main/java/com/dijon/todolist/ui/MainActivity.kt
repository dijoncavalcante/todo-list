package com.dijon.todolist.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
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
//        binding.fabAddTask.setOnClickListener {
//            startActivityForResult(Intent(this, AddTaskActivity::class.java), CREATE_NEW_TASK)
//        }
//
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
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == CREATE_NEW_TASK && resultCode == Activity.RESULT_OK) updateList()
//    }
//
//    private fun updateList() {
//        val list = mainViewModel._allTasks
//        //TaskDataSource.getList()
//        binding.includeEmpty.emptyState.visibility = if (list.value?.isEmpty()!!) View.VISIBLE else View.GONE
//
//        adapterTaskListAdapter.submitList(mainViewModel.allTasks)
//    }


}