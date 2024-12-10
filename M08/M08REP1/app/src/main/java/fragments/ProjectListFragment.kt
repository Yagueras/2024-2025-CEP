package fragments

import adapters.ProjectListAdapter
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        val projectListAdapter = ProjectListAdapter(listOfProjects!!)

        val projectList = view.findViewById<RecyclerView>(R.id.project_list)
        projectList.layoutManager = LinearLayoutManager(requireContext())
        projectList.adapter = projectListAdapter

    }
}