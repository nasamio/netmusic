package com.mio.netmusic.ui.theme

import MainPage
import android.net.Uri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mio.netmusic.ui.pages.LoginPage
import com.mio.netmusic.ui.pages.SongListPage

enum class Page(val route: String) {
    Main("main"),
    Login("login"),

    Home("Home"),
    Favorite("Favorite"),
    Message("Message"),
    Mine("Mine"),

    SongList("SongList"),
}

fun NavGraphBuilder.MainConfig(navController: NavHostController) {
    composable(Page.Main.route) { MainPage(navController) }
    composable(Page.Login.route) { LoginPage(navController) }
    composable(Page.SongList.route){ SongListPage(navController = navController )}
}
