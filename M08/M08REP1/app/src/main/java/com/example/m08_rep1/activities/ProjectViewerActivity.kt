package com.example.m08_rep1.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.m08_rep1.R
import dataClasses.User
import fragments.ProjectListFragment
import tools.UserFileHandler
import viewmodels.ProjectsViewModel

class ProjectViewerActivity : AppCompatActivity(), View.OnClickListener {
    private val viewModel: ProjectsViewModel by viewModels()
    private var currentDepth = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_view)

        val logOutButton = findViewById<ImageButton>(R.id.button_logout)
        val title = findViewById<TextView>(R.id.projects_title)

        //Revisamos la profundidad de la aplicación
        viewModel.fragmentDepth.observe(this) { liveDepth ->
            currentDepth = liveDepth
        }

        //Cargamos el layout por defecto
        viewModel.checkIfNull()
        if (currentDepth == 0) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.prj_view_host_fragment, ProjectListFragment())
                .commit()
        }

        //Cargamos los datos de usuario del último login
        val user = intent.getSerializableExtra("loggedUser") as User
        Toast.makeText(this, "Welcome back ${user.name}", Toast.LENGTH_SHORT).show()

        //Habilitamos el retroceso personalizado
        setCustomBackPressedHandler()

        //Actualizamos el título
        viewModel.titleCardValue.observe(this) { selectedElement ->
            title.text = selectedElement
        }

        //Lógica para el botón de retroceso
        logOutButton.setOnClickListener(this)

        /*Aquí limpiamos los datos de inicio de sesión del último usuario
        que ha iniciado sesión, y le devuelve a la pantalla de Login*/
        logOutButton.setOnLongClickListener {
            wipeLastValidLogin(this)
            logOut()
            true
        }
    }

    override fun onClick(v: View?) {
        viewModel.checkIfNull()
        viewModel.checkValidDepth()

        if (currentDepth == 0) {
            Toast.makeText(this, "Long press to Log Out", Toast.LENGTH_SHORT).show()
        } else {
            //Manejo del retroceso personalizado si currentDepth > 0
            onBackPressedDispatcher.onBackPressed() // Esto invoca el comportamiento predeterminado de retroceso
        }
    }

    /*Hacemos esto, para poder darle al botón de retroceso, el mismo funcionamiento
    que el botón físico del dispositivo, y que sea compatible con project depth*/
    private fun setCustomBackPressedHandler() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.removeDepth()
                popBackStack()
            }
        })
    }

    private fun popBackStack(){
        //Para asegurarnos de que el valor de fragmentDepth está actualizado antes de usarlo para navegar, lo observamos, y usamos el valor más reciente.
        viewModel.fragmentDepth.observe(this@ProjectViewerActivity) { updatedDepth ->
            supportFragmentManager.popBackStack(
                updatedDepth.toString(),
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
            /*Puesto que cada vez que retrocedamos, observaremos el valor, para evitar
            observaciones múltiples o fugas de memoria, debemos retirar los observadores creados*/
            viewModel.fragmentDepth.removeObservers(this@ProjectViewerActivity)
        }
    }

    private fun logOut() {
        val intent = Intent(this@ProjectViewerActivity, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }
    private fun wipeLastValidLogin(context: Context) {
        UserFileHandler(context).writeUserToFile("lastValidLogin.json", null, true)
    }
}