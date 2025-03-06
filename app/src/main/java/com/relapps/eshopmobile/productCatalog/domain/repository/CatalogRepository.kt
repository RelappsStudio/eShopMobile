package com.relapps.eshopmobile.productCatalog.domain.repository
import CatalogResponse
import kotlinx.coroutines.flow.Flow

interface CatalogRepository {
    suspend fun getCatalog(): Flow<CatalogResponse>
}