package fragments

import adapters.TaskListAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.m08_rep1.R
import dataClasses.Project

class ProjectDetailsFragment(private val selectedProject: Project) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_project_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Load the menu fragment inside this fragment
        if (savedInstanceState == null) {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.details_menu_fragment, ProjectMenuFragment(selectedProject))
                .commit()
        }

        // Set up the RecyclerView for tasks
        if (selectedProject.tasks != null) {
            val taskList = view.findViewById<RecyclerView>(R.id.task_list)
            taskList.layoutManager = LinearLayoutManager(requireContext())
            taskList.adapter = TaskListAdapter(
                selectedProject.tasks,
                selectedProject.statusList
            ) { task ->
                // Handle task item click
                Toast.makeText(context, "Clicked: ${task.name}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}