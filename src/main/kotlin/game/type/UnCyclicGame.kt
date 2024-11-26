package game.type

import game.abstract.Game

class UnCyclicGame : Game {

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