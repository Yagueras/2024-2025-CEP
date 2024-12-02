package com.example.a13_16

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GamesAdapter (val context: Context, /*val layout: Int,*/ val gamesList: MutableList<Game>): //<< Definimos aquí los parámetros que recibe el adapter

    RecyclerView.Adapter<GamesAdapter.GamesViewHolder>(){
        private val layout = R.layout.games_list_item
        private var clickListener: View.OnClickListener? = null

    class GamesViewHolder(val view: View):
    RecyclerView.ViewHolder(view){
        var gameImg: ImageView //= view.findViewById<ImageView>(R.id.gamesListImg)
        var gameName: TextView //= view.findViewById<TextView>(R.id.gameName)
        var gamePublisher: TextView //= view.findViewById<TextView>(R.id.gamePublisher)

        init {
            gameImg = view.findViewById(R.id.gameImg)
            gameName = view.findViewById(R.id.gameName)
            gamePublisher = view.findViewById(R.id.gamePublisher)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return GamesViewHolder(view)
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        val game = gamesList[position]
        bindGame(holder, game)
    }

    fun bindGame(holder: GamesViewHolder, game:Game){
        val decodedImage = BitmapFactory.decodeFile(context.getFilesDir().toString() + "/img/" + game.gameCover)
        holder.gameImg.setImageBitmap(decodedImage)
        holder.gameName.text = game.name
        holder.gamePublisher.text = game.publisher
    }

    //Implementamos la interfaz obligatoria
    override fun getItemCount() = gamesList.size

    fun setOnClickListener(listener: View.OnClickListener) {
        clickListener = listener
    }

    fun onClick(view: View){
        clickListener?.onClick(view)
    }

}


//Pendiente de análisis
/*
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
*/