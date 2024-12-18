package adapters

import android.os.Bundle
import android.provider.Settings.Global.putString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.m08_rep1.R
import dataClasses.Project
import fragments.ProjectDetailsFragment

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

        holder.itemView.setOnClickListener {
            onItemClick(project)
        }
    }

    fun onItemClick(project: Project, sharedView: View) {
        val projectDetailsFragment = ProjectDetailsFragment(project)

        // Start the transition
        (sharedView.context as FragmentActivity).supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.projects_fragment, projectDetailsFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun getItemCount(): Int = projectList.size

    inner class ProjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val projectName: TextView = itemView.findViewById(R.id.project_name)
        private val taskProgress: TextView = itemView.findViewById(R.id.project_progress)//LinearLayout
        private val projectTimeUsed: TextView = itemView.findViewById(R.id.project_time_used)//LinearLayout

        fun bind(project: Project) {
            projectName.text = project.name
            taskProgress.text = "TASK PROGRESS CHART(TO DO)"
            projectTimeUsed.text = "TIME USED CHART(TO DO)"
        }
    }
}