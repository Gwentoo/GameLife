package components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import game.abstract.Game

@Composable
fun Cell(isActive: MutableState<Int>, onClick: () -> Unit, size: Int,  x: Int, y:Int) {

    Box(
        modifier = Modifier
            .offset(x.dp,  y.dp)
            .size(size.dp)
            .background(if (isActive.value == 1) Color.Black else Color.White)
            .border(1.dp, Color.Black)
            .clickable { onClick() },
    )
}