package com.mio.netmusic.bean

import kotlinx.serialization.Serializable

@Serializable
data class QrKey(
    val code: Int,
    val unikey: String,
)
