package model

import kotlinx.serialization.Serializable

@Serializable
data class PreviewImage(
	val id: String? = null,
	val url: String? = null
)
