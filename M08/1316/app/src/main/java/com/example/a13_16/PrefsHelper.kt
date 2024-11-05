package com.example.a13_16

import android.content.Context

//Dado que no tengo un servidor ni API para descargar las portadas de cada juego,
// uso esta clase para poder cargarlas desde drawable la primera vez que se abre la aplicación,
// y así poder mostrarlas y utilizarlas en la lista de juegos.
class PrefsHelper(context: Context) {
    private val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    fun isFirstTimeLaunch(): Boolean {
        return prefs.getBoolean("first_time_launch", true)
    }

    fun setFirstTimeLaunch(isFirstTime: Boolean) {
        prefs.edit().putBoolean("first_time_launch", isFirstTime).apply()
    }
}