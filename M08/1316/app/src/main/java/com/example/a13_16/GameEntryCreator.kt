package com.example.a13_16

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class GameEntryCreator : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_creator_activity)

        val inputGameCover = findViewById<ImageButton>(R.id.newGameCover)
        val inputGameName = findViewById<EditText>(R.id.newGameName)
        val inputGamePublisher = findViewById<EditText>(R.id.newGamePublisher)
        val inputGamePlayerCount = findViewById<EditText>(R.id.newGamePlayerCount)
        val newEntryButton = findViewById<Button>(R.id.createEntryButton)

        //val selectedImagePath = getFilesDir().toString() + "/img/" + inputGameCover.get

        var selectedImage =

        inputGameCover.setOnClickListener(){

        }

        newEntryButton.setOnClickListener(){
            //val newGameEntry = Game(inputGameName.text.toString(), selectedImage)
        }
    }

    override fun onStop() {
        super.onStop()
        setResult(RESULT_CANCELED)
    }
}