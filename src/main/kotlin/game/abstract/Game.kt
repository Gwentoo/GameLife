package game.abstract
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.max

abstract class Game {

    var matrix: Array<IntArray>
    var rows = 8
    var cols = 8

    constructor(rows: Int, cols: Int){
        this.matrix = Array(rows) { IntArray(cols) {0} }
        this.rows = rows
        this.cols = cols
    }
    constructor(){
        this.matrix = Array(rows){IntArray(cols) {0} }

    }
    constructor(matrix: Array<IntArray>){
        this.matrix = matrix
        this.rows = matrix.size
        this.cols = matrix[0].size

    }

    abstract fun neighbors(row: Int, col: Int): Int

    fun newState():  MutableList<IntArray> {
        var changeCell: MutableList<IntArray> = mutableListOf()
        var newState = Array(rows) {IntArray(cols)}
        for (i in 0 until rows){
            for (j in 0 until cols){
                val neighborsCount = neighbors(i, j)
                if (matrix[i][j] == 0 && neighborsCount == 3){
                    newState[i][j] = 1
                }
                if (matrix[i][j] == 1 && (neighborsCount == 2 || neighborsCount == 3)){
                    newState[i][j] = 1
                }
                if (matrix[i][j] != newState[i][j]){
                    changeCell.add(intArrayOf(i, j))

                }
            }
        }
        for (coord in changeCell){
            val (x, y) = coord
            matrix[x][y] = if(matrix[x][y] == 1) 0 else 1
        }
      return changeCell
    }



    }

