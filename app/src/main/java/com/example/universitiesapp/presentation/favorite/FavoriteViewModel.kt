package com.example.universitiesapp.presentation.favorite

import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universitiesapp.data.mapper.toUniversity
import com.example.universitiesapp.domain.model.University
import com.example.universitiesapp.domain.use_case.DeleteUniversityFromFavorites
import com.example.universitiesapp.domain.use_case.GetFavoriteUniversities
import com.example.universitiesapp.domain.use_case.MakePhoneCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteUniversities: GetFavoriteUniversities,
    private val deleteUniversityFromFavorites: DeleteUniversityFromFavorites,
    private val makePhoneCall: MakePhoneCall,
) : ViewModel() {

    private val _favoriteState = MutableStateFlow(FavoriteState())
    val favoriteState : StateFlow<FavoriteState> get() = _favoriteState.asStateFlow()

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                getFavoriteUniversities().collect {universityEntityList ->
                    _favoriteState.value = FavoriteState(data = universityEntityList.map { it.toUniversity() })
                }
            } catch (e : Exception) {
                _favoriteState.value = FavoriteState(error = e.localizedMessage ?: "Error !")
            }
        }
    }

    fun deleteFavorite(university: University) = viewModelScope.launch {
        deleteUniversityFromFavorites(university)
    }

    fun viewPhoneCall(
        phoneNumber: String,
        phoneCallPermissionResultLauncher: ManagedActivityResultLauncher<String, Boolean>,
    ) {
        makePhoneCall(phoneNumber, phoneCallPermissionResultLauncher)
    }

}