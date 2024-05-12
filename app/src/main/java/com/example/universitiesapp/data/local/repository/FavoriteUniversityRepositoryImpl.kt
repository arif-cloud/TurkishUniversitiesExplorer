package com.example.universitiesapp.data.local.repository

import com.example.universitiesapp.data.local.db.UniversitiesDao
import com.example.universitiesapp.data.local.db.UniversityEntity
import com.example.universitiesapp.data.mapper.toUniversityEntity
import com.example.universitiesapp.domain.model.University
import com.example.universitiesapp.domain.repository.FavoriteUniversityRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteUniversityRepositoryImpl @Inject constructor(
    private val dao : UniversitiesDao,
    private val ioDispatcher : CoroutineDispatcher = Dispatchers.IO
) : FavoriteUniversityRepository {

    override fun getFavoriteUniversities(): Flow<List<UniversityEntity>> {
        return dao.getAllUniversities()
    }

    override fun checkUniversityFavorite(universityName: String): Flow<Boolean> {
        return dao.checkUniversityExist(universityName)
    }

    override suspend fun addFavoriteUniversity(university: University) = withContext(ioDispatcher) {
        dao.insertUniversity(university.toUniversityEntity())
    }

    override suspend fun deleteFavoriteUniversity(university: University) = withContext(ioDispatcher) {
        dao.deleteUniversity(university.toUniversityEntity())
    }

}