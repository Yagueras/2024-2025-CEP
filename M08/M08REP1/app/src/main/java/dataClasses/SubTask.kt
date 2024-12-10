package dataClasses

import java.io.Serializable

data class SubTask(
    val subTaskID: Int,
    val name: String,
    val description: String,
    val requiredTime: Int,
    val timeUsed: Int,
    val status: Int
) : Serializable