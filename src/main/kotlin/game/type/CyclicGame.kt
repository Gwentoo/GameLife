package game.type

import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import game.abstract.Game
import kotlin.math.max


class CyclicGame : Game {

    constructor(rows: Int, cols: Int){
        this.matrix = Array(rows) { IntArray(cols) {0} }
        this.rows = rows
        this.cols = cols

    }
    constructor(){
        this.matrix = Array(rows){IntArray(cols){0} }

    }
    constructor(matrix: Array<IntArray>){
        this.matrix = matrix
        this.rows = matrix.size
        this.cols = matrix[0].size

    }

    override fun neighbors(row: Int, col: Int): Int{
        var count = 0
        for (i in -1..1) {
            for (j in -1..1) {

                if (i == 0 && j == 0) continue
                val nx = (row + i + rows) % rows
                val ny = (col + j + cols) % cols

                count += matrix[nx][ny]
            }
        }

        return count
    }

}