package com.mio.netmusic.utlils

import LogUtils
import com.mio.netmusic.bean.BaseResponse
import com.mio.netmusic.bean.LoginData
import com.mio.netmusic.bean.PlayListResponse
import com.mio.netmusic.bean.QrCheck
import com.mio.netmusic.bean.QrImg
import com.mio.netmusic.bean.QrKey
import com.mio.netmusic.bean.SongResponse
import com.mio.netmusic.ui.theme.App
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.serialization.json.Json


/**
 * Ktor 网络请求封装
 */
object KtorHelper {
    const val LOCAL_TEST = false
    const val BASE_URL = "1.94.134.14"
    const val PORT = 3001

    val httpClient: HttpClient
        get() = HttpClient(Android) {
            install(DefaultRequest) {
                url {
                    host = BASE_URL
                    port = PORT
                }
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
        }

    fun close() {
        httpClient.close()
    }

    private inline fun <reified T> get(
        url: String,
        headers: Map<String, String> = emptyMap(),
        params: Map<String, String> = emptyMap(),
        crossinline onError: (Throwable) -> Unit = {
            logError("Error occurred during network request", it)
        },
    ): Flow<T> {
        LogUtils.d("get: 请求接口:$url")
        return flow {
            val response: HttpResponse = httpClient.get(url) {
                headers.forEach { header(it.key, it.value) }
                params.forEach { parameter(it.key, it.value) }
            }
            val result = response.body<T>()
            emit(result)
        }.catch { throwable: Throwable ->
            onError(throwable)
        }.onCompletion { cause ->
            close()
        }.flowOn(Dispatchers.Default)
    }

    private inline fun <reified T> post(
        url: String,
        params: Map<String, String> = emptyMap(),
        headers: Map<String, String> = emptyMap(),
        body: Any? = null,  // 新增 body 参数，默认为 null
    ): Flow<T> {
        return flow {
            val response: HttpResponse = httpClient.post(url) {
                params.forEach { parameter(it.key, it.value) }
                headers.forEach { header(it.key, it.value) }
                body?.let {
                    contentType(ContentType.Application.Json)
                    setBody(it)
                }
            }
            val result = response.body<T>()
            emit(result)
        }.catch { throwable: Throwable ->
            logError("Error occurred during network request", throwable)
        }.onCompletion { cause ->
            close()
        }.flowOn(Dispatchers.Default)
    }


    fun logError(message: String, throwable: Throwable) {
        // 这里可以使用你喜欢的日志框架进行日志记录
        // 例如，使用LogUtils.d简单输出到控制台
        LogUtils.d("Net Error:$message: ${throwable.message}")
        throwable.printStackTrace()
    }


    suspend fun qrKey() = get<BaseResponse<QrKey>>(
        url = "/login/qr/key",
        params = mapOf(),
    )

    suspend fun qrImg(key: String) = get<BaseResponse<QrImg>>(
        url = "/login/qr/create",
        params = mapOf(
            "key" to key,
            "qrimg" to "true",
        ),
    )

    suspend fun qrCheck(key: String) = get<QrCheck>(
        url = "/login/qr/check",
        params = mapOf(
            "key" to key,
            "timestamp" to System.currentTimeMillis().toString(),
        ),
    )

    /**
     * 以下所有登录后的接口都需要登录状态 请求接口带cookie
     */

    suspend fun loginStatus() = get<BaseResponse<LoginData>>(
        url = "/login/status",
        params = mapOf(
            "timestamp" to System.currentTimeMillis().toString(),
            "cookie" to App.cookie.value,
        ),
    )

   suspend fun playlist(userId: Long?)  = get<PlayListResponse>(
        url = "/user/playlist",
        params = mapOf(
            "uid" to userId.toString(),
            "timestamp" to System.currentTimeMillis().toString(),
            "cookie" to App.cookie.value,
        ),
    )

    fun getSongs(songId: String) = get<SongResponse>(
        url = "/playlist/track/all",
        params = mapOf(
            "id" to songId.toString(),
            "timestamp" to System.currentTimeMillis().toString(),
            "cookie" to App.cookie.value,
        ),
    )

}

fun Int.isOk() = this == 200