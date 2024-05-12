package com.example.universitiesapp.presentation.home

import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.universitiesapp.data.remote.api.dto.UniversityData
import com.example.universitiesapp.domain.model.University
import com.example.universitiesapp.domain.use_case.AddUniversityToFavorites
import com.example.universitiesapp.domain.use_case.CheckUniversityIsFavorite
import com.example.universitiesapp.domain.use_case.DeleteUniversityFromFavorites
import com.example.universitiesapp.domain.use_case.GetUniversities
import com.example.universitiesapp.domain.use_case.MakePhoneCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getUniversities: GetUniversities,
    private val addUniversityToFavorites: AddUniversityToFavorites,
    private val deleteUniversityFromFavorites: DeleteUniversityFromFavorites,
    private val checkUniversityIsFavorite: CheckUniversityIsFavorite,
    private val makePhoneCall: MakePhoneCall,
) : ViewModel() {

    private val _pagingData = getUniversities().cachedIn(scope = viewModelScope)
    val pagingData : Flow<PagingData<UniversityData>> get() = _pagingData

    fun addFavorite(university: University) = viewModelScope.launch {
        addUniversityToFavorites(university)
    }

    fun deleteFavorite(university: University) = viewModelScope.launch {
        deleteUniversityFromFavorites(university)
    }

    fun checkFavorite(universityName: String): Flow<Boolean> = callbackFlow {
        val job = viewModelScope.launch {
            checkUniversityIsFavorite(universityName).flowOn(Dispatchers.IO).collect { isFavorite ->
                trySend(isFavorite)
            }
        }
        awaitClose { job.cancel() }
    }

    fun viewPhoneCall(
        phoneNumber: String,
        phoneCallPermissionResultLauncher: ManagedActivityResultLauncher<String, Boolean>,
    ) {
        makePhoneCall(phoneNumber, phoneCallPermissionResultLauncher)
    }

}