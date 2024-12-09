package com.example.m08_rep1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton = findViewById<Button>(R.id.button_login)
        val userEmailET = findViewById<EditText>(R.id.email_input)
        val userPasswordET = findViewById<EditText>(R.id.password_input)

        loginButton.setOnClickListener(){
            var userEmailDATA = userEmailET.text
            var userPasswordDATA = userPasswordET.text


        }
    }
}