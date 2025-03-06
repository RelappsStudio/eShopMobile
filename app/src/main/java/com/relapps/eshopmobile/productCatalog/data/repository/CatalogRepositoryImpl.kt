package com.relapps.eshopmobile.productCatalog.data.repository

import CatalogResponse
import com.relapps.eshopmobile.productCatalog.data.remote.CatalogApiService
import com.relapps.eshopmobile.productCatalog.domain.repository.CatalogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CatalogRepositoryImpl @Inject constructor (
    private val catalogApiService: CatalogApiService
) : CatalogRepository {

    override suspend fun getCatalog(): Flow<CatalogResponse> {
        return flow {
            try {
                val catalog = catalogApiService.getAllItems()
                emit(catalog)
            } catch (e: Exception) {
                print(e.message)
            }
        }
    }

}