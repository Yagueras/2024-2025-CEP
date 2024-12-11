package adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
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
        private val taskProgress: TextView =itemView.findViewById(R.id.task_progress)//LinearLayout
        private val timeUsed: TextView =itemView.findViewById(R.id.time_used)//LinearLayout

        fun bind(project: Project) {
            projectName.text = project.name
            taskProgress.text = "TASK PROGRESS CHART(TO DO)"
            timeUsed.text = "TIME USED CHART(TO DO)"
        }
    }
}

    /*(
    private val projectList: Array<Project>
) : RecyclerView.Adapter<ProjectListAdapter.ProjectListViewHolder>(), OnClickListener {
    private val layout = R.layout.project_item
    private var clickListener: OnClickListener? = null


    class ProjectListViewHolder(view: View) :
        ViewHolder(view) {
        var projectName: TextView
        var taskProgress: TextView//LinearLayout
        var timeUsed: TextView//LinearLayout

        init {
            projectName = view.findViewById(R.id.project_name)
            taskProgress = view.findViewById(R.id.task_progress)
            timeUsed = view.findViewById(R.id.time_used)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        view.setOnClickListener(this)
        return ProjectListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectListViewHolder, position: Int) {
        val project = projectList[position]
        bindProject(holder, project)
    }

    fun bindProject(holder: ProjectListViewHolder, project: Project) {
        holder.projectName.text = project.name
        holder.taskProgress.text = "TASK PROGRESS CHART(TO DO)"
        holder.timeUsed.text = "TIME USED CHART(TO DO)"
    }

    override fun getItemCount() = projectList.size

    private fun setOnClickListener(listener: OnClickListener) {
        clickListener = listener
    }

    override fun onClick(view: View?) {
        clickListener?.onClick(view)
    }

}*/