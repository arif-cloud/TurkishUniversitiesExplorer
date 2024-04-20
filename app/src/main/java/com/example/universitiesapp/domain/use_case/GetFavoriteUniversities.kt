package com.example.universitiesapp.domain.use_case

import com.example.universitiesapp.data.local.db.UniversityEntity
import com.example.universitiesapp.domain.repository.FavoriteUniversityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteUniversities @Inject constructor(
    private val repository: FavoriteUniversityRepository
) {
    suspend operator fun invoke() : Flow<List<UniversityEntity>> {
        return repository.getFavoriteUniversities()
    }
}