package com.numq.ecommerce.category

data class Category(
    val id: String,
    val name: String,
    val description: String,
    val imageBytes: ByteArray,
    val tags: Array<String>,
    val createdAt: Long,
    val updatedAt: Long
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Category

        if (id != other.id) return false
        if (name != other.name) return false
        if (description != other.description) return false
        if (!imageBytes.contentEquals(other.imageBytes)) return false
        if (!tags.contentEquals(other.tags)) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + imageBytes.contentHashCode()
        result = 31 * result + tags.contentHashCode()
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + updatedAt.hashCode()
        return result
    }
}