package com.relapps.eshopmobile.productCatalog.domain
import CatalogResponse
import com.relapps.eshopmobile.productCatalog.domain.repository.CatalogRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CatalogUseCase @Inject constructor (
    private val catalogRepository: CatalogRepository
) {
    suspend fun getCatalog(): Flow<CatalogResponse> = catalogRepository.getCatalog()
}