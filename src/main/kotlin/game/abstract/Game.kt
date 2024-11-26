package game.abstract

abstract class Game {

    var matrix: Array<IntArray>
    var rows = 8
    var cols = 8

    constructor(rows: Int, cols: Int){
        this.matrix = Array(rows) { IntArray(cols) }
        this.rows = rows
        this.cols = cols
    }
    constructor(){
        this.matrix = Array(8){IntArray(8)}

    }

    abstract fun neighborsNP(row: Int, col: Int): Int

    fun newStateNP(): Array<IntArray>{
        var newState = Array(rows) {IntArray(cols)}
        for (i in 0 until rows){
            for (j in 0 until cols){
                val neighborsCount = neighborsNP(i, j)
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