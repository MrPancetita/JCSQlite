package net.iessochoa.sergiocontreras.jcsqlite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import net.iessochoa.sergiocontreras.jcsqlite.sqlite.Constants
import net.iessochoa.sergiocontreras.jcsqlite.sqlite.DatabaseHelper
import net.iessochoa.sergiocontreras.jcsqlite.ui.theme.JCSQliteTheme

class AddActivity : ComponentActivity() {

    private lateinit var db: DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JCSQliteTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()

                Scaffold(modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackbarHostState) }
                ){ innerPadding ->
                    AddView(
                        modifier = Modifier.padding(innerPadding),
                        onSave = {name, isFavorite ->
                            checkAndSave(name, isFavorite,
                                onError = {
                                    scope.launch {
                                        snackbarHostState.showSnackbar(
                                            message = getString(R.string.add_error_save),
                                            duration = SnackbarDuration.Long
                                        )
                                    }

                                })
                        }
                    )
                }
            }
        }

        setupDatabase()

    }

    private fun setupDatabase() {
        db = DatabaseHelper(this)
    }

    private fun checkAndSave(name: String, isFavorite: Boolean, onError: () -> Unit) {
        if(name.isNotEmpty()) {
            val park = Park(name = name, isFavorite = isFavorite)
            savePark(park) { isSuccess ->
                if (isSuccess) {
                    finish()
                } else {
                    onError()
                }
            }
        }
    }

    private fun savePark(park: Park, onFinished: (Boolean) -> Unit) {
        park.id = db.insertPark(park)
        onFinished(park.id != Constants.ERROR_ID)
    }
}





@Preview(showBackground = true)
@Composable
fun AddPreview() {
    JCSQliteTheme {
        AddViewPreview()
    }
}