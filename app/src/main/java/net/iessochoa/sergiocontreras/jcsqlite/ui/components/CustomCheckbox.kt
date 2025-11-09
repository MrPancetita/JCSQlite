package net.iessochoa.sergiocontreras.jcsqlite.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import net.iessochoa.sergiocontreras.jcsqlite.R

/**
 * Project: JC SQlite
 * From: net.iessochoa.sergiocontreras.jcsqlite.ui.components
 * Created by: Contr
 * On: 09/11/2025 at 21:48
 * Creado en Settings -> Editor -> File and Code Templates
 */

@Preview(showBackground = true)
@Composable
fun CustomCheckBoxPreview(){
    CustomCheckBox(checked = true, onCheckedChange = {})
}


@Composable //Interesante patron de superposición...
fun CustomCheckBox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
){
    IconButton(
        onClick = {onCheckedChange(!checked)}
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_favorite_border),
            contentDescription = "Favorite Icon Checkbox",
            tint = colorResource(R.color.red_300)
        )
        AnimatedVisibility(
            visible = checked,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_favorite),
                contentDescription = "Favorite Icon Checkbox",
                tint = colorResource(R.color.red_700)
            )
        }
    }


}