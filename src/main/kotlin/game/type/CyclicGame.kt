package game.type

import game.abstract.Game

class CyclicGame : Game {
    constructor(rows: Int, cols: Int){
        this.matrix = Array(rows) { IntArray(cols) }
        this.rows = rows
        this.cols = cols
    }
    constructor(){
        this.matrix = Array(8){IntArray(8)}

    }

    override fun neighborsNP(row: Int, col: Int): Int{
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