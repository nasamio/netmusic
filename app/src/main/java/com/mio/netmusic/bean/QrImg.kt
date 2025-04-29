package com.mio.netmusic.bean

import kotlinx.serialization.Serializable

@Serializable
data class QrImg(
    val qrurl: String,
    val qrimg: String,
)
