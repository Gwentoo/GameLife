package game.abstract

import game.type.UnCyclicGame

abstract class Game {

    var matrix: Array<IntArray>
    var rows = 5
    var cols = 5

    constructor(rows: Int, cols: Int){
        this.matrix = Array(rows) { IntArray(cols) }
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

    fun newState(): Array<IntArray> {
        val newState = Array(rows) {IntArray(cols)}
        for (i in 0 until rows){
            for (j in 0 until cols){
                val neighborsCount = neighbors(i, j)
                if (matrix[i][j] == 0 && neighborsCount == 3){
                    newState[i][j] = 1
                }
                if (matrix[i][j] == 1 && (neighborsCount == 2 || neighborsCount == 3)){
                    newState[i][j] = 1
                }
            }
        }

      return newState
    }



}