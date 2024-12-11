package com.example.m08_rep1.activities

import adapters.ProjectListAdapter
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.m08_rep1.R
import dataClasses.Project
import dataClasses.Status
import dataClasses.SubTask
import dataClasses.Task
import dataClasses.User

class ProjectViewerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_project_view)

        val user = intent.getSerializableExtra("loggedUser") as User
        Toast.makeText(this, "Welcome back ${user.name}", Toast.LENGTH_SHORT).show()
    }
}