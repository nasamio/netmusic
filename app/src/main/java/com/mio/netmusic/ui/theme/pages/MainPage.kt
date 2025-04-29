import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mio.netmusic.ui.theme.Page
import com.mio.netmusic.ui.theme.pages.HomePage
import com.mio.netmusic.ui.theme.pages.MinePage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(outNavController: NavHostController) {
    val innerNavController = rememberNavController()
    val bottomList = listOf(
        BottomItem("首页", Icons.Rounded.Home, Page.Home.route),
        BottomItem("收藏", Icons.Rounded.Favorite, Page.Favorite.route),
        BottomItem("消息", Icons.Rounded.Email, Page.Message.route),
        BottomItem("我的", Icons.Rounded.Person, Page.Mine.route),
    )
    // 监听当前路由，决定底部栏选中项
    val currentBackStackEntry by innerNavController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route ?: Page.Home.route
    // 计算选中索引
    val selectedIndex = bottomList.indexOfFirst { it.route == currentRoute }.takeIf { it >= 0 } ?: 0
    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            AnimatedVisibility(
                visible = selectedIndex == 0,
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> -fullHeight }
                ),
                exit = slideOutVertically(
                    targetOffsetY = { fullHeight -> -fullHeight }
                )
            ) {
                TopAppBar(title = { Text(text = "Net Music") }, actions = {
                    Icon(imageVector = Icons.Rounded.Menu, contentDescription = null)
                })
            }
        },
        bottomBar = {
            BottomNav(
                modifier = Modifier,
                bottomList = bottomList,
                selectedIndex = selectedIndex
            ) { route ->
                innerNavController.navigate(route) {
                    launchSingleTop = true
                    restoreState = true
                    popUpTo(innerNavController.graph.startDestinationId) {
                        saveState = true
                    }
                }
            }
        }
    ) { padding ->
        HomeContent(innerNavController, outNavController, padding)
    }
}

@Composable
fun HomeContent(
    innerNavController: NavHostController,
    outNavController: NavHostController,
    paddingValues: PaddingValues
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        NavHost(navController = innerNavController, startDestination = Page.Home.route) {
            composable(Page.Home.route) {
                HomePage(innerNavController, outNavController)
            }
            composable(Page.Favorite.route) {
                HomePage(innerNavController, outNavController)
            }
            composable(Page.Message.route) {
                HomePage(innerNavController, outNavController)
            }
            composable(Page.Mine.route) {
                MinePage(innerNavController, outNavController)
            }
        }
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