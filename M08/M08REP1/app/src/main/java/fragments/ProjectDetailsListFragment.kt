package fragments

import adapters.TaskListAdapter
import android.os.Bundle
import android.transition.Fade
import android.transition.Slide
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.replace
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.m08_rep1.R
import dataClasses.Project
import viewmodels.ProjectsViewModel

class ProjectDetailsListFragment(private val selectedProject: Project) : Fragment() {

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
        if (selectedProject.tasks != null) {
            val taskList = view.findViewById<RecyclerView>(R.id.project_list)
            taskList.layoutManager = LinearLayoutManager(requireContext())
            taskList.adapter = TaskListAdapter(
                selectedProject.tasks,
                selectedProject.statusList
            ) { task ->
                // Handle task item click
                Toast.makeText(context, "Clicked: ${task.name}", Toast.LENGTH_SHORT).show()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.details_menu_fragment, TaskMenuFragment(task))
                    .replace(R.id.task_list, SubTaskListFragment(task, selectedProject.statusList))//esto ha de ser la lista de subtareas
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
}