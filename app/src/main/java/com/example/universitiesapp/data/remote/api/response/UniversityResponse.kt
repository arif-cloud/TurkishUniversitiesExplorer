package com.example.universitiesapp.data.remote.api.response


import com.example.universitiesapp.data.remote.api.dto.UniversityData
import com.google.gson.annotations.SerializedName

data class UniversityResponse(
    @SerializedName("currentPage")
    val currentPage: Int? = null,
    @SerializedName("data")
    val data : List<UniversityData> = emptyList(),
    @SerializedName("itemPerPage")
    val itemPerPage: Int? = null,
    @SerializedName("pageSize")
    val pageSize: Int? = null,
    @SerializedName("total")
    val total: Int? = null,
    @SerializedName("totalPage")
    val totalPage: Int? = null
)