package com.example.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.a14_activities_yjs.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val userName = "cep"
        val password = "informatica"

        val inputName = findViewById<EditText>(R.id.userName)
        val inputPassword = findViewById<EditText>(R.id.password)

    }
}