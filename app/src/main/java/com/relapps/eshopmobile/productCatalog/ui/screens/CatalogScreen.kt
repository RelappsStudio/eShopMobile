import android.R.attr.onClick
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.relapps.eshopmobile.presentation.CatalogViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.IconButton
import androidx.compose.ui.draw.clip

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(viewModel: CatalogViewModel = viewModel()) {
    val filteredCatalog by viewModel.filteredCatalog.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val catalogTypes by viewModel.catalogTypes.collectAsState()


    Scaffold { innerPadding ->
        PullToRefreshBox(
            isRefreshing = false,
            onRefresh = {
                viewModel.fetchCatalog()
                viewModel.fetchCatalogTypes()
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
            ) {

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { viewModel.updateSearchQuery(it) },
                    leadingIcon = {
                        Icon(Icons.Filled.Search, contentDescription = "Search")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(40.dp),
                    placeholder = { Text("Search items") },
                    shape = RoundedCornerShape(50.dp)
                )

//                figure out why catalog grid is crashing the app

//                catalogTypes?.let { catalogTypesData ->
//                    LazyVerticalGrid(
//                        columns = GridCells.Adaptive(minSize = 128.dp)
//                    ) {
//                        items(
//                            items = catalogTypesData,
//                            key = {it.id}
//                        ) { catalogTypeValue ->
//                            ElevatedButton(onClick = {/*figure out what buttons do */  }) {
//                                Text(catalogTypeValue.name)
//                            }
//                        }
//                    }
//                }


                filteredCatalog?.let { catalogData ->
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        items(items = catalogData.data, key = { it.id }) { item ->
                            LaunchedEffect(key1 = item.id) {
                                viewModel.fetchItemImage(item.id)
                            }

                            CatalogItemWidget(

                                item = item
                            )
                        }
                    }
                }
            }

        }
    }
}
