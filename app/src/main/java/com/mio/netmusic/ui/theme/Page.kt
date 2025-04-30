package com.mio.netmusic.ui.theme

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.mio.netmusic.ui.pages.HomePage
import com.mio.netmusic.ui.pages.LoginPage
import com.mio.netmusic.ui.pages.MinePage
import com.mio.netmusic.ui.pages.SongListPage
import com.mio.netmusic.ui.player.FullPlayer

enum class Page(val route: String) {
    Main("main"),
    Login("login"),

    Home("Home"),
    Favorite("Favorite"),
    Message("Message"),
    Mine("Mine"),

    SongList("SongList"),


    FullPlayer("FullPlayer"),
}

fun NavGraphBuilder.MainConfig(navController: NavHostController) {
//    composable(Page.Main.route) { MainPage(navController) }
    composable(Page.Login.route) { LoginPage(navController) }
    composable(Page.SongList.route) { SongListPage(navController = navController) }
    composable(Page.Home.route) {
        HomePage(navController)
    }
    composable(Page.Favorite.route) {
        HomePage(navController)
    }
    composable(Page.Message.route) {
        HomePage(navController)
    }
    composable(Page.Mine.route) {
        MinePage(navController)
    }


    composable(Page.FullPlayer.route) {
        FullPlayer()
    }
}
