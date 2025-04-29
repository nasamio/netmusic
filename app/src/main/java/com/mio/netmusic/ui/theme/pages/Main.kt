package com.mio.netmusic.ui.theme.pages

import MainPage
import LogUtils
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mio.netmusic.ui.theme.App
import com.mio.netmusic.ui.theme.Page
import com.mio.netmusic.utlils.KtorHelper
import com.mio.netmusic.utlils.isOk

@Composable
fun Main() {
    LogUtils.d("Main Page")
    val navController = rememberNavController()

    var isJudgeLogin by remember { mutableStateOf(false) }

    var startDestination by remember { mutableStateOf(Page.Main.route) }

    // 1. 读cookie，只执行一次
    LaunchedEffect(Unit) {
        App.cookie.value = App.sp.getString("cookie", "") ?: ""

        KtorHelper.loginStatus().collect {
            LogUtils.d("${it.data?.code}${it.data?.account}\n${it.data?.profile}")

            val hasLogin = it.code.isOk() && it.data?.account != null && it.data.profile != null

            if (hasLogin) {
                App.account.value = it.data?.account
                App.profile.value = it.data?.profile
                App.toast("登录成功")
            }
            startDestination = if (hasLogin) Page.Main.route else Page.Login.route

            App.isLogin.value = hasLogin
            isJudgeLogin = true
        }
    }

    if (isJudgeLogin) {
        NavHost(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
            navController = navController,
            startDestination = startDestination
        ) {
            composable(Page.Main.route) { MainPage(navController) }
            composable(Page.Login.route) { LoginPage(navController) }
        }
    } else {
        // 加载中界面可选
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ) {
            // todo 解决导入不了的问题
//            CircularProgressIndicator(
//                modifier = Modifier
//                    .padding(16.dp)
//                    .align(androidx.compose.ui.Alignment.Center)
//            )

            Text(text = "加载中...")
        }
    }
}




