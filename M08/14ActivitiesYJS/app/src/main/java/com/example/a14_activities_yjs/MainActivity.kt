package com.example.a14_activities_yjs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    //En actividades que van a recoger un resultado, la actividad "padre", se queda esperando el resultado
    private val getResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            val newUserName = it.data?.getStringExtra("newUser")
            Toast.makeText(this, "Welcome $newUserName", Toast.LENGTH_SHORT).show()
        }else if(it.resultCode == RESULT_CANCELED){
            Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputtedName = findViewById<EditText>(R.id.userName)
        val inputtedPassword = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            if (inputtedName.text.isNotBlank()&&inputtedPassword.text.isNotBlank()) {
                //Creamos el intent, y le damos tanto el contexto de la actividad actual, como la actividad que ha de lanzar
                val intent = Intent(this, ProfileCreator::class.java)
                //Después le damos los datos que querramos transferirle a esta nueva actividad
                //Importante pasar los valores de los campos a string antes de pasarlos, o no logra resolverlo a la hora de inicializar la actividad
                intent.putExtra("userName", inputtedName.text.toString())
                intent.putExtra("password", inputtedPassword.text.toString())
                getResult.launch(intent)
            }else{
                Toast.makeText(this, "You can't leave fields blank", Toast.LENGTH_SHORT).show()
            }
        }
    }
}