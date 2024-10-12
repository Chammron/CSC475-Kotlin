package com.cameron.helloandroid

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
import com.cameron.helloandroid.ui.theme.HelloAndroidTheme

//MainActivity = Main class.
class MainActivity : ComponentActivity() {
    //Application launch function. Override parent function.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Fullscreen.
        enableEdgeToEdge()
        //Format = Jetpack Compose vs. XML.
        setContent {
            HelloAndroidTheme {
                //Screen structure
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //Function called. Name set to Android.
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

//Prints to screen. $ allows values in strings.
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

//If using emulator, this part is not used. Just shows the results w/o emulator.
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HelloAndroidTheme {
        //If preview is executed this would be the variable used. Can be different from original.
        Greeting("Android")
    }
}