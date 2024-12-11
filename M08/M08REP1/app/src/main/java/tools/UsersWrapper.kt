package tools

import dataClasses.User

data class UsersWrapper(
    val users: Array<User>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UsersWrapper

        return users.contentEquals(other.users)
    }

    override fun hashCode(): Int {
        return users.contentHashCode()
    }
}