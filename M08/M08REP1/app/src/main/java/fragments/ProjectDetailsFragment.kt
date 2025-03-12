package fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.replace
import com.example.m08_rep1.R
import dataClasses.Project
import dataClasses.Task
import viewmodels.ProjectsViewModel

class ProjectDetailsFragment(
    private val selectedProject: Project,
    private val selectedTask: Task?
) : Fragment() {

    private val viewModel: ProjectsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_project_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fragmentDepth.observe(viewLifecycleOwner) { liveDepth ->
            if (liveDepth == 1) {
                // Load the menu fragment inside this fragment
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.details_menu_fragment, ProjectMenuFragment(selectedProject))
                    .replace(R.id.task_list, ProjectDetailsTaskListFragment(selectedProject))
                    .commit()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.setTitleCardValue("Projects")
    }

}