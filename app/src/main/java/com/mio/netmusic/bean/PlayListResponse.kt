package com.mio.netmusic.bean

import kotlinx.serialization.Serializable

@Serializable
data class PlayListResponse(
    val code: Int?,
    val more: Boolean?,
    val playlist: List<Playlist>
)