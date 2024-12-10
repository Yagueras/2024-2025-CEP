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
import dataClasses.Status
import dataClasses.SubTask
import dataClasses.Task

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

        val listOfProjects = listOf(
            Project(
                1,
                "Proyecto A",
                listOf(
                    Status(1, "To do"),
                    Status(2, "In progress"),
                    Status(3, "Completed")),
                listOf(
                    Task(1, "Diseño", 40, 25, 2,
                        listOf(
                            SubTask(1,"wireframes", "askjdhasdklfh", 10, 8,3))))
            ), Project(
                2,
                "Proyecto B",
                listOf(
                    Status(1, "To do"),
                    Status(2, "In progress"),
                    Status(3, "Completed")),
                listOf(
                    Task(1, "Desarollo", 40, 25, 2,
                        listOf(
                            SubTask(1,"wireframes", "askjdhasdklfh", 10, 8,3))))
            )
        )
        val projectListAdapter = ProjectListAdapter(listOfProjects)

        val projectList = view.findViewById<RecyclerView>(R.id.project_list)
        projectList.layoutManager = LinearLayoutManager(requireContext())
        projectList.adapter = projectListAdapter

    }
}