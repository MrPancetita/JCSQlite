package net.iessochoa.sergiocontreras.jcsqlite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import net.iessochoa.sergiocontreras.jcsqlite.ui.theme.JCSQliteTheme

class AddActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JCSQliteTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AddView(
                        modifier = Modifier.padding(innerPadding),
                        onSave = {name, isFavorite -> }
                    )
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun AddPreview() {
    JCSQliteTheme {
        AddViewPreview()
    }
}