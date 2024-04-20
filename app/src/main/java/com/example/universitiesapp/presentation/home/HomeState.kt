package com.example.universitiesapp.presentation.home

import androidx.paging.PagingData
import com.example.universitiesapp.data.remote.api.dto.UniversityData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeState(
    val data : Flow<PagingData<UniversityData>> = emptyFlow(),
    val error : String = ""
)
