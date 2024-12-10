package dataClasses

import java.io.Serializable

data class Project(
    val projectID: String,
    val name: String,
    val tasks: List<Task>
) : Serializable