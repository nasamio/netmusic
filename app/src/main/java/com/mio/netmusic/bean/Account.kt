package com.mio.netmusic.bean

import kotlinx.serialization.Serializable

@Serializable
data class Account(
    val id: Long,                // JSON中id较大，建议用Long
    val userName: String,
    val type: Int,
    val status: Int,
    val whitelistAuthority: Int,
    val createTime: Long,
    val tokenVersion: Int,
    val ban: Int,
    val baoyueVersion: Int,
    val donateVersion: Int,
    val vipType: Int,
    val anonimousUser: Boolean,
    val paidFee: Boolean
)