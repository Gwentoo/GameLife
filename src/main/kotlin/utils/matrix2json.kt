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
    fileChooser.dialogTitle = "Сохранить файл"
    fileChooser.fileFilter = FileNameExtensionFilter("JSON Files", "json")

    // Открываем окно выбора файла
    val returnValue = fileChooser.showSaveDialog(null)

    // Если файл выбран, сохраняем данные
    if (returnValue == JFileChooser.APPROVE_OPTION) {
        val selectedFile: File = fileChooser.selectedFile
        if (!selectedFile.name.endsWith(".json")) {
            // Добавляем расширение .json, если его нет
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