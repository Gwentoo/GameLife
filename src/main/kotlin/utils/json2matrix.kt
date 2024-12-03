package utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.IOException
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

fun json2matrix(): Array<IntArray> {
    val fileChooser = JFileChooser()


    fileChooser.dialogTitle = "Открыть файл"
    fileChooser.fileFilter = FileNameExtensionFilter("JSON Files", "json")


    val returnValue = fileChooser.showOpenDialog(null)


    if (returnValue == JFileChooser.APPROVE_OPTION) {
        val selectedFile: File = fileChooser.selectedFile
        val type = object : TypeToken<Array<IntArray>>() {}.type
        return try {
            val json = selectedFile.readText()
            Gson().fromJson(json, type)
        } catch (e: IOException) {
            e.printStackTrace()
            Array(8) { IntArray(8) }
        }
    }
    return Array(8) { IntArray(8) }
}