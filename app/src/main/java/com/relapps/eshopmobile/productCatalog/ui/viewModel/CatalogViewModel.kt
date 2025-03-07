package com.relapps.eshopmobile.presentation

import CatalogResponse
import CatalogType
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.relapps.eshopmobile.productCatalog.domain.CatalogUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val catalogUseCase: CatalogUseCase
) : ViewModel() {

    private val _catalog = MutableStateFlow<CatalogResponse?>(null)
//    val catalog: StateFlow<CatalogResponse?> = _catalog.asStateFlow()
    private val _catalogTypes = MutableStateFlow<List<CatalogType>?>(null)
    val catalogTypes: StateFlow<List<CatalogType>?> = _catalogTypes.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _filteredCatalog = MutableStateFlow<CatalogResponse?>(null)
    val filteredCatalog: StateFlow<CatalogResponse?> = _filteredCatalog.asStateFlow()

    init {
        fetchCatalogTypes()
        fetchCatalog()
        observeSearch()
    }

    fun fetchCatalogTypes() {
        viewModelScope.launch {
            try {
                catalogUseCase.getCatalogTypes().collectLatest { types ->
                    _catalogTypes.value = types
                }
            } catch (e: Exception) {
                println("Error fetching catalog: ${e.message}")
                // Handle the error, e.g., set an error state
            }
        }
    }

    fun fetchCatalog() {
        viewModelScope.launch {
            try {
                catalogUseCase.getCatalog().collectLatest { catalogResponse ->
                    val catalogWithImages = catalogResponse.copy(
                        data = catalogResponse.data.map { it.copy(image = null) }
                    )
                    _catalog.value = catalogWithImages
                }
            } catch (e: Exception) {
                println("Error fetching catalog: ${e.message}")
                // Handle the error, e.g., set an error state
            }
        }
    }

    fun fetchItemImage(itemId: Int) {
        viewModelScope.launch {
            try {
                catalogUseCase.getItemPicture(itemId).collectLatest { imageBitmap ->
                    // Update the catalog to include the image
                    _catalog.update { currentCatalog ->
                        currentCatalog?.let { catalog ->
                            val updatedItems = catalog.data.map { item ->
                                if (item.id == itemId) {
                                    item.copy(image = imageBitmap)
                                } else {
                                    item
                                }
                            }
                            catalog.copy(data = updatedItems)
                        }
                    }
                }
            } catch (e: Exception) {
                println("Error fetching image for item $itemId: ${e.message}")
                // Handle the error, e.g., show an error message
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    private fun observeSearch(){
        viewModelScope.launch {
            combine(_catalog, _searchQuery){ catalogResponse, query ->
                if(catalogResponse != null){
                    if(query.isBlank()){
                        catalogResponse
                    }else{
                        val filteredItems = catalogResponse.data.filter { item ->
                            item.name.contains(query, ignoreCase = true) ||
                                    item.description.contains(query, ignoreCase = true)
                        }
                        catalogResponse.copy(data = filteredItems)
                    }
                }else{
                    null
                }

            }.collectLatest { filteredResponse ->
                _filteredCatalog.value = filteredResponse
            }
        }
    }
}