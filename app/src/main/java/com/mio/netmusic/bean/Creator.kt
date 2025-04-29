package com.mio.netmusic.bean

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Creator(
    val accountStatus: Int?,
    val anchor: Boolean?,
    val authStatus: Int?,
    val authenticationTypes: Int?,
    val authority: Int?,
    val avatarDetail: String?,
    val avatarImgId: Long?,
    val avatarImgIdStr: String?,
    val avatarUrl: String?,
    val backgroundImgId: Long?,
    val backgroundImgIdStr: String?,
    val backgroundUrl: String?,
    val birthday: Int?,
    val city: Int?,
    val defaultAvatar: Boolean?,
    val description: String?,
    val detailDescription: String?,
    val djStatus: Int?,
    val expertTags: Array<String>?,
    val experts: Experts?,
    val followed: Boolean?,
    val gender: Int?,
    val mutual: Boolean?,
    val nickname: String?,
    val province: Int?,
    val remarkName: String?,
    val signature: String?,
    val userId: Int?,
    val userType: Int?,
    val vipType: Int?,
)

@Serializable
data class Experts(
    @SerialName("1")
    val a: String?,
    @SerialName("2")
    val b: String?,
)