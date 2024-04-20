package com.example.universitiesapp.presentation.root.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.compose.LazyPagingItems
import com.example.universitiesapp.common.Constants
import com.example.universitiesapp.data.remote.api.dto.UniversityData
import com.example.universitiesapp.presentation.favorite.FavoriteScreen
import com.example.universitiesapp.presentation.home.HomeScreen
import com.example.universitiesapp.presentation.root.screen.Screen
import com.example.universitiesapp.presentation.web_view.WebViewScreen

@Composable
fun RootNavigationGraph(
    pagingData : LazyPagingItems<UniversityData>,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(pagingData, navController)
        }
        composable(route = Screen.Favorite.route) {
            FavoriteScreen(navController)
        }
        composable(
            route = "${Screen.WebView.route}/{${Constants.PARAM_UNIVERSITY_NAME}}/{${Constants.PARAM_WEBSITE_URL}}",
            arguments = listOf(
                navArgument(Constants.PARAM_UNIVERSITY_NAME) { NavType.StringType },
                navArgument(Constants.PARAM_WEBSITE_URL) { NavType.StringType }
            )
        ) {backStackEntry ->
            WebViewScreen(
                universityName = backStackEntry.arguments?.getString(Constants.PARAM_UNIVERSITY_NAME),
                websiteUrl = backStackEntry.arguments?.getString(Constants.PARAM_WEBSITE_URL),
                navController = navController
            )
        }
    }
}