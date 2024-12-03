package utils

import com.google.gson.Gson
import game.abstract.Game
import java.io.File
import java.io.FileWriter
import java.io.IOException
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

fun matrix2json(game: Game) {
    val gson = Gson()
    val fileChooser = JFileChooser()

    val projectPath = File("D:/Progs/Life").absoluteFile
    fileChooser.currentDirectory = projectPath

    fileChooser.dialogTitle = "Сохранить файл"
    fileChooser.fileFilter = FileNameExtensionFilter("JSON Files", "json")

    val returnValue = fileChooser.showSaveDialog(null)

    if (returnValue == JFileChooser.APPROVE_OPTION) {
        val selectedFile: File = fileChooser.selectedFile
        if (!selectedFile.name.endsWith(".json")) {

            selectedFile.renameTo(File(selectedFile.parent, "${selectedFile.name}.json"))
        }
        try {
            FileWriter(selectedFile).use { fileWriter ->
                gson.toJson(game.matrix, fileWriter)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}