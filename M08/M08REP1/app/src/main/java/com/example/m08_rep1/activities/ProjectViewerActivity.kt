package com.example.m08_rep1.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.m08_rep1.R
import dataClasses.User
import fragments.ProjectListFragment
import tools.UserFileHandler
import viewmodels.ProjectsViewModel

class ProjectViewerActivity : AppCompatActivity() {
    private val viewModel: ProjectsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_view)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.projects_fragment, ProjectListFragment())
                .commit()
        }

        val user = intent.getSerializableExtra("loggedUser") as User
        Toast.makeText(this, "Welcome back ${user.name}", Toast.LENGTH_SHORT).show()

        val logOutButton = findViewById<ImageButton>(R.id.button_logout)
        val title = findViewById<TextView>(R.id.projects_title)

        viewModel.selectedProjectName.observe(this) { projectName ->
            title.text = projectName
        }

        logOutButton.setOnLongClickListener() {
            wipeLastValidLogin(this)
            logOut()

            true
        }
        logOutButton.setOnClickListener() {
            Toast.makeText(this, "Long press to Log Out", Toast.LENGTH_SHORT).show()
        }
    }

    private fun logOut() {
        val intent = Intent(
            this@ProjectViewerActivity,
            LoginActivity::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    private fun wipeLastValidLogin(context: Context) {
        UserFileHandler(context).writeUserToFile("lastValidLogin.json", null, true)
    }
}