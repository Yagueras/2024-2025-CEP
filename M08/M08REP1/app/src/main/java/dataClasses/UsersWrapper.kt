package dataClasses

data class UsersWrapper(
    val projects: Array<User>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UsersWrapper

        return projects.contentEquals(other.projects)
    }

    override fun hashCode(): Int {
        return projects.contentHashCode()
    }
}