package fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.m08_rep1.R
import dataClasses.Project
import viewmodels.ProjectsViewModel

class ProjectDetailsFragment(private val selectedProject: Project) : Fragment() {

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

        // Load the menu fragment inside this fragment
        if (viewModel.fragmentDepth.value!! < 1) {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.details_menu_fragment, ProjectMenuFragment(selectedProject))
                .replace(R.id.task_list, ProjectDetailsTaskListFragment(selectedProject))
                .commit()
        }

    }

    override fun onStop() {
        super.onStop()
        viewModel.titleCardValue.value = "Projects"
        viewModel.removeDepth()
    }
}