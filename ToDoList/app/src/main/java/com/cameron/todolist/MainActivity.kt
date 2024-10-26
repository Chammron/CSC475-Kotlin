package com.cameron.todolist

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import android.app.Activity

class MainActivity : Activity() {

    //Database helper and UI init later.
    private lateinit var dbHelper: TodoDatabaseHelper
    private lateinit var taskListLayout: LinearLayout
    private lateinit var taskInput: EditText
    private lateinit var addButton: Button

    //Basic layout and init database and UI. Also defines buttons.
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //See asso. XML file.
        setContentView(R.layout.activity_main)
        dbHelper = TodoDatabaseHelper(this)
        taskListLayout = findViewById(R.id.taskListLayout)
        taskInput = findViewById(R.id.taskInput)
        addButton = findViewById(R.id.addButton)

        //If entry is valid, button adds user input.
        addButton.setOnClickListener {

            val task = taskInput.text.toString()

            if (task.isNotEmpty()) {

                dbHelper.addTask(task)
                taskInput.text.clear()
                loadTasks()
            } else {

                Toast.makeText(this, "Please, enter a task before adding one.", Toast.LENGTH_SHORT).show()
            }
        }
        loadTasks()
    }

    //Retrieves from DB and displays to screen
    private fun loadTasks() {

        taskListLayout.removeAllViews()
        val tasks = dbHelper.getTasks()

        //Goes through the task and creates UI and adds to layout.
        for (task in tasks) {

            val taskView = layoutInflater.inflate(R.layout.taskitem, null)
            val checkBox = taskView.findViewById<CheckBox>(R.id.taskCheckBox)
            val deleteButton = taskView.findViewById<Button>(R.id.deleteButton)

            checkBox.text = task.name
            checkBox.isChecked = task.isCompleted

            //Complete if checked.
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                dbHelper.updateTaskStatus(task.id, isChecked)
            }

            //Deletes task from list.
            deleteButton.setOnClickListener {
                dbHelper.deleteTask(task.id)
                loadTasks()
            }

            taskListLayout.addView(taskView)
        }
    }

    //Task: ID, Name, & status.
    data class Task(val id: Int, val name: String, val isCompleted: Boolean)

    //This manages SQLite functions; i.e. reading and updating.
    class TodoDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

        //Begin Table on start.
        override fun onCreate(db: SQLiteDatabase) {

            db.execSQL(CREATE_TABLE)
        }

        //Handles changes.
        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

            db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(db)
        }

        //New tasks are entered into DB and set to not complete.
        fun addTask(name: String) {

            val db = writableDatabase

            val values = ContentValues().apply {

                put(COLUMN_NAME, name)
                put(COLUMN_COMPLETED, 0)
            }

            db.insert(TABLE_NAME, null, values)
            db.close()
        }

        //Gets all tasks and returns as objects.
        fun getTasks(): List<Task> {

            val tasks = mutableListOf<Task>()
            val db = readableDatabase
            val cursor = db.query(TABLE_NAME, null, null, null, null, null, null)

            with(cursor) {
                while (moveToNext()) {

                    val id = getInt(getColumnIndexOrThrow(COLUMN_ID))
                    val name = getString(getColumnIndexOrThrow(COLUMN_NAME))
                    val isCompleted = getInt(getColumnIndexOrThrow(COLUMN_COMPLETED)) > 0
                    tasks.add(Task(id, name, isCompleted))
                }
                close()
            }
            db.close()
            return tasks
        }

        //Updates tasks when checked.
        fun updateTaskStatus(id: Int, isCompleted: Boolean) {

            val db = writableDatabase

            val values = ContentValues().apply {

                put(COLUMN_COMPLETED, if (isCompleted) 1 else 0)
            }
            db.update(TABLE_NAME, values, "$COLUMN_ID = ?", arrayOf(id.toString()))
            db.close()
        }

        //Removes task when button pressed.
        fun deleteTask(id: Int) {

            val db = writableDatabase
            db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(id.toString()))
            db.close()
        }

        //Defines DB/Table constants.
        companion object {

            private const val DATABASE_VERSION = 1
            private const val DATABASE_NAME = "TodoList.db"
            private const val TABLE_NAME = "tasks"
            private const val COLUMN_ID = "id"
            private const val COLUMN_NAME = "name"
            private const val COLUMN_COMPLETED = "completed"

            private const val CREATE_TABLE = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_NAME TEXT, $COLUMN_COMPLETED INTEGER)"
        }
    }
}