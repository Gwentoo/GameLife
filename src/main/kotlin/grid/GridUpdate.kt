package grid


import androidx.compose.runtime.Composable
import game.abstract.Game

@Composable
fun gridUpdate( clickCoordinates: Pair<Float, Float>?,  cellSize: Int, game: Game, changeCells: MutableList<IntArray>) {
    print("UPD")
    clickCoordinates?.let { (x, y) ->
        game.matrix[y.toInt()/cellSize][x.toInt()/cellSize] = 1 - game.matrix[y.toInt()/cellSize][x.toInt()/cellSize]

    }




}