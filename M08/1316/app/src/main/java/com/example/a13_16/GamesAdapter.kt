package com.example.a13_16

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class GamesAdapter (context: Context, val layout: Int, val gamesList: MutableList<Game>): //<< Definimos aquí los parámetros que recibe el adapter

    //Llamo al constructor de la clase base, y le paso los atributos de GamesAdapter
    ArrayAdapter<Game>(context,layout,gamesList) {

        //Usamos getView para pasar los datos de gamesList a las posiciones de la lista
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view: View

        if (convertView != null){
            view = convertView
        } else{

            view = LayoutInflater.from(getContext()).inflate(layout, parent, false)
        }

            bindGame(view, gamesList[position], context)

        return view
    }

    fun bindGame(view: View, game: Game, context: Context){
        val gameImg = view.findViewById<ImageView>(R.id.gamesListImg)
        val decodedImage = BitmapFactory.decodeFile(context.getFilesDir().toString() + "/img/" + game.gameCover)
        gameImg.setImageBitmap(decodedImage)
        val gameName = view.findViewById<TextView>(R.id.gameName)
        gameName.text = game.name
        val gamePublisher = view.findViewById<TextView>(R.id.gamePublisher)
        gamePublisher.text = game.publisher
    }

}