package com.example.a13_16

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var gameSelection: MutableList<Game>
    private lateinit var gameListAdapter: GamesAdapter

    private val defaultImages = listOf(
        R.drawable.codmw2,
        R.drawable.feh3h,
        R.drawable.thecrew2,
        R.drawable.titanfall2,
        R.drawable.r6s,
        R.drawable.signalis,
        R.drawable.sottr
    )

    //Comprueba si la aplicación lanzada, ha sido un éxito o no
    private val getResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val newGameEntry = it.data?.getSerializableExtra("newEntry")
                gameSelection.add(newGameEntry as Game)
                gameListAdapter.notifyDataSetChanged()
            } else if (it.resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "New entry cancelled", Toast.LENGTH_SHORT).show()
            }
        }

        //Carga las images por defecto, de drawable, a files/img
    fun loadDefaultImages(context: Context, defaultImages: List<Int>, imgDir: String) {
        for (i in defaultImages.indices) {
            val resourceId = defaultImages[i]
            val imageName =  getResources().getResourceEntryName(resourceId) // Customize naming as needed
            try {
                val bitmap = BitmapFactory.decodeResource(context.resources, resourceId)
                val file = File(imgDir, imageName)
                val outputStream = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                outputStream.flush()
                outputStream.close()
            } catch (e: IOException) {
                Log.e("LoadDefaultImages", "Error loading image: $imageName", e)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lateinit var imgDir : String

        //Comprueba si es la primera ejecución del programa,
        // para mover las imágenes de drawable a files/img
        val prefsHelper = PrefsHelper(this)
        if (prefsHelper.isFirstTimeLaunch()) {
            imgDir = getFilesDir().toString()
            val directory = File("$imgDir/img")
            directory.mkdirs()
            loadDefaultImages(this, defaultImages, directory.absolutePath)
            prefsHelper.setFirstTimeLaunch(false)
        }

        //Caragmos los objetos por defecto de la aplicación
        gameSelection = mutableListOf(
            Game("Titanfall 2", "EA", 12000, "titanfall2"),
            Game("Fire Emblem Three Houses", "Nintendo", 19000, "feh3h"),
            Game("Call Of Duty MW2", "Activision", 10000, "codmw2"),
            Game("Signalis", "Rose Engine", 7000, "signalis"),
            Game("The Crew 2", "Ubisoft", 9500, "thecrew2"),
            Game("Tom Clancy's Rainbow Six Siege", "Ubisoft", 29000, "r6s"),
            Game("Shadow of the Tomb Raider", "Crystal Dynamics", 4500, "sottr")
        )

        setContentView(R.layout.activity_main)

        gameListAdapter = GamesAdapter(this, R.layout.games_list_item, gameSelection)

        val gamesList = findViewById<RecyclerView>(R.id.gamesList)

        gamesList.adapter = gameListAdapter

        //Falta implementacion onClick en GamesAdapter
        gamesList.setOnItemClickListener()
        { _, _, position, _ ->

            val gameDetailsImg = findViewById<ImageView>(R.id.gameImg)
            val loadImage = BitmapFactory.decodeFile(getFilesDir().toString() + "/img/" + gameSelection[position].gameCover)
            gameDetailsImg.setImageBitmap(loadImage)
            gameDetailsImg.visibility = View.VISIBLE

            val gameDetailsTitle = findViewById<TextView>(R.id.gameDetailsTitle)
            gameDetailsTitle.text = gameSelection[position].name
            gameDetailsTitle.visibility = View.VISIBLE

            val gameDetailsPublisher = findViewById<TextView>(R.id.gameDetailsPublisher)
            gameDetailsPublisher.text = gameSelection[position].publisher
            gameDetailsPublisher.visibility = View.VISIBLE

            val gameDetailsPlayerCount = findViewById<TextView>(R.id.gameDetailsPlayerCount)
            gameDetailsPlayerCount.text =
                "Current players: " + gameSelection[position].playerCount.toString()
            gameDetailsPlayerCount.visibility = View.VISIBLE
        }

        val addNewGameButton = findViewById<FloatingActionButton>(R.id.addNewGameButton)

        addNewGameButton.setOnClickListener {
            val intent = Intent(this, GameEntryCreator::class.java)
            getResult.launch(intent)
        }
    }
}
