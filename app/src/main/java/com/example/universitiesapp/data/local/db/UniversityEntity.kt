package com.example.universitiesapp.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_university")
data class UniversityEntity(
    @PrimaryKey
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("phone")
    val phone: String?,
    @ColumnInfo("fax")
    val fax: String?,
    @ColumnInfo("website")
    val website: String?,
    @ColumnInfo("email")
    val email: String?,
    @ColumnInfo("address")
    val address: String?,
    @ColumnInfo("rector")
    val rector: String?
)
