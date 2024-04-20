package com.example.universitiesapp.data.remote.api

import com.example.universitiesapp.data.remote.api.response.UniversityResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface UniversityApi {

    @GET("universities-at-turkey/page-{page}.json")
    suspend fun getUniversities(@Path("page") page : Int) : UniversityResponse

}