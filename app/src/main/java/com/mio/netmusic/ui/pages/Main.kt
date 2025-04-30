package com.mio.netmusic.ui.pages

import LogUtils
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mio.netmusic.ui.player.FullPlayer
import com.mio.netmusic.ui.player.Player
import com.mio.netmusic.ui.player.PlayerHelper
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

    var hasJudgeLogin by remember { mutableStateOf(false) }

    var startDestination by remember { mutableStateOf(Page.Home.route) }

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
            startDestination = if (hasLogin) Page.Home.route else Page.Login.route

            App.isLogin.value = hasLogin
            hasJudgeLogin = true
        }
    }

    LaunchedEffect(1) {
        // 测试代码 直接跳转
        delay(400)
//        navController.navigate(
//            Page.SongList.route + "/8963563622"
//        )
    }

    MainUi(navController, hasJudgeLogin, startDestination)
}

@Composable
fun MainUi(navController: NavHostController, showContent: Boolean, startDestination: String) {
    val showFullPlayer = PlayerHelper.showFullPlayer.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (showContent) {
            MainContent(navController, startDestination)
        } else {
            LoadingUi()
        }

        if (showFullPlayer.value) {
            FullPlayer()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(navController: NavHostController, startDestination: String) {
    val bottomList = listOf(
        BottomItem("首页", Icons.Rounded.Home, Page.Home.route),
        BottomItem("收藏", Icons.Rounded.Favorite, Page.Favorite.route),
        BottomItem("消息", Icons.Rounded.Email, Page.Message.route),
        BottomItem("我的", Icons.Rounded.Person, Page.Mine.route),
    )
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    var showTopBar by remember { mutableStateOf(true) }
    var showBottomBar by remember { mutableStateOf(true) }

    // 监听路由变化
    LaunchedEffect(currentBackStackEntry) {
        LogUtils.d("路由发生变化: ${currentBackStackEntry?.destination?.route}")

//        when (currentBackStackEntry?.destination?.route) {
//            Page.Home.route -> {
//                showBottomBar = true
//                showTopBar = true
//            }
//
//            Page.FullPlayer.route -> {
//                showBottomBar = false
//                showTopBar = false
//            }
//        }
    }

    val currentRoute = currentBackStackEntry?.destination?.route ?: Page.Home.route
    // 计算选中索引
    val selectedIndex = bottomList.indexOfFirst { it.route == currentRoute }.takeIf { it >= 0 } ?: 0

    LaunchedEffect(selectedIndex) {
        showTopBar = selectedIndex == 0
    }

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            AnimatedVisibility(
                visible = showTopBar,
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> -fullHeight }
                ),
                exit = slideOutVertically(
                    targetOffsetY = { fullHeight -> -fullHeight }
                )
            ) {
                TopAppBar(title = { Text(text = "Net Music") }, actions = {
                    Icon(
                        modifier = Modifier.clickable {
                            PlayerHelper.showFullPlayer.value = !PlayerHelper.showFullPlayer.value
                        },
                        imageVector = Icons.Rounded.Menu,
                        contentDescription = null,
                    )
                })
            }
        },
        bottomBar = {
            AnimatedVisibility(
                visible = showBottomBar,
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> -fullHeight }
                ),
                exit = slideOutVertically(
                    targetOffsetY = { fullHeight -> -fullHeight }
                )
            ) {
                BottomNav(
                    modifier = Modifier,
                    bottomList = bottomList,
                    selectedIndex = selectedIndex
                ) { route ->
                    navController.navigate(route) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                    }
                }
            }
        }
    ) { padding ->
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(padding), // 给状态栏让出一个位置
            navController = navController,
            startDestination = startDestination,
        ) {
            MainConfig(navController)
        }
    }
}

@Composable
fun LoadingUi() {
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

@Composable
fun BottomNav(
    modifier: Modifier,
    bottomList: List<BottomItem>,
    selectedIndex: Int,
    onItemClick: (String) -> Unit
) {
    NavigationBar(modifier = modifier) {
        bottomList.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedIndex,
                onClick = {
                    onItemClick(item.route)
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = null)
                },
                label = { Text(item.title) }
            )
        }
    }
}

data class BottomItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

