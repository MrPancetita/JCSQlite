package net.iessochoa.sergiocontreras.jcsqlite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iessochoa.sergiocontreras.jcsqlite.ui.theme.JCSQliteTheme
import net.iessochoa.sergiocontreras.jcsqlite.ui.theme.Typography

/**
 * Project: JC SQlite
 * From: net.iessochoa.sergiocontreras.jcsqlite
 * Created by: Contr
 * On: 09/11/2025 at 09:38
 * Creado en Settings -> Editor -> File and Code Templates
 */

@Preview(showBackground = true)
@Composable
fun MainViewPreview() {
    JCSQliteTheme {
        MainView(
            modifier = Modifier.padding(24.dp),
            parks = parksPreview,
            isRefreshing = false,
            onRefresh = {},
            onClick = {},
            onLongClick = {}
        )
    }
}

@Composable
fun MainView(
    modifier: Modifier,
    parks: List<Park>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onClick: (Park) -> Unit,
    onLongClick: (Park) -> Unit
) {
    Column (
        modifier = modifier
            .padding(dimensionResource(R.dimen.common_padding_default))
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = Typography.displaySmall
        )

        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = onRefresh,
            modifier = modifier.fillMaxSize()
        ) {
            LazyColumn() {
                items(count = parks.size, key = { parks[it].id }) { index ->
                    val park = parks[index]
                    ItemParkView(
                        park = park,
                        onClick = onClick,
                        onLongClick = onLongClick
                    )

                }
            }
        }


    }

}
