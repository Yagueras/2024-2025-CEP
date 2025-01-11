package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.m08_rep1.R
import dataClasses.Status
import dataClasses.SubTask

class SubTaskListAdapter (
    private val subtaskList: Array<SubTask>,
    private val statusList: Array<Status>,
    private val onItemClick: (SubTask) -> Unit // Listener para clics
) : RecyclerView.Adapter<SubTaskListAdapter.SubTaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubTaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return SubTaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubTaskListAdapter.SubTaskViewHolder, position: Int) {
        val subtask = subtaskList[position]
        holder.bind(subtask)
        holder.itemView.setOnClickListener { onItemClick(subtask) } // Asignar el clic
    }

    override fun getItemCount(): Int = subtaskList.size

    inner class SubTaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val subtaskName: TextView = itemView.findViewById(R.id.task_name)
        private val subtaskStatus: TextView = itemView.findViewById(R.id.task_status)
        private val subtaskProgress: TextView = itemView.findViewById(R.id.task_progress)//LinearLayout
        private val subtaskTimeUsed: TextView = itemView.findViewById(R.id.task_time_used)//LinearLayout

        fun bind(subtask: SubTask) {
            subtaskName.text = subtask.name
            subtaskStatus.text = statusList[subtask.status].statusName
            subtaskProgress.text = "TASK PROGRESS CHART(TO DO)"
            subtaskTimeUsed.text = "TIME USED CHART(TO DO)"
        }
    }
}