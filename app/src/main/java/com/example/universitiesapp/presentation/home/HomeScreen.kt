package com.example.universitiesapp.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.universitiesapp.R
import com.example.universitiesapp.data.remote.api.dto.UniversityData
import com.example.universitiesapp.presentation.home.component.UniversityDataItem
import com.example.universitiesapp.presentation.root.screen.Screen
import com.example.universitiesapp.ui.theme.Green
import com.example.universitiesapp.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    universitiesData : LazyPagingItems<UniversityData>,
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val expandedProvinceIndexes = rememberSaveable(
        saver = listSaver(
            save = { stateList -> stateList.toList() },
            restore = { it.toMutableStateList() }
        )
    ) { mutableStateListOf<Int>() }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Üniversiteler",
                        style = Typography.titleLarge
                    )
                },
                actions = {
                    IconButton(
                        onClick = { navController.navigate(Screen.Favorite.route) },
                        content = {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "favorite_icon"
                            )
                        }
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { expandedProvinceIndexes.clear() },
                containerColor = Green,
                content = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.collapse_icon),
                        contentDescription = "collapse_icon"
                    )
                }
            )
        },
        content = {paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(universitiesData.itemCount) {index ->
                        UniversityDataItem(
                            universityData = universitiesData[index],
                            isExpanded = expandedProvinceIndexes.contains(index),
                            onClickUniversityDataItem = { if (expandedProvinceIndexes.contains(index)) expandedProvinceIndexes.remove(index) else expandedProvinceIndexes.add(index) },
                            viewModel = viewModel,
                            navController = navController
                        )
                    }
                    if (universitiesData.loadState.append == LoadState.Loading) {
                        item {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentWidth(Alignment.CenterHorizontally)
                            )
                        }
                    }
                }
            }
        }
    )
}