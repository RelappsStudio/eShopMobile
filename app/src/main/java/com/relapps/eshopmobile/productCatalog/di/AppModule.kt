package com.relapps.eshopmobile.productCatalog.di

import com.relapps.eshopmobile.productCatalog.data.remote.CatalogApiService
import com.relapps.eshopmobile.productCatalog.data.repository.CatalogRepositoryImpl
import com.relapps.eshopmobile.productCatalog.domain.CatalogUseCase
import com.relapps.eshopmobile.productCatalog.domain.repository.CatalogRepository
import com.relapps.eshopmobile.productCatalog.utils.RetrofitInstance
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CatalogModule {

//
    @Binds
    @Singleton
    abstract fun bindCatalogRepository(
        catalogRepositoryImpl: CatalogRepositoryImpl
    ): CatalogRepository
//@Binds
//@Singleton
//abstract fun bindCatalogRepository(
//    catalogRepositoryImpl: CatalogRepositoryImpl
//): CatalogRepository

    companion object {
        @Provides
        @Singleton
        fun provideCatalogApiService(): CatalogApiService {
            return RetrofitInstance.catalogApi
        }

        @Provides
        @Singleton
        fun provideCatalogUseCase(catalogRepository: CatalogRepository): CatalogUseCase {
            return CatalogUseCase(catalogRepository)
        }


//        @Provides
//        @Singleton
//        fun provideCatalogRepository(catalogApiService: CatalogApiService): CatalogRepository {
//            return CatalogRepositoryImpl(catalogApiService)
//        }
    }



}
