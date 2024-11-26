package view

import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.MediumBtn
import components.coolButton

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun dimError(showErrorDialog: Boolean, onDismiss: () -> Unit){
    if (showErrorDialog){AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Ошибка") },
        text = { Text("Количество строк или столбцов не может быть меньше 1") },
        confirmButton = {
            coolButton(onClick = {onDismiss()}, size = MediumBtn, text = "ОК")
        },
    )}

}