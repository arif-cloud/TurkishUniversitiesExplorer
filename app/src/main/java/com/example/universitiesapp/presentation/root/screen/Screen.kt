package com.example.universitiesapp.presentation.root.screen

sealed class Screen(val route : String) {
    data object Home : Screen("home")
    data object Favorite : Screen("favorite")
    data object WebView : Screen("web_view")
}