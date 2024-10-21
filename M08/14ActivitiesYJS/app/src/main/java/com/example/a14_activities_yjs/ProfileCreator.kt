package com.example.a14_activities_yjs

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProfileCreator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_creator)

        val userName = "cep"
        val password = "informatica"


        val givenUserName = intent.getStringExtra("userName")
        val givenUserPassword = intent.getStringExtra("password")
        var credentialsCheck = false

        val newUserInput = findViewById<EditText>(R.id.nameInput)
        val utilityButton = findViewById<Button>(R.id.createUserButton)

        if (userName == givenUserName && password == givenUserPassword){
            newUserInput.visibility = View.VISIBLE
            utilityButton.setText("Create your user!")
            credentialsCheck = true
            Toast.makeText(this, "Credentials match", Toast.LENGTH_SHORT).show()
        } else{
            utilityButton.setText("Return")
            Toast.makeText(this, "Credentials do not match", Toast.LENGTH_SHORT).show()
        }

        utilityButton.setOnClickListener(){
            if (credentialsCheck){
                if (newUserInput.text.isNotBlank()){
                val result = Intent(this, MainActivity::class.java)
                setResult(RESULT_OK, result)
                result.putExtra("newUser", newUserInput.text.toString())
                finish()
                }else{
                    Toast.makeText(this, "Please input a valid name", Toast.LENGTH_SHORT).show()
                }
            }else{
                setResult(RESULT_CANCELED)
                finish()
            }
        }
    }
}