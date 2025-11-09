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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import net.iessochoa.sergiocontreras.jcsqlite.ui.theme.JCSQliteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JCSQliteTheme {

                var parks by remember { mutableStateOf(emptyList<Park>()) }
                parks = parksPreview

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainView (
                        modifier = Modifier.padding(innerPadding),
                        parks = parks,
                        onClick = {},
                        onLongClick = {}
                    )
                }
            }
        }
    }
}



@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    JCSQliteTheme {
        MainViewPreview()
    }
}