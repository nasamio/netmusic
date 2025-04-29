package com.mio.netmusic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.mio.netmusic.ui.theme.App
import com.mio.netmusic.ui.theme.NetmusicTheme
import com.mio.netmusic.ui.theme.pages.Main

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        App.initApp(this)
        super.onCreate(savedInstanceState)

        setContent {
            NetmusicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Main()
                }
            }
        }
    }

    private suspend fun test() {

    }


}

