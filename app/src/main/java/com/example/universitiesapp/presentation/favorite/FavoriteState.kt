package com.example.universitiesapp.presentation.favorite

import com.example.universitiesapp.domain.model.University

data class FavoriteState(
    val data : List<University>? = null,
    val error : String = ""
)
