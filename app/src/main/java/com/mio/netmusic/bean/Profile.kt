package com.mio.netmusic.bean

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val userId: Long?,
    val userType: Int?,
    val nickname: String?,
    val avatarImgId: Long?,
    val avatarUrl: String?,
    val backgroundImgId: Long?,
    val backgroundUrl: String?,
    val signature: String?,
    val createTime: Long?,
    val userName: String?,
    val accountType: Int?,
    val shortUserName: String?,
    val birthday: Long?,
    val authority: Int?,
    val gender: Int?,
    val accountStatus: Int?,
    val province: Int?,
    val city: Int?,
    val authStatus: Int?,
    val description: String?,
    val detailDescription: String?,
    val defaultAvatar: Boolean?,
    val expertTags: String?,
    val experts: String?,
    val djStatus: Int?,
    val locationStatus: Int?,
    val vipType: Int?,
    val followed: Boolean?,
    val mutual: Boolean?,
    val authenticated: Boolean?,
    val lastLoginTime: Long?,
    val lastLoginIP: String?,
    val remarkName: String?,
    val viptypeVersion: Long?,
    val authenticationTypes: Int?,
    val avatarDetail: String?,   // 如果api返回结构复杂可能定义为其他类型，暂用String或Any?
    val anchor: Boolean?
)