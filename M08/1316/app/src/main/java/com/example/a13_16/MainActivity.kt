package com.example.a13_16

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val gameSelection = arrayListOf(
            Game("Titanfall 2", "EA", 12000, R.drawable.titanfall2),
            Game("Fire Emblem Three Houses", "Nintendo", 19000, R.drawable.feh3h),
            Game("Call Of Duty MW2", "Activision", 10000, R.drawable.codmw2),
            Game("Signalis", "Rose Engine", 7000, R.drawable.signalis),
            Game("The Crew 2", "Ubisoft", 9500, R.drawable.thecrew2),
            Game("Tom Clancy's Rainbow Six Siege", "Ubisoft", 29000, R.drawable.r6s),
            Game("Shadow of the Tomb Raider", "Crystal Dynamics", 4500, R.drawable.sottr))



        val gamesList: ListView = findViewById<ListView>(R.id.gamesList)

        val adapter = GamesAdapter(this, R.layout.games_list_item, gameSelection)

        gamesList.adapter = adapter

        gamesList.setOnItemClickListener()
        { _, _, position, _ ->

            val gameDetailsImg = findViewById<ImageView>(R.id.gameImg)
            gameDetailsImg.setImageResource(gameSelection[position].gameCover)
            gameDetailsImg.visibility = View.VISIBLE

            val gameDetailsTitle = findViewById<TextView>(R.id.gameDetailsTitle)
            gameDetailsTitle.text = gameSelection[position].name
            gameDetailsTitle.visibility = View.VISIBLE

            val gameDetailsPublisher = findViewById<TextView>(R.id.gameDetailsPublisher)
            gameDetailsPublisher.text = gameSelection[position].publisher
            gameDetailsPublisher.visibility = View.VISIBLE

            val gameDetailsPlayerCount = findViewById<TextView>(R.id.gameDetailsPlayerCount)
            gameDetailsPlayerCount.text = "Current players: " + gameSelection[position].playerCount.toString()
            gameDetailsPlayerCount.visibility = View.VISIBLE
        }
    }
}