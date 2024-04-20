package com.example.universitiesapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.universitiesapp.common.Constants
import com.example.universitiesapp.presentation.favorite.FavoriteScreen
import com.example.universitiesapp.presentation.home.HomeScreen
import com.example.universitiesapp.presentation.home.HomeViewModel
import com.example.universitiesapp.presentation.root.graph.RootNavigationGraph
import com.example.universitiesapp.presentation.root.screen.Screen
import com.example.universitiesapp.presentation.web_view.WebViewScreen
import com.example.universitiesapp.ui.theme.UniversitiesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel : HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            val data = viewModel.pagingData.collectAsLazyPagingItems()
            splashScreen.setKeepOnScreenCondition{ data.itemCount == 0 }
            if (data.loadState.refresh is LoadState.Error) {
                val errorMessage = (data.loadState.refresh as LoadState.Error).error.localizedMessage
                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
            }
            UniversitiesAppTheme {
                val navController = rememberNavController()
                RootNavigationGraph(pagingData = data, navController = navController)
            }
        }
    }
}