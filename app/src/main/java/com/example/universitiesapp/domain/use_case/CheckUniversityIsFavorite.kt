package com.example.universitiesapp.domain.use_case

import com.example.universitiesapp.domain.repository.FavoriteUniversityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckUniversityIsFavorite @Inject constructor(
    private val repository: FavoriteUniversityRepository
) {
    operator fun invoke(universityName: String): Flow<Boolean> {
        return repository.checkUniversityFavorite(universityName)
    }
}