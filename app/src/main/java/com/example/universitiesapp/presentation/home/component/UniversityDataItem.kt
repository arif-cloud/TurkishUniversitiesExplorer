package com.example.universitiesapp.presentation.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.universitiesapp.R
import com.example.universitiesapp.data.remote.api.dto.UniversityData
import com.example.universitiesapp.presentation.home.HomeViewModel
import com.example.universitiesapp.ui.theme.Orange
import com.example.universitiesapp.ui.theme.Typography

@Composable
fun UniversityDataItem(
    universityData : UniversityData?,
    isExpanded : Boolean,
    onClickUniversityDataItem : () -> Unit,
    viewModel: HomeViewModel,
    navController: NavController
) {
    val expandedUniversityIndexes = rememberSaveable(
        saver = listSaver(
            save = { stateList -> stateList.toList() },
            restore = { it.toMutableStateList() }
        )
    ) { mutableStateListOf<Int>() }
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        modifier = Modifier.clickable { onClickUniversityDataItem() },
        content = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                content = {
                    universityData?.universities?.let {universityList ->
                        if (universityList.isNotEmpty()) {
                            Icon(
                                imageVector = if (isExpanded) ImageVector.vectorResource(id = R.drawable.minus_icon) else Icons.Default.Add,
                                contentDescription = "plus_icon",
                                tint = Orange
                            )
                        }
                    }
                    Text(
                        text = universityData?.province ?: "",
                        style = Typography.titleMedium
                    )
                }
            )
        }
    )
    if (isExpanded) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 5.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            content = {
                universityData?.universities?.let {universityList ->
                    universityList.forEachIndexed { index, university ->
                        val isFavorite by viewModel.checkFavorite(university.name ?: "").collectAsStateWithLifecycle(initialValue = false)
                        UniversityItem(
                            university = university,
                            isExpanded = expandedUniversityIndexes.contains(index),
                            isFavorite = isFavorite,
                            onClickExpand = { if (expandedUniversityIndexes.contains(index)) expandedUniversityIndexes.remove(index) else expandedUniversityIndexes.add(index) },
                            onAddFavorite = { viewModel.addFavorite(university) },
                            onDeleteFavorite = { viewModel.deleteFavorite(university) },
                            onRedirectToPhoneCall = { viewModel.viewPhoneCallScreen(university.phone ?: "") },
                            navController = navController
                        )
                    }
                }
            }
        )
    }
}