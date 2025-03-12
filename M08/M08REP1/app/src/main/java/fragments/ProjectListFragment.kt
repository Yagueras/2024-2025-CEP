package fragments

import adapters.ProjectListAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        //Revisamos la profundidad de la aplicación
        var currentDepth = 0
        viewModel.fragmentDepth.observe(viewLifecycleOwner) { liveDepth ->
            currentDepth = liveDepth
        }
        /*Creamos un valor para guardar la profundidad en la que se encuentra la aplicación
        al iniciarse. Lo usaremos para etiquetar el fragmento actual en el backstack, en vez
        de currentDepth debido a su volatilidad como MutableLiveData*/
        val activityDepth = currentDepth

        //Lógica para cargar la lista de proyectos
        val projectFileHandler = ProjectFileHandler(requireContext())
        val listOfProjects = projectFileHandler.readProjectsFromFile("projects.json")
        val projectList = view.findViewById<RecyclerView>(R.id.project_list)
        projectList.layoutManager = LinearLayoutManager(requireContext())

        //onClick
        projectList.adapter = ProjectListAdapter(listOfProjects!!) { project ->

            viewModel.addDepth()

            val projectDetails = ProjectDetailsFragment(project, null)
            requireActivity().supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_from_bottom,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.exit_to_bottom
                )
                .replace(R.id.prj_view_host_fragment, projectDetails)
                .addToBackStack(activityDepth.toString())
                .commit()

            viewModel.setTitleCardValue(project.name)
        }
    }
}