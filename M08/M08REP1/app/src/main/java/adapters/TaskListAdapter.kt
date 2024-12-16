package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.m08_rep1.R
import dataClasses.Status
import dataClasses.Task

class TaskListAdapter(
    private val taskList: Array<Task>,
    private val onItemClick: (Task) -> Unit, // Listener para clics
    private val statusList: Array<Status>
) : RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskListAdapter.TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.bind(task)
        holder.itemView.setOnClickListener { onItemClick(task) } // Asignar el clic
    }

    override fun getItemCount(): Int = taskList.size

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val taskName: TextView = itemView.findViewById(R.id.task_name)
        private val taskStatus: TextView = itemView.findViewById(R.id.task_status)
        private val taskProgress: TextView = itemView.findViewById(R.id.task_progress)//LinearLayout
        private val tasktimeUsed: TextView = itemView.findViewById(R.id.task_time_used)//LinearLayout

        fun bind(task: Task) {
            taskName.text = task.name
            taskStatus.text = statusList[task.status].statusName
            taskProgress.text = "TASK PROGRESS CHART(TO DO)"
            tasktimeUsed.text = "TIME USED CHART(TO DO)"
        }
    }
}