package dataClasses

import java.io.Serializable

data class Task(
    val taskID: String,
    val name: String,
    val requiredTime: Int,
    val timeUsed: Int,
    val status: String,
    val subTasks: List<SubTask>
) : Serializable