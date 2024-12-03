package components
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Text
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

import androidx.compose.ui.unit.dp
import jdk.jfr.Enabled


interface BtnSize{
    val width: Dp
    val height: Dp
}

object MediumBtn: BtnSize{
    override var width: Dp = 270.dp
    override var height: Dp = 40.dp
}

object ErrorSize: BtnSize{
    override var width: Dp = 450.dp
    override var height: Dp = 100.dp
}




@Composable
fun coolButton(
    onClick: ()->Unit,
    size: BtnSize,
    text: String,
    enabled: Boolean = true,
    backgroundColor: Color = Color(1,  1, 1)
)
    {

    Button(
        onClick = onClick,
        modifier = Modifier.size(width=size.width, height=size.height),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = Color.White),
            enabled = enabled

    ){
        Text(text)
    }


}
