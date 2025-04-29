package com.mio.netmusic.bean

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val code: Int = 200, // http状态码 200:正常 500:服务器异常
    val msg: String = "", // 返回消息
    val data: T? = null,
)
