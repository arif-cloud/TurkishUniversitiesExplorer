package com.example.universitiesapp.domain.repository

import com.example.universitiesapp.data.remote.api.response.UniversityResponse

interface UniversityRepository {

    suspend fun getUniversities(page : Int) : UniversityResponse

}