package fragments

import adapters.ProjectListAdapter
import adapters.TaskListAdapter
import android.os.Bundle
import android.transition.Fade
import android.transition.Slide
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.m08_rep1.R
import dataClasses.Project

class ProjectDetailsFragment(selectedProject: Project) : Fragment() {
    private val selectedProject = selectedProject

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_project_details, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Load ProjectListFragment only if no fragment is currently loaded
        if (savedInstanceState == null) {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.details_menu_fragment, ProjectMenuFragment())
                .commit()
        }

        if (selectedProject.tasks != null){
            val taskList = view.findViewById<RecyclerView>(R.id.task_list)
            exitTransition = Slide()
            taskList.layoutManager = LinearLayoutManager(requireContext())
            taskList.adapter = TaskListAdapter(selectedProject.tasks, selectedProject.statusList) { task ->
                Toast.makeText(context, "Clic en: ${task.name}", Toast.LENGTH_SHORT).show()

            }
        }

    }

}