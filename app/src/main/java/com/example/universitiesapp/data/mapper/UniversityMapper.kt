package com.example.universitiesapp.data.mapper

import com.example.universitiesapp.data.local.db.UniversityEntity
import com.example.universitiesapp.domain.model.University

fun University.toUniversityEntity() : UniversityEntity {
    return UniversityEntity(
        name = name ?: "",
        phone = phone,
        fax = fax,
        website = website,
        email = email,
        address = address,
        rector = rector
    )
}

fun UniversityEntity.toUniversity() : University {
    return University(
        name = name,
        phone = phone,
        fax = fax,
        website = website,
        email = email,
        address = address,
        rector = rector
    )
}