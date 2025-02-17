package tools

import android.content.Context
import com.google.gson.Gson
import dataClasses.Project
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

class ProjectFileHandler(private val context: Context) {

    private val gson = Gson()

    /**
     * Reads the JSON file from the app's files directory and deserializes it into a ProjectsWrapper object.
     * @param fileName The name of the JSON file.
     * @return A ProjectsWrapper object if successful, or null if an error occurs.
     */
    fun readProjectsFromFile(fileName: String): Array<Project>? {
        return try {
            // Get the file path
            val file = File(context.filesDir, fileName)

            // Check if the file exists
            if (!file.exists()) {
                throw Exception("File not found: $fileName")
            }

            // Open the file and read its content
            val reader = BufferedReader(InputStreamReader(file.inputStream()))
            val json = reader.use { it.readText() }

            // Deserialize the JSON content to ProjectsWrapper
            gson.fromJson(json, Array<Project>::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * Writes a ProjectsWrapper object to a JSON file in the app's files directory.
     * @param fileName The name of the JSON file.
     * @param projectsWrapper The ProjectsWrapper object to save.
     */
    fun writeProjectsToFile(fileName: String, projects: Array<Project>) {
        try {
            // Serialize the ProjectsWrapper object to JSON
            val json = gson.toJson(projects)

            // Write the JSON content to the file
            val file = File(context.filesDir, fileName)
            file.writeText(json)

            println("File saved successfully: ${file.absolutePath}")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

