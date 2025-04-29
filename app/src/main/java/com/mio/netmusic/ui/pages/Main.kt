package com.mio.netmusic.ui.pages

import LogUtils
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mio.netmusic.ui.theme.App
import com.mio.netmusic.ui.theme.MainConfig
import com.mio.netmusic.ui.theme.Page
import com.mio.netmusic.utlils.KtorHelper
import com.mio.netmusic.utlils.isOk
import kotlinx.coroutines.delay

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

    LaunchedEffect(1) {
        // 测试代码 直接跳转
        delay(400)
//        navController.navigate(
//            Page.SongList.route + "/8963563622"
//        )
    }

    if (isJudgeLogin) {
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(top = 32.dp), // 给状态栏让出一个位置
            navController = navController,
            startDestination = startDestination,
        ) {
            MainConfig(navController)
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




