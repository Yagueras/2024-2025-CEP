package dataClasses

import java.io.Serializable

data class User(
    val name: String,
    val surname1: String,
    val surname2: String,
    val email: String,
    val password: String
) : Serializable