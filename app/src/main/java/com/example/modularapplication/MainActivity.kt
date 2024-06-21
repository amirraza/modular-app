package com.example.modularapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.main_sdk.MainSDKManager
import com.example.main_sdk.ServiceTypes
import com.example.main_sdk.StreamParams
import com.example.main_sdk.StreamStatus
import com.example.main_sdk.StreamStatusListener
import com.example.modularapplication.ui.theme.ModularApplicationTheme

class MainActivity : ComponentActivity(), StreamStatusListener {

    private var statusText by mutableStateOf("")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val manager = MainSDKManager()
        manager.initialize()
        manager.addStatusListener(this)

        setContent {
            ModularApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Greeting("Modular Application")

                        Spacer(modifier = Modifier.height(36.dp))

                        Text(text = statusText, fontSize = 20.sp)

                        Spacer(modifier = Modifier.height(16.dp))

                        FilledTonalButton(
                            shape = RoundedCornerShape(100.dp),
                            onClick = {
                                manager.start(
                                    StreamParams(
                                        name = "Download service",
                                        type = ServiceTypes.DOWNLOAD
                                    )
                                )
                            }) {
                            Text(text = "Start Download")
                        }

                        FilledTonalButton(
                            shape = RoundedCornerShape(100.dp),
                            onClick = {
                                manager.cancel(
                                    StreamParams(
                                        name = "Download service",
                                        type = ServiceTypes.DOWNLOAD
                                    )
                                )
                            }) {
                            Text(text = "Cancel Download")
                        }
                        FilledTonalButton(
                            shape = RoundedCornerShape(100.dp),
                            onClick = {
                                manager.stop(
                                    StreamParams(
                                        name = "Download service",
                                        type = ServiceTypes.DOWNLOAD
                                    )
                                )
                            }) {
                            Text(text = "Stop Download")
                        }

                        Spacer(modifier = Modifier.height(32.dp))

                        FilledTonalButton(
                            shape = RoundedCornerShape(100.dp),
                            onClick = {
                                manager.start(
                                    StreamParams(
                                        name = "Upload service",
                                        type = ServiceTypes.UPLOAD
                                    )
                                )
                            }) {
                            Text(text = "Start Upload")
                        }

                        FilledTonalButton(
                            shape = RoundedCornerShape(100.dp),
                            onClick = {
                                manager.cancel(
                                    StreamParams(
                                        name = "Upload service",
                                        type = ServiceTypes.UPLOAD
                                    )
                                )
                            }) {
                            Text(text = "Cancel Upload")
                        }
                        FilledTonalButton(
                            shape = RoundedCornerShape(100.dp),
                            onClick = {
                                manager.stop(
                                    StreamParams(
                                        name = "Upload service",
                                        type = ServiceTypes.UPLOAD
                                    )
                                )
                            }) {
                            Text(text = "Stop Upload")
                        }
                    }
                }
            }
        }
    }

    override fun onStatusChange(status: StreamStatus) {
        statusText = when (status) {
            is StreamStatus.OnStart -> {
                status.message
            }

            is StreamStatus.OnInProgress -> {
                status.message
            }

            is StreamStatus.OnError -> {
                status.message
            }

            is StreamStatus.OnCompleted -> {
                status.message
            }
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
        fontSize = 24.sp
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ModularApplicationTheme {
        Greeting("Android")
    }
}