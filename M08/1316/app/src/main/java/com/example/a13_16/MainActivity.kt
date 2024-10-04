package com.example.a13_16

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val games = arrayOf(
            "Titanfall 2",
            "Fire Emblem Three Houses",
            "Call Of Duty MW2",
            "Signalis",
            "The Crew 2",
            "Tom Clancy's Rainbow Six Siege",
            "Shadow of the Tomb Raider"
        )

        val gamesList: ListView = findViewById<ListView>(R.id.gamesList)

        val adapter = ArrayAdapter<String>(this, R.layout.games_list_item, games)

        gamesList.adapter = adapter

        gamesList.setOnItemClickListener()
        { parent, view, position, id ->

            val gameImg = findViewById<ImageView>(R.id.gamesListImg)
            gameImg.setImageResource(R.drawable.    )
        }
    }
}