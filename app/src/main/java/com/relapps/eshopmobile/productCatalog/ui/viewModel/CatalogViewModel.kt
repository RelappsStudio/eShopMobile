package com.relapps.eshopmobile.presentation

import CatalogResponse
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.relapps.eshopmobile.productCatalog.domain.CatalogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val catalogUseCase: CatalogUseCase
) : ViewModel() {
    private val _catalog = MutableStateFlow<CatalogResponse?>(null)
    val catalog: StateFlow<CatalogResponse?> = _catalog
    private val _images = mutableMapOf<Int, MutableLiveData<ImageBitmap?>>()

    init {
        fetchCatalog()
    }

    fun fetchCatalog() {
        viewModelScope.launch {
            try {
                 catalogUseCase.getCatalog().collect { catalog ->

                     _catalog.value = catalog
                     //TODO: refractor image fetching to be a usecase
//                     catalog.data.forEach {
//                         fetchItemImage(it.id)
//                     }
                 }

            } catch (e: Exception) {

               print("Error fetching catalog: ${e.message}")
            }
        }
    }

    fun fetchItemImage(itemId: Int): LiveData<ImageBitmap?> {
        if (!_images.containsKey(itemId)) {
            _images[itemId] = MutableLiveData()
            viewModelScope.launch {
                try {
//                    val response = RetrofitInstance.catalogApi.getItemPicture(itemId)
//                    val imageBitmap = response.byteStream().use { it.toImageBitmap() }
//                    _images[itemId]?.postValue(imageBitmap)
                } catch (e: Exception) {
                    print( "Error fetching image for item $itemId")
                    _images[itemId]?.postValue(null)
                }
            }
        }
        return _images[itemId]!!
    }

    private fun InputStream.toImageBitmap(): ImageBitmap? {
        return BitmapFactory.decodeStream(this)?.asImageBitmap()
    }
}