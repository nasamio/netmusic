package com.mio.netmusic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.mio.netmusic.ui.theme.App
import com.mio.netmusic.ui.theme.NetmusicTheme
import com.mio.netmusic.ui.theme.Settings
import com.mio.netmusic.ui.theme.pages.Main

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        App.initApp(this)
        super.onCreate(savedInstanceState)

        setContent {
            val darkTheme = Settings.isLightMode.collectAsState()
            NetmusicTheme(
                darkTheme = darkTheme.value
            ) {
                Main()
            }
        }
    }

    private suspend fun test() {

    }


}

