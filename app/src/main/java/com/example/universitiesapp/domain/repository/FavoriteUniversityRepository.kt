package com.example.universitiesapp.domain.repository

import com.example.universitiesapp.data.local.db.UniversityEntity
import com.example.universitiesapp.domain.model.University
import kotlinx.coroutines.flow.Flow

interface FavoriteUniversityRepository {
    fun getFavoriteUniversities(): Flow<List<UniversityEntity>>
    fun checkUniversityFavorite(universityName: String): Flow<Boolean>
    suspend fun addFavoriteUniversity(university: University)
    suspend fun deleteFavoriteUniversity(university : University)
}