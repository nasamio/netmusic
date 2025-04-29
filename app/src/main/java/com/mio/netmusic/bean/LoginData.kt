package com.mio.netmusic.bean

import kotlinx.serialization.Serializable

@Serializable
data class LoginData(
    val code: Int?,
    val account: Account?,
    val profile: Profile?
)