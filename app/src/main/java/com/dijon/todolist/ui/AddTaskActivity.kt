package com.dijon.todolist.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.dijon.todolist.R
import com.dijon.todolist.databinding.ActivityAddTaskBinding
import com.dijon.todolist.extensions.format
import com.dijon.todolist.extensions.text
import com.dijon.todolist.model.data.Task
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class AddTaskActivity : AppCompatActivity() {

    private val addTaskViewModel: AddTaskViewModel by viewModel()
    private lateinit var bindingAddTask: ActivityAddTaskBinding
    var _id: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingAddTask = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(bindingAddTask.root)
        setSupportActionBar(bindingAddTask.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent.hasExtra(TASK_ID)) {
            _id = 0
            val task = intent.getSerializableExtra(TASK_ID) as Task
            bindingAddTask.tilTitle.text = task.title
            bindingAddTask.tilDate.text = task.date
            bindingAddTask.tilHour.text = task.hour
            bindingAddTask.tilDescription.text = task.description
            _id = task.id
        }

        insertListeners()
    }

    private fun insertListeners() {
        bindingAddTask.tilDate.editText?.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1
                bindingAddTask.tilDate.text = Date(it + offset).format()
            }
            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
        }

        bindingAddTask.tilHour.editText?.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()
            timePicker.addOnPositiveButtonClickListener {
                val minute =
                    if (timePicker.minute in 0..9) "0${timePicker.minute}" else timePicker.minute
                val hour = if (timePicker.hour in 0..9) "0${timePicker.hour}" else timePicker.hour

                bindingAddTask.tilHour.text = "$hour:$minute"
            }
            timePicker.show(supportFragmentManager, null)
        }

        bindingAddTask.btnCancelTask.setOnClickListener {
            Log.d(TAG, "action cancel task ")
            finish()
        }

        bindingAddTask.btnNewTask.setOnClickListener {
            if (validateFields()) {
                val task = Task(
                    title = bindingAddTask.tilTitle.text,
                    description = bindingAddTask.tilDescription.text,
                    date = bindingAddTask.tilDate.text,
                    hour = bindingAddTask.tilHour.text,
                    id = if (_id > 0) _id else 0
                )

                if (_id!! > 0L) {
                    addTaskViewModel.update(task)
                } else {
                    addTaskViewModel.save(task)
                }
                finish()
            }
        }
    }

    private fun validateFields(): Boolean {
        var fieldOk = true
        if (bindingAddTask.tilTitle.text.isEmpty()) {
            bindingAddTask.tilTitle.error = getString(R.string.validate_title)
            fieldOk = false
        }
        if (bindingAddTask.tilDescription.text.isEmpty()) {
            bindingAddTask.tilDescription.error = getString(R.string.validate_description)
            fieldOk = false
        }
        if (bindingAddTask.tilDate.text.isEmpty()) {
            bindingAddTask.tilDate.error = getString(R.string.validate_date)
            fieldOk = false
        }
        if (bindingAddTask.tilHour.text.isEmpty()) {
            bindingAddTask.tilHour.error = getString(R.string.validate_hour)
            fieldOk = false
        }
        return fieldOk
    }

    companion object {
        const val TASK_ID = "task_id"
        const val TAG = "AddTaskActivity"
    }
}

