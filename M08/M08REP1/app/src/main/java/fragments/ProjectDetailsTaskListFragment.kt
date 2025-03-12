package fragments

import adapters.TaskListAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment 
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.m08_rep1.R
import com.example.m08_rep1.activities.ProjectViewerActivity
import dataClasses.Project
import viewmodels.ProjectsViewModel

class ProjectDetailsTaskListFragment(private val selectedProject: Project) : Fragment() {

    private val viewModel: ProjectsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_project_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //replaceFragmentLogic
        // Set up the RecyclerView for tasks

        //Revisamos la profundidad de la aplicación
        var currentDepth = 0
        viewModel.fragmentDepth.observe(viewLifecycleOwner) { liveDepth ->
            currentDepth = liveDepth
            val activityDepth = currentDepth

            if (selectedProject.tasks != null) {
                val taskList = view.findViewById<RecyclerView>(R.id.project_list)
                taskList.layoutManager = LinearLayoutManager(requireContext())
                taskList.adapter = TaskListAdapter(
                    selectedProject.tasks,
                    selectedProject.statusList
                ) { task -> // Handle task item click
                    //Actualizamos el ViewModel
                    if (activityDepth == 2) {
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(R.id.details_menu_fragment, TaskMenuFragment(task, selectedProject.statusList))
                            .replace(R.id.task_list, TaskDetailsSubTaskListFragment(task, selectedProject.statusList))
                            .addToBackStack((activityDepth).toString())
                            .commit()
                    }

                    viewModel.setTitleCardValue(task.name)
                    viewModel.addDepth()

        }
        /*Creamos un valor para guardar la profundidad en la que se encuentra la aplicación
        al iniciarse. Lo usaremos para etiquetar el fragmento actual en el backstack, en vez
        de currentDepth debido a su volatilidad como MutableLiveData*/
            }
        }
    }
}