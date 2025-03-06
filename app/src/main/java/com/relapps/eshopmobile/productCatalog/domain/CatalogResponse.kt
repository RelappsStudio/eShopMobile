data class CatalogResponse (
    val pageIndex: Int,
    val pageSize: Int,
    var data: List<CatalogItem>
    )

data class CatalogItem (
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    var picture: Any?,
    val catalogTypeId: Int,
    val catalogType: String?,
    val catalogBrandId: Int,
    val catalogBrand: String?,
    val availableStock: Int,
    val restockTreshold: Int,
    val maxStockTreshold: Int,
    val onReorder: Boolean,
)
