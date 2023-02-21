package com.numq.ecommerce.catalog

data class CatalogItem(
    val id: String,
    val sku: String,
    val name: String,
    val description: String,
    val imageBytes: ByteArray,
    val price: Float,
    val discount: Float,
    val weight: Float,
    val quantity: Int,
    val tags: Array<String>,
    val createdAt: Long,
    val updatedAt: Long
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CatalogItem

        if (id != other.id) return false
        if (sku != other.sku) return false
        if (name != other.name) return false
        if (description != other.description) return false
        if (!imageBytes.contentEquals(other.imageBytes)) return false
        if (price != other.price) return false
        if (discount != other.discount) return false
        if (weight != other.weight) return false
        if (quantity != other.quantity) return false
        if (!tags.contentEquals(other.tags)) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + sku.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + imageBytes.contentHashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + discount.hashCode()
        result = 31 * result + weight.hashCode()
        result = 31 * result + quantity
        result = 31 * result + tags.contentHashCode()
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + updatedAt.hashCode()
        return result
    }
}