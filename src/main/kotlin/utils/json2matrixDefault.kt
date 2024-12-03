package utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

fun json2matrixDefault(name: String): Array<IntArray>{
    val type = object : TypeToken<Array<IntArray>>() {}.type

    val json = File(name).readText()

    val matrix: Array<IntArray> = Gson().fromJson(json, type)

    return matrix

}