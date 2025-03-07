package com.relapps.eshopmobile.productCatalog.domain
import CatalogResponse
import CatalogType
import androidx.compose.ui.graphics.ImageBitmap
import com.relapps.eshopmobile.productCatalog.domain.repository.CatalogRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CatalogUseCase @Inject constructor (
    private val catalogRepository: CatalogRepository
) {
    suspend fun getCatalog(): Flow<CatalogResponse> = catalogRepository.getCatalog()
    suspend fun getItemPicture(itemId: Int): Flow<ImageBitmap> = catalogRepository.getItemPicture(itemId)
    suspend fun getCatalogTypes(): Flow<List<CatalogType>> = catalogRepository.getCatalogTypes()
}