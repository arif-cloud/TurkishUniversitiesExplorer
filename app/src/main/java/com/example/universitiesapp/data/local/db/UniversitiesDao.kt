package com.example.universitiesapp.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UniversitiesDao {

    @Query("SELECT * FROM favorite_university")
    fun getAllUniversities(): Flow<List<UniversityEntity>>

    @Query("SELECT EXISTS(SELECT * FROM favorite_university WHERE name = :universityName)")
    fun checkUniversityExist(universityName : String) : Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUniversity(universityEntity: UniversityEntity)

    @Delete
    suspend fun deleteUniversity(universityEntity: UniversityEntity)

}