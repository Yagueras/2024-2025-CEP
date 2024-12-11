package tools

import android.content.Context
import com.google.gson.Gson
import dataClasses.User
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

class UserFileHandler(private val context: Context)  {
    private val gson = Gson()

    fun readUsersFromFile(fileName: String): Array<User>? {
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
            gson.fromJson(json, Array<User>::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun writeUserToFile(fileName: String, usersWrapper: UsersWrapper) {
        try {
            // Serialize the ProjectsWrapper object to JSON
            val json = gson.toJson(usersWrapper)

            // Write the JSON content to the file
            val file = File(context.filesDir, fileName)
            file.writeText(json)

            println("File saved successfully: ${file.absolutePath}")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}