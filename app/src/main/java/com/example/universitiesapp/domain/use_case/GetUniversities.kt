package com.example.universitiesapp.domain.use_case

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.universitiesapp.data.remote.api.dto.UniversityData
import com.example.universitiesapp.data.remote.pagination.UniversityPagingSource
import com.example.universitiesapp.domain.repository.UniversityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUniversities @Inject constructor(
    private val repository: UniversityRepository
) {
    operator fun invoke() : Flow<PagingData<UniversityData>> {
        return Pager(config = PagingConfig(pageSize = 1)) {
            UniversityPagingSource(repository)
        }.flow
    }
}