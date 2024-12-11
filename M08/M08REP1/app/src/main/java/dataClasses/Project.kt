package dataClasses

import java.io.Serializable

data class Project(
    val projectID: Int,
    val name: String,
    val statusList: List<Status>,
    val tasks: List<Task>?
) : Serializable