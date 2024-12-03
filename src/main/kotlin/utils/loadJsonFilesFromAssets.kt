package utils

import java.io.File

fun loadJsonFileNamesFromAssets(): List<String> {
    val directory = File("src/main/kotlin/assets/patterns")
    return if (directory.exists() && directory.isDirectory) {
        directory.listFiles { _, name -> name.endsWith(".json") }?.map {
            it.nameWithoutExtension // Получаем имя без расширения
        } ?: emptyList()
    } else {
        emptyList()
    }
}