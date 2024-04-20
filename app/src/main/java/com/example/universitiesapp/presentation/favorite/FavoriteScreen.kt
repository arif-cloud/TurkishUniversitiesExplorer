package com.example.universitiesapp.presentation.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.universitiesapp.presentation.home.component.UniversityItem
import com.example.universitiesapp.ui.theme.Red
import com.example.universitiesapp.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    navController: NavController,
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val state by viewModel.favoriteState.collectAsState()
    val expandedIndexes = rememberSaveable(
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
                        text = "Favorilerim",
                        style = Typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        content = {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "back_icon"
                            )
                        }
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
                state.data?.let {universities ->
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        itemsIndexed(universities) {index, university ->
                            UniversityItem(
                                university = university,
                                isExpanded = expandedIndexes.contains(index),
                                isFavorite = true,
                                onClickExpand = { if (expandedIndexes.contains(index)) expandedIndexes.remove(index) else expandedIndexes.add(index) },
                                onAddFavorite = { /*TODO*/ },
                                onDeleteFavorite = { viewModel.deleteFavorite(university) },
                                onRedirectToPhoneCall = { viewModel.viewPhoneCallScreen(university.phone ?: "") },
                                navController = navController
                            )
                        }
                    }
                    if (universities.isEmpty()) {
                        Text(
                            text = "Henüz bir üniversite eklemedin!",
                            modifier = Modifier.align(Alignment.Center),
                            style = Typography.titleMedium
                        )
                    }
                }
                if (state.error.isNotEmpty()) {
                    Text(
                        text = state.error,
                        modifier = Modifier.align(Alignment.Center),
                        color = Red
                    )
                }
            }
        }
    )
}