package fragments

import adapters.SubTaskListAdapter
import adapters.TaskListAdapter
import android.os.Bundle
import android.transition.Fade
import android.transition.Slide
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.m08_rep1.R
import dataClasses.Project
import dataClasses.Status
import dataClasses.Task

class SubTaskListFragment(private val selectedTask: Task, private val statusList: Array<Status>) :
    Fragment() {


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
            ) { task ->
                // Handle task item click
                Toast.makeText(context, "Clicked: ${task.name}", Toast.LENGTH_SHORT).show()
                //fragment swap logic
            }
        }
    }
}