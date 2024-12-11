package dataClasses

import java.io.Serializable

data class Task(
    val taskID: Int,
    val name: String,
    val requiredTime: Int,
    val timeUsed: Int,
    val status: Int,
    val subTasks: List<SubTask>?
) : Serializable