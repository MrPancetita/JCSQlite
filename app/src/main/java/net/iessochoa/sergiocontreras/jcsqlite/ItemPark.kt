package net.iessochoa.sergiocontreras.jcsqlite

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import net.iessochoa.sergiocontreras.jcsqlite.ui.components.CustomCheckBox
import net.iessochoa.sergiocontreras.jcsqlite.ui.theme.JCSQliteTheme
import net.iessochoa.sergiocontreras.jcsqlite.ui.theme.Typography

/**
 * Project: JC SQlite
 * From: net.iessochoa.sergiocontreras.jcsqlite
 * Created by: Contr
 * On: 08/11/2025 at 22:48
 * Creado en Settings -> Editor -> File and Code Templates
 */

@Preview(showBackground = true)
@Composable
fun ItemParkPreview() {
    JCSQliteTheme() {
        ItemParkView(
            park = parkPreview,
            onClick = { },
            onLongClick = { }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemParkView(park: Park, onClick: (Park) -> Unit, onLongClick: (Park) -> Unit) {
    var isFavorite by remember { mutableStateOf(park.isFavorite) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.common_padding_min))
            .combinedClickable(
                onClick = { },
                onLongClick = { onLongClick(park) }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_park),
            contentDescription = "Icon Place",
            tint = colorResource(R.color.teal_700)
        )
        Text (
            park.name,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = dimensionResource(R.dimen.common_padding_min)),
            style = Typography.headlineSmall
        )

        /*Checkbox(
            checked = isFavorite,
            onCheckedChange = {
                isFavorite = it
                park.isFavorite = isFavorite
                onClick(park)
            }
        )
        */
        CustomCheckBox (
            checked = isFavorite,
            onCheckedChange = {
                isFavorite = it
                park.isFavorite = isFavorite
                 onClick(park)
            }
        )


}

}