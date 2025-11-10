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
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import net.iessochoa.sergiocontreras.jcsqlite.ui.components.CustomDialogInfo
import net.iessochoa.sergiocontreras.jcsqlite.ui.theme.JCSQliteTheme

class MainActivity : ComponentActivity() {

    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JCSQliteTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()

                var parks by remember { mutableStateOf(emptyList<Park>()) }
                var openDialog by remember { mutableStateOf(false) }
                var isRefreshing by remember { mutableStateOf(false) }
                var selectedPark: Park? = null

                val refresh = {
                    isRefreshing = true
                    lifecycleScope.launch {
                        refreshData { result ->
                            parks = result
                            isRefreshing = false
                        }
                    }
                }


                // parks = parksPreview

                Scaffold(
                    snackbarHost = { SnackbarHost(snackbarHostState) },
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
                        isRefreshing = isRefreshing,
                        onRefresh =  { refresh() },
                        onClick = { park ->
                            if(!db.updatePark(park)) {
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = getString(R.string.add_error_save),
                                        duration = SnackbarDuration.Long
                                    )
                                }
                            }
                        },
                        onLongClick = { park ->
                            selectedPark = park
                            openDialog = true
                        }
                    )

                    if (openDialog) {
                        selectedPark?.let { park ->
                            CustomDialogInfo(
                                info = stringResource(R.string.dialog_message_warning),
                                titleRes = R.string.dialog_title,
                                confirmRes = R.string.dialog_delete
                            ) { delete ->
                                if (delete) {
                                    if (db.deletePark(park)) {
                                        parks = parks - park
                                    } else {
                                        scope.launch {
                                            snackbarHostState.showSnackbar(
                                                message = getString(R.string.main_error_remove),
                                                duration = SnackbarDuration.Long
                                            )
                                        }
                                    }
                                }

                                openDialog = false
                            }

                        }
                    }


                }



                val lifecycleObserver = LifecycleEventObserver {_, event ->
                    when (event) {
                        Lifecycle.Event.ON_RESUME  -> {
                                refresh()
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

    private suspend fun refreshData(onResult: (List<Park>) -> Unit) {
        val parks = db.getAllParks()
        simulateDelay()
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