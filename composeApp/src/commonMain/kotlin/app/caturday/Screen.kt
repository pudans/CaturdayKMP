package app.caturday

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
	val route: String,
	val title: String,
	val defaultIcon: ImageVector,
	val selectedIcon: ImageVector
) {
	data object Feed : Screen("/feed", "Feed", Icons.Outlined.Home, Icons.Filled.Home)
	data object Profile : Screen("/profile", "Profile", Icons.Outlined.Person, Icons.Filled.Person)
	data object UploadVideo : Screen("/upload", "Upload", Icons.Outlined.AddCircle, Icons.Filled.AddCircle)

	companion object {
		val ITEMS = listOf(Feed, UploadVideo, Profile)
	}
}