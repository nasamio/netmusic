package com.mio.netmusic.ui.theme.pages

import LogUtils
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mio.netmusic.ui.theme.App
import com.mio.netmusic.ui.theme.Page
import com.mio.netmusic.utlils.KtorHelper
import com.mio.netmusic.utlils.isOk
import kotlinx.coroutines.delay

@Composable
fun LoginPage(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    var qrImgBase64 by remember { mutableStateOf("") }
    val bitmap = remember(qrImgBase64) {
        if (qrImgBase64.startsWith("data:image/png;base64,")) {
            val base64Str = qrImgBase64.removePrefix("data:image/png;base64,")
            try {
                val bytes = Base64.decode(base64Str, Base64.DEFAULT)
                BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        } else {
            null
        }
    }
    var loginText by remember { mutableStateOf("") }

    LaunchedEffect(bitmap) {
        if (bitmap != null && App.qrKey.isNotEmpty()) {
            while (App.cookie.value.isEmpty()) {
                LogUtils.d("准备请求 qr check:${App.qrKey}")
                // 800 为二维码过期,801 为等待扫码,802 为待确认,803 为授权登录成功(803 状态码下会返回 cookies),如扫码后返回502,则需加上noCookie参数,如&noCookie=true
                KtorHelper.qrCheck(App.qrKey).collect {
                    LogUtils.d("qr check$it")
                    when (it.code) {
                        800 -> {
                            loginText = "二维码已过期，请重新扫码"
                            // todo 实现点击刷新
                        }

                        801 -> loginText = "请打开网易云音乐APP扫码登录"


                        802 -> loginText = "已扫描，待确认"


                        803 -> {
                            App.sp.saveString("cookie", it.cookie)
                            App.cookie.value = it.cookie
                            loginText = "登录成功"
                            App.toast("登录成功,即将跳转...")
                            navController.navigate(Page.Main.route)
                        }
                    }

                    delay(2_000)
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        KtorHelper.qrKey().collect {
            LogUtils.d("qr key:$it")
            if (it.code.isOk()) {
                App.qrKey = it.data!!.unikey
                KtorHelper.qrImg(App.qrKey).collect {
                    LogUtils.d("qr img:$it")
                    if (it.code.isOk()) {
                        qrImgBase64 = it.data!!.qrimg
                    } else {
//                        App.toast("获取qr img失败，请退出应用稍后再试...")
                        loginText = "获取qr img失败，请退出应用稍后再试..."
                    }
                }
            } else {
//                App.toast("获取qr key失败，请退出应用稍后再试...")
                loginText = "获取qr key失败，请退出应用稍后再试..."
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        bitmap?.let {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    modifier = Modifier.size(200.dp),
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = "QR Code",
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = loginText)
            }

        } ?: Text(text = "二维码加载失败，请退出应用稍后再试...")


    }
}
