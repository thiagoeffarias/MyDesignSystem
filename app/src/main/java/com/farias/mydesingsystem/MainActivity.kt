package com.farias.mydesingsystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.farias.mydesingsystem.ui.theme.MyDesingSystemTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyDesingSystemTheme {
                // A surface container using the 'background' color from the theme
                var buttonState by remember { mutableStateOf(ButtonLoadingState.Active) }
                val coroutineScope = rememberCoroutineScope()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(verticalArrangement = Arrangement.Bottom) {

                        ButtonLoading(
                            buttonState = buttonState,
                            label = "Loading Button Test",
                            onClick = {
                                coroutineScope.launch {
                                    buttonState =
                                        ButtonLoadingState.Loading
                                    delay(2000)
                                    buttonState =
                                        ButtonLoadingState.Inactive
                                    delay(2000)
                                    buttonState =
                                        ButtonLoadingState.Active
                                }
                            })
                    }
                }
            }
        }
    }
}
