package com.mio.netmusic.ui.player

import kotlinx.coroutines.flow.MutableStateFlow

object PlayerHelper {
    val showFullPlayer = MutableStateFlow(false)

    val playerVisibility = MutableStateFlow(PlayerVisibility.MINI)
}

enum class PlayerVisibility {
    MINI, // 小窗口
    FULL, // 全屏
    INVISIBLE // 隐藏
}