
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import view.myScreen


@Composable
@Preview
fun app() {

    myScreen()
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = WindowState(width = 1080.dp, height = 1080.dp),
        title = "Game Life",
        icon = painterResource("icon.png")
    ) {
        app()
    }
}
