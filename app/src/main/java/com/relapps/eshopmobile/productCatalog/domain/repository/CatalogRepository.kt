package com.relapps.eshopmobile.productCatalog.domain.repository
import CatalogResponse
import CatalogType
import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.coroutines.flow.Flow

interface CatalogRepository {
    suspend fun getCatalog(): Flow<CatalogResponse>
    suspend fun getItemPicture(itemId: Int): Flow<ImageBitmap>
    suspend fun getCatalogTypes(): Flow<List<CatalogType>>
}