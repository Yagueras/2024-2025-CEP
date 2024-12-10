package adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.m08_rep1.R
import dataClasses.Project

class ProjectListAdapter(val projectList: Array<Project>) :

    RecyclerView.Adapter<ProjectListAdapter.ProjectListViewHolder>() {
    private val layout = R.layout.project_item
    private var clickListener: View.OnClickListener? = null


    class ProjectListViewHolder(val view: View) :
        RecyclerView.ViewHolder(view) {
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

    fun setOnClickListener(listener: View.OnClickListener) {
        clickListener = listener
    }

    fun onClick(view: View){
        clickListener?.onClick(view)
    }
}