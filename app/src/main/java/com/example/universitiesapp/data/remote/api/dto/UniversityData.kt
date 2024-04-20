package com.example.universitiesapp.data.remote.api.dto


import com.example.universitiesapp.domain.model.University
import com.google.gson.annotations.SerializedName

data class UniversityData(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("province")
    val province: String?,
    @SerializedName("universities")
    val universities: List<University>?
)