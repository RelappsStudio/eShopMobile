package com.relapps.eshopmobile.productCatalog.data.remote

import CatalogItem
import CatalogResponse
import CatalogType
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_VERSION = "1.0"

//http://localhost:5222/api/catalog/items?api-version=1.0
interface CatalogApiService {

    @GET("api/catalog/items")
    suspend fun getAllItems(
        @Query("api-version") version: String = API_VERSION,
        @Query("pageSize") pageSize: Int = 1000,
        @Query("pageIndex") pageIndex: Int = 0
    ): CatalogResponse

    @GET("api/catalog/items/{id}")
    suspend fun getItemDetails(
        @Path("id") itemId: Int,
        @Query("api-version") version: String = API_VERSION
    ) :CatalogItem

    @GET("/api/catalog/catalogtypes")
    suspend fun getAllTypes(
        @Query("api-version") version: String = API_VERSION
    ): List<CatalogType>

    @GET("/api/catalog/items/{id}/pic")
    suspend fun getItemPicture(
        @Path("id") itemId: Int,
        @Query("api-version") version: String = API_VERSION
    ): ResponseBody
}