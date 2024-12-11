package fragments

import adapters.ProjectListAdapter
import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.m08_rep1.R
import dataClasses.Project
import dataClasses.ProjectsWrapper
import dataClasses.Status
import dataClasses.SubTask
import dataClasses.Task
import tools.ProjectFileHandler

class ProjectListFragment : Fragment() {
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
        projectList.hasFixedSize()
        projectList.adapter = ProjectListAdapter(listOfProjects!!) { project ->
            Toast.makeText(context, "Clic en: ${project.name}", Toast.LENGTH_SHORT).show()
        }




    }
}