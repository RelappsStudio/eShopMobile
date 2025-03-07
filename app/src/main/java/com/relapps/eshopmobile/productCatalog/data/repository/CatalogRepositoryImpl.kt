package com.relapps.eshopmobile.productCatalog.data.repository

import CatalogResponse
import CatalogType
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.relapps.eshopmobile.productCatalog.data.remote.CatalogApiService
import com.relapps.eshopmobile.productCatalog.domain.repository.CatalogRepository
import com.relapps.eshopmobile.productCatalog.utils.RetrofitInstance
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

    override suspend fun getItemPicture(itemId: Int): Flow<ImageBitmap> {
        return flow {
            try {
                val response = RetrofitInstance.catalogApi.getItemPicture(itemId)
                val imageBitmap = BitmapFactory.decodeStream(response.byteStream()).asImageBitmap()
                emit(imageBitmap)
            } catch (e: Exception) {
                print(e.message)
            }
        }
    }

    override suspend fun getCatalogTypes(): Flow<List<CatalogType>> {
       return flow {
           try {
               val response = RetrofitInstance.catalogApi.getAllTypes()
               emit(response)
           } catch (e: Exception) {
               print(e.message)
           }
       }
    }

}

