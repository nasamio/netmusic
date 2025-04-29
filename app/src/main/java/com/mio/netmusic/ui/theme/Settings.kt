package com.mio.netmusic.ui.theme

import kotlinx.coroutines.flow.MutableStateFlow

/**
 * 设置项
 */
object Settings {
    val isLightMode = MutableStateFlow(false).apply {
        value = App.sp.getBoolean("isLightMode", false)
    }
}