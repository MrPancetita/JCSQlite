package net.iessochoa.sergiocontreras.jcsqlite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iessochoa.sergiocontreras.jcsqlite.ui.components.CustomCheckBox
import net.iessochoa.sergiocontreras.jcsqlite.ui.theme.JCSQliteTheme

/**
 * Project: JC SQlite
 * From: net.iessochoa.sergiocontreras.jcsqlite
 * Created by: Contr
 * On: 09/11/2025 at 10:12
 * Creado en Settings -> Editor -> File and Code Templates
 */

@Preview (showBackground = true)
@Composable
fun AddViewPreview() {
    JCSQliteTheme {
        AddView(
            modifier = Modifier.padding(top = 24.dp),
            onSave = {_, _ -> }
        )

    }
}

@Composable
fun AddView(modifier: Modifier, onSave: (String, Boolean) -> Unit) {

    var nameValue by remember {mutableStateOf("")}
    var isFavorite by remember {mutableStateOf(false)}
    var isError by remember { mutableStateOf(false)}

    val maxLength = integerResource(R.integer.name_max_length)

    Column (
        modifier.padding(dimensionResource(R.dimen.common_padding_default))
    ) {
        OutlinedTextField(
            value = nameValue,
            onValueChange = {
                if (it.length <= maxLength) nameValue = it
                isError = nameValue.isEmpty()
            },
            label = {
                Text(stringResource(R.string.add_hint_name))
            },
            isError = isError,
            modifier = Modifier.fillMaxWidth(),
            supportingText = { //Esto es para el contador de caracteres
                Row {
                    if (isError) {
                        Text(stringResource(R.string.add_field_required))
                    }
                    Text(
                        "${nameValue.length} / $maxLength",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.End
                    )
                }
            }
        )
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            /*Checkbox(
                checked = isFavorite,
                onCheckedChange = {isFavorite = it}
            )*/
            CustomCheckBox(
                checked = isFavorite,
                onCheckedChange = {isFavorite = it}
            )
            Text(stringResource(R.string.add_cb_favorite),
                modifier= Modifier.padding(vertical = dimensionResource(R.dimen.common_padding_default))
            )
        }

        Button(
            onClick = { onSave(nameValue, isFavorite) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.common_padding_default))
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_save_alt),
                contentDescription = "Icon save"
            )
            Text(stringResource(R.string.add_btn_save), modifier = Modifier.padding(start = dimensionResource(R.dimen.common_padding_min)))
        }

    }

}