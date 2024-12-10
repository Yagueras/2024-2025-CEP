package dataClasses

data class ProjectsWrapper(
    val projects: Array<Project>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProjectsWrapper

        return projects.contentEquals(other.projects)
    }

    override fun hashCode(): Int {
        return projects.contentHashCode()
    }
}