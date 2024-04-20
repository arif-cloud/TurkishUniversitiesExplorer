package com.example.universitiesapp.data.repository

import com.example.universitiesapp.data.remote.api.UniversityApi
import com.example.universitiesapp.data.remote.api.response.UniversityResponse
import com.example.universitiesapp.domain.repository.UniversityRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UniversityRepositoryImpl @Inject constructor(
    private val api: UniversityApi,
    private val ioDispatchers : CoroutineDispatcher = Dispatchers.IO
) : UniversityRepository {

    override suspend fun getUniversities(page: Int): UniversityResponse = withContext(ioDispatchers) {
        api.getUniversities(page)
    }

}