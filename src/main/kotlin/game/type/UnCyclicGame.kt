package game.type

import game.abstract.Game

class UnCyclicGame : Game {

    constructor(rows: Int, cols: Int){
        this.matrix = Array(rows) { IntArray(cols) }
        this.rows = rows
        this.cols = cols
    }
    constructor(){
        this.matrix = Array(rows){IntArray(cols)}

    }
    constructor(matrix: Array<IntArray>){
        this.matrix = matrix
        this.rows = matrix.size
        this.cols = matrix[0].size

    }

    override fun neighbors(row: Int, col: Int): Int{
        var count = 0
        for (i in -1..1){
            for (j in -1..1){
                if (i == 0 && j == 0) continue
                val nx = i + row
                val ny = j + col

                if ((nx >= 0 && nx < rows) && (ny >= 0 && ny < cols)){
                    count += matrix[nx][ny]

                }
            }
        }

        return count
    }

}