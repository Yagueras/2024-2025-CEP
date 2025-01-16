package fragments

import adapters.ProjectListAdapter
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
import tools.ProjectFileHandler
import viewmodels.ProjectsViewModel


class ProjectListFragment : Fragment() {

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

        val projectFileHandler = ProjectFileHandler(requireContext())

        val listOfProjects = projectFileHandler.readProjectsFromFile("projects.json")

        val projectList = view.findViewById<RecyclerView>(R.id.project_list)
        projectList.layoutManager = LinearLayoutManager(requireContext())
        //onClick
        projectList.adapter = ProjectListAdapter(listOfProjects!!) { project ->
            Toast.makeText(context, "Clic en: ${project.name}", Toast.LENGTH_SHORT).show()

            //Actualizamos la profundidad del ViewModel
            viewModel.addDepth()
            viewModel.titleCardValue.value = project.name

            val projectDetails = ProjectDetailsFragment(project)

            requireActivity().supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter_from_bottom, R.anim.fade_out, R.anim.fade_in, R.anim.exit_to_bottom)
                .replace(R.id.projects_fragment, projectDetails)
                .addToBackStack("0")
                .commit()
        }
    }
}