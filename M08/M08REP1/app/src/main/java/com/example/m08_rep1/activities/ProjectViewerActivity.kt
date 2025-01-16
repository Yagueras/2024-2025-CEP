package com.example.m08_rep1.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.m08_rep1.R
import com.google.android.material.internal.ContextUtils.getActivity
import dataClasses.User
import fragments.ProjectListFragment
import tools.UserFileHandler
import viewmodels.ProjectsViewModel

class ProjectViewerActivity : AppCompatActivity(), View.OnClickListener {
    private val viewModel: ProjectsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_view)

        if (viewModel.fragmentDepth.value == 0) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.projects_fragment, ProjectListFragment())
                .commit()
        }

        val user = intent.getSerializableExtra("loggedUser") as User
        Toast.makeText(this, "Welcome back ${user.name}", Toast.LENGTH_SHORT).show()

        val logOutButton = findViewById<ImageButton>(R.id.button_logout)
        val title = findViewById<TextView>(R.id.projects_title)

        viewModel.titleCardValue.observe(this) { projectName ->
            title.text = projectName
        }

        logOutButton.setOnLongClickListener() {
            wipeLastValidLogin(this)
            logOut()
            true
        }

        logOutButton.setOnClickListener(this)
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

    override fun onClick(v: View?) {
        viewModel.fragmentDepth.observe(this) { mode ->
            Toast.makeText(this, "Mode is set to ${viewModel.fragmentDepth.value}", Toast.LENGTH_SHORT).show()
        }
        if(viewModel.fragmentDepth.value == 0){
            Toast.makeText(this, "Long press to Log Out", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.validDepth()
            supportFragmentManager.restoreBackStack(viewModel.fragmentDepth.value.toString())
        }
    }
}