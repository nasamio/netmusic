package com.mio.netmusic.bean

import kotlinx.serialization.Serializable

@Serializable
data class QrCheck(
    val code: Int,
    val message: String,
    val cookie: String,
    val nickname: String = "",
    val avatarUrl: String = "",
)
