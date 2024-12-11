package com.example.m08_rep1.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.m08_rep1.R
import dataClasses.User
import tools.UserFileHandler


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton = findViewById<Button>(R.id.button_login)
        val userEmailET = findViewById<EditText>(R.id.email_input)
        val userPasswordET = findViewById<EditText>(R.id.password_input)
        val users = UserFileHandler(this).readUsersFromFile("users.json")
        val lastUserLogin = UserFileHandler(this).readUsersFromFile("lastValidLogin.json")?.get(0)
        lateinit var successfulUserLogin: User
        var validLogin = false

        if (users == null) {
            Toast.makeText(this, "File not found: users.json", Toast.LENGTH_SHORT).show()
        }

        if (lastUserLogin != null) {
            launchProjectViewer(lastUserLogin)
        }

        loginButton.setOnClickListener {
            if (userEmailET.text.isNullOrBlank() || userPasswordET.text.isNullOrBlank()) {
                Toast.makeText(this, "Please, fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                val userEmailDATA = userEmailET.text
                val userPasswordDATA = userPasswordET.text

                for (user in users!!) {
                    if (userEmailDATA.toString() == user.email) {
                        if (userPasswordDATA.toString() == user.password) {
                            validLogin = true
                            successfulUserLogin = user
                        } else {
                            Toast.makeText(
                                this,
                                "Incorrect password, try again",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
                if (validLogin) {
                    if (lastUserLogin == null){
                        updateLastValidLogin(this, successfulUserLogin)
                    }
                    launchProjectViewer(successfulUserLogin)
                } else {
                    Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun launchProjectViewer(loggedUser: User) {
        val intent = Intent(
            this@LoginActivity,
            ProjectViewerActivity::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra("loggedUser", loggedUser)
        startActivity(intent)
        finish()
    }

    private fun updateLastValidLogin(context: Context, validUserLogin: User) {
        val newValidLogin : Array<User> = arrayOf(validUserLogin)
        UserFileHandler(context).writeUserToFile("lastValidLogin.json", newValidLogin)
    }

}