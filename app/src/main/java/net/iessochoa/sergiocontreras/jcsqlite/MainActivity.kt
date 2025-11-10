package net.iessochoa.sergiocontreras.jcsqlite

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import net.iessochoa.sergiocontreras.jcsqlite.sqlite.DatabaseHelper
import net.iessochoa.sergiocontreras.jcsqlite.ui.theme.JCSQliteTheme

class MainActivity : ComponentActivity() {

    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JCSQliteTheme {

                var parks by remember { mutableStateOf(emptyList<Park>()) }
                // parks = parksPreview

                Scaffold(
                    floatingActionButton = {
                        ExtendedFloatingActionButton(
                            onClick = { launchAdd() },
                            icon = {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.ic_add),
                                    contentDescription = "Add button icon",
                                )
                            },
                            text = {
                                Text(stringResource(R.string.main_efab_add))
                            }
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    MainView(
                        modifier = Modifier.padding(innerPadding),
                        parks = parks,
                        onClick = {},
                        onLongClick = {}
                    )
                }

                val lifecycleObserver = LifecycleEventObserver {_, event ->
                    when (event) {
                        Lifecycle.Event.ON_RESUME  -> {
                            lifecycleScope.launch {
                                refreshData { result ->
                                    parks = result
                                }
                            }

                        }
                        else -> {}
                    }
                }
                val lifecycle = LocalLifecycleOwner.current.lifecycle
                DisposableEffect(lifecycle) {
                    lifecycle.addObserver(lifecycleObserver)
                    onDispose {
                        lifecycle.removeObserver(lifecycleObserver)
                    }
                }


            }
        }

        setupDatabase()


    }

    private fun setupDatabase() {
        db = DatabaseHelper(this)
    }

    private fun launchAdd() {
        val intent = Intent(this, AddActivity::class.java)
        startActivity(intent)
    }

    private fun refreshData(onResult: (List<Park>) -> Unit) {
        val parks = db.getAllParks()
        onResult(parks)


    }
}



@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    JCSQliteTheme {
        MainViewPreview()
    }
}