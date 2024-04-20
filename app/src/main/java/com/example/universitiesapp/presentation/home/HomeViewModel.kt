package com.example.universitiesapp.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
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
import com.example.universitiesapp.domain.use_case.RedirectToPhoneCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getUniversities: GetUniversities,
    private val addUniversityToFavorites: AddUniversityToFavorites,
    private val deleteUniversityFromFavorites: DeleteUniversityFromFavorites,
    private val checkUniversityIsFavorite: CheckUniversityIsFavorite,
    private val redirectToPhoneCall: RedirectToPhoneCall
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
            checkUniversityIsFavorite(universityName).collect { isFavorite ->
                trySend(isFavorite)
            }
        }
        awaitClose { job.cancel() }
    }

    fun viewPhoneCallScreen(phoneNumber : String) {
        redirectToPhoneCall(phoneNumber)
    }

}