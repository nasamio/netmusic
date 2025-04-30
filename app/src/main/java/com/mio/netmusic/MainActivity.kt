package com.mio.netmusic

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import com.mio.netmusic.ui.theme.App
import com.mio.netmusic.ui.theme.NetmusicTheme
import com.mio.netmusic.ui.theme.Settings
import com.mio.netmusic.ui.pages.Main
import com.mio.netmusic.ui.player.PlayerHelper

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


    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (PlayerHelper.showFullPlayer.value) {
                PlayerHelper.showFullPlayer.value = false
                return true
            }
        }

        return super.onKeyUp(keyCode, event)
    }
}

