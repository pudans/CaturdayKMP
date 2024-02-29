package model

import kotlinx.serialization.Serializable

@Serializable
data class User(
	val uid: String? = null,
	val name: String? = null,
	val email: String? = null,
	val photoUrl: String? = null
)
