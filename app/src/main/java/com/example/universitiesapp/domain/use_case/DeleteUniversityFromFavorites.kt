package com.example.universitiesapp.domain.use_case

import com.example.universitiesapp.domain.model.University
import com.example.universitiesapp.domain.repository.FavoriteUniversityRepository
import javax.inject.Inject

class DeleteUniversityFromFavorites @Inject constructor(
    private val repository: FavoriteUniversityRepository
) {
    suspend operator fun invoke(university: University) {
        repository.deleteFavoriteUniversity(university)
    }
}