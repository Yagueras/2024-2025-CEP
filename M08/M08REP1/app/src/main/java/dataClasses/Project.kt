package dataClasses

import java.io.Serializable

data class Project(
    val projectID: Int,
    val name: String,
    val statusList: Array<Status>,
    val tasks: Array<Task>?
) : Serializable