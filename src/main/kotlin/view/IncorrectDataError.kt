package view

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable


@OptIn(ExperimentalMaterialApi::class)
@Composable

fun incorrectDataError(showErrorDialog: Boolean, onDismiss: () -> Unit){
    if (showErrorDialog){
        AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Ошибка") },
        text = { Text("Введены некорректные данные") },
        confirmButton = {
            Button(onClick = {onDismiss()}) {
                Text("ОК")
            }
        },
    )
    }

}