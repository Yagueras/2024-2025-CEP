package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.m08_rep1.R
import dataClasses.Project

class ProjectListAdapter(
    private val projectList: Array<Project>,
    private val onItemClick: (Project) -> Unit // Listener para clics
) : RecyclerView.Adapter<ProjectListAdapter.ProjectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.project_item, parent, false)
        return ProjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val project = projectList[position]
        holder.bind(project)
        holder.itemView.setOnClickListener { onItemClick(project) } // Asignar el clic
    }

    override fun getItemCount(): Int = projectList.size

    inner class ProjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val projectName: TextView = itemView.findViewById(R.id.project_name)
        private val taskProgress: TextView = itemView.findViewById(R.id.project_progress)//LinearLayout
        private val timeUsed: TextView = itemView.findViewById(R.id.project_time_used)//LinearLayout

        fun bind(project: Project) {
            projectName.text = project.name
            taskProgress.text = "TASK PROGRESS CHART(TO DO)"
            timeUsed.text = "TIME USED CHART(TO DO)"
        }
    }
}