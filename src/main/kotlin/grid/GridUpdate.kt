package grid


import androidx.compose.runtime.Composable
import game.abstract.Game

@Composable
fun gridUpdate( clickCoordinates: Pair<Float, Float>?,  cellSize: Int, game: Game, changeCells: MutableList<IntArray>) {
    clickCoordinates?.let { (x, y) ->
        if (x != -1f && y != -1f) {
            game.matrix[y.toInt()/(cellSize)][x.toInt()/(cellSize)] = 1 - game.matrix[y.toInt()/(cellSize)][x.toInt()/(cellSize)]
        }


    }





}