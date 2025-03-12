package fragments

import adapters.SubTaskListAdapter
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
import dataClasses.Status
import dataClasses.Task
import viewmodels.ProjectsViewModel

class TaskDetailsSubTaskListFragment(
    private val selectedTask: Task,
    private val statusList: Array<Status>
) :
    Fragment() {

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
        if (selectedTask.subTasks != null) {
            val subTaskList = view.findViewById<RecyclerView>(R.id.project_list)
            subTaskList.layoutManager = LinearLayoutManager(requireContext())
            subTaskList.adapter = SubTaskListAdapter(
                selectedTask.subTasks,
                statusList
            ) { subTask ->
                // Handle subTask item click

                var currentDepth = 0
                viewModel.fragmentDepth.observe(viewLifecycleOwner) { liveDepth ->
                    currentDepth = liveDepth
                }
                //Actualizamos la profundidad del ViewModel
                viewModel.setTitleCardValue(subTask.name)

                Toast.makeText(context, "Clicked: ${subTask.name}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}