package net.iessochoa.sergiocontreras.jcsqlite.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import net.iessochoa.sergiocontreras.jcsqlite.R

/**
 * Project: JC SQlite
 * From: net.iessochoa.sergiocontreras.jcsqlite.ui.components
 * Created by: Contr
 * On: 10/11/2025 at 12:04
 * Creado en Settings -> Editor -> File and Code Templates
 */

@Composable
fun CustomDialogInfo(
    info: String,
    titleRes: Int,
    confirmRes: Int = R.string.dialog_ok,
    onDismissRequest: (Boolean) -> Unit) {

    AlertDialog(
        onDismissRequest = { onDismissRequest(false) },
        title = { Text(text = stringResource(id = titleRes)) },
        text = { Text(text = info) },
        confirmButton = {
            TextButton(onClick = { onDismissRequest(true) }) {
                Text(text = stringResource(id = confirmRes))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest(false) }) {
                Text(text = stringResource(id = R.string.dialog_cancel))
            }
        }

    )

}
