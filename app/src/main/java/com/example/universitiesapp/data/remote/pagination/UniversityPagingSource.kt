package com.example.universitiesapp.data.remote.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.universitiesapp.data.remote.api.dto.UniversityData
import com.example.universitiesapp.domain.repository.UniversityRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UniversityPagingSource @Inject constructor(
    private val repository: UniversityRepository
) : PagingSource<Int, UniversityData>() {


    override fun getRefreshKey(state: PagingState<Int, UniversityData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UniversityData> {
        return try {
            val pageNumber = params.key ?: 1
            val response = repository.getUniversities(pageNumber)
            val data = response.data
            LoadResult.Page(
                data = data,
                prevKey = if (pageNumber == 1) null else pageNumber.minus(1),
                nextKey = if (pageNumber == response.totalPage) null else pageNumber.plus(1)
            )
        } catch (e : Exception) {
            LoadResult.Error(e)
        }
    }

}